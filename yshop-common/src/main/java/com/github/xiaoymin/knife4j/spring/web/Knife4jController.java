package com.github.xiaoymin.knife4j.spring.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSort;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.github.xiaoymin.knife4j.spring.common.SwaggerBootstrapUiHostNameProvider;
import com.github.xiaoymin.knife4j.spring.model.MarkdownFiles;
import com.github.xiaoymin.knife4j.spring.model.RestHandlerMapping;
import com.github.xiaoymin.knife4j.spring.model.SwaggerBootstrapUi;
import com.github.xiaoymin.knife4j.spring.model.SwaggerBootstrapUiPath;
import com.github.xiaoymin.knife4j.spring.model.SwaggerBootstrapUiTag;
import com.github.xiaoymin.knife4j.spring.model.SwaggerExt;
import com.github.xiaoymin.knife4j.spring.util.SwaggerUtil;
import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import io.swagger.models.Swagger;
import io.swagger.models.properties.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UriComponents;
import springfox.documentation.RequestHandler;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.Documentation;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.service.RequestHandlerProvider;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.WebMvcRequestHandler;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.swagger.common.HostNameProvider;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author LionCitys
 */
@Controller
@ApiIgnore
public class Knife4jController {
    private static final String DEFAULT_SORT_URL = "/v2/api-docs-ext";
    private static final String HAL_MEDIA_TYPE = "application/hal+json";
    private static final Logger LOGGER = LoggerFactory.getLogger(Knife4jController.class);
    private final ServiceModelToSwagger2Mapper mapper;
    private final DocumentationCache documentationCache;
    private final JsonSerializer jsonSerializer;
    private final String hostNameOverride;
    private final List<RequestHandlerProvider> handlerProviders;
    private final MarkdownFiles markdownFiles;
    private ArrayList<RestHandlerMapping> globalHandlerMappings = new ArrayList<>();
    private final RequestMethod[] globalRequestMethods;

    @Autowired
    public Knife4jController(Environment environment, ServiceModelToSwagger2Mapper mapper, DocumentationCache documentationCache, JsonSerializer jsonSerializer, List<RequestHandlerProvider> handlerProviders, ObjectProvider<MarkdownFiles> markdownFilesObjectProvider) {
        this.globalRequestMethods = new RequestMethod[]{RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS, RequestMethod.HEAD};
        this.mapper = mapper;
        this.documentationCache = documentationCache;
        this.jsonSerializer = jsonSerializer;
        this.hostNameOverride = environment.getProperty("springfox.documentation.swagger.v2.host", "DEFAULT");
        this.handlerProviders = handlerProviders;
        this.markdownFiles = (MarkdownFiles)markdownFilesObjectProvider.getIfAvailable();
    }

    private Function<RequestHandlerProvider, ? extends Iterable<RequestHandler>> handlers() {
        return new Function<RequestHandlerProvider, Iterable<RequestHandler>>() {
            @Override
            public Iterable<RequestHandler> apply(RequestHandlerProvider input) {
                return input.requestHandlers();
            }
        };
    }

    @RequestMapping(value = DEFAULT_SORT_URL,
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_VALUE, HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Json> apiSorts(@RequestParam(value = "group",required = false) String swaggerGroup, HttpServletRequest request) {
        String groupName = Optional.ofNullable(swaggerGroup).orElse("default");
        Documentation documentation = this.documentationCache.documentationByGroup(groupName);
        if (documentation == null) {
            LOGGER.warn("Unable to find specification for group {},use default", groupName);
            documentation = this.documentationCache.documentationByGroup("default");
            if (documentation == null) {
                LOGGER.warn("Unable to find specification for group default");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        Swagger swagger = this.mapper.mapDocumentation(documentation);
        UriComponents uriComponents = null;

        try {
            uriComponents = HostNameProvider.componentsFrom(request, swagger.getBasePath());
        } catch (Throwable var9) {
            LOGGER.error(var9.getClass().getName() + ":" + var9.getMessage());
            if (var9 instanceof NoClassDefFoundError) {
                String msg = var9.getMessage();
                if (msg != null && !"".equals(msg) && msg.endsWith("HostNameProvider")) {
                    uriComponents = SwaggerBootstrapUiHostNameProvider.componentsFrom(request, swagger.getBasePath());
                }
            }
        }

        swagger.basePath(Strings.isNullOrEmpty(uriComponents.getPath()) ? "/" : uriComponents.getPath());
        if (Strings.isNullOrEmpty(swagger.getHost())) {
            swagger.host(this.hostName(uriComponents));
        }
        extend(swagger);
        SwaggerExt swaggerExt = new SwaggerExt(swagger);
        swaggerExt.setSwaggerBootstrapUi(this.initSwaggerBootstrapUi(request, documentation, swaggerExt));
        return new ResponseEntity<>(this.jsonSerializer.toJson(swaggerExt), HttpStatus.OK);
    }

    private SwaggerBootstrapUi initSwaggerBootstrapUi(HttpServletRequest request, Documentation documentation, SwaggerExt swaggerExt) {
        SwaggerBootstrapUi swaggerBootstrapUi = new SwaggerBootstrapUi();
        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        if (wc == null) {
            String msg = "WebApplicationContext is Empty~!,Enable SwaggerBootstrapUi fun fail~!";
            LOGGER.warn(msg);
            swaggerBootstrapUi.setErrorMsg(msg);
            return swaggerBootstrapUi;
        } else {
            Iterator<Tag> tags = documentation.getTags().iterator();
            this.initGlobalRequestMappingArray(swaggerExt);
            List<SwaggerBootstrapUiTag> targetTagLists = Lists.newArrayList();

            ArrayList targetPathLists;
            SwaggerBootstrapUiTag tag;
            label64:
            for(targetPathLists = Lists.newArrayList(); tags.hasNext(); targetTagLists.add(tag)) {
                Tag sourceTag = tags.next();
                String tagName = sourceTag.getName();
                int order = 2147483647;
                tag = new SwaggerBootstrapUiTag(order);
                tag.name(tagName).description(sourceTag.getDescription());
                Api tagApi = null;
                RestHandlerMapping tagMapping = null;
                Iterator<RestHandlerMapping> var15 = this.globalHandlerMappings.iterator();
                while(true) {
                    while(true) {
                        while(var15.hasNext()) {
                            RestHandlerMapping rhm = var15.next();
                            Api api = (Api)rhm.getBeanType().getAnnotation(Api.class);
                            if (api != null) {
                                api.tags();
                                if (api.tags().length > 0) {
                                    if (Lists.newArrayList(api.tags()).contains(tagName)) {
                                        tagApi = api;
                                        tagMapping = rhm;
                                        this.createPathInstance(rhm, targetPathLists);
                                    } else {
                                        String firstTag = api.tags()[0];
                                        if (StringUtils.isEmpty(firstTag) && this.checkExists(tagName, rhm.getBeanType())) {
                                            tagApi = api;
                                            tagMapping = rhm;
                                            this.createPathInstance(rhm, targetPathLists);
                                        }
                                    }
                                } else if (this.checkExists(tagName, rhm.getBeanType())) {
                                    if (!StringUtils.isEmpty(api.value())) {
                                        tag.name(api.value());
                                    }
                                    tagApi = api;
                                    tagMapping = rhm;
                                    this.createPathInstance(rhm, targetPathLists);
                                }
                            } else if (this.checkExists(tagName, rhm.getBeanType())) {
                                tagMapping = rhm;
                                this.createPathInstance(rhm, targetPathLists);
                            }
                        }
                        if (tagMapping != null) {
                            tag.setOrder(this.getRestTagOrder(tagMapping.getBeanType(), tagApi));
                        }
                        continue label64;
                    }
                }
            }

            Collections.sort(targetTagLists, new Comparator<SwaggerBootstrapUiTag>() {
                @Override
                public int compare(SwaggerBootstrapUiTag o1, SwaggerBootstrapUiTag o2) {
                    return o1.getOrder().compareTo(o2.getOrder());
                }
            });
            targetPathLists.sort(new Comparator<SwaggerBootstrapUiPath>() {
                @Override
                public int compare(SwaggerBootstrapUiPath o1, SwaggerBootstrapUiPath o2) {
                    return o1.getOrder().compareTo(o2.getOrder());
                }
            });
            swaggerBootstrapUi.setTagSortLists(targetTagLists);
            swaggerBootstrapUi.setPathSortLists(targetPathLists);
            if (this.markdownFiles != null) {
                swaggerBootstrapUi.setMarkdownFiles(this.markdownFiles.getMarkdownFiles());
            }

            return swaggerBootstrapUi;
        }
    }

    private void initGlobalRequestMappingArray(SwaggerExt swaggerExt) {
        if (this.globalHandlerMappings.size() == 0) {
            String parentPath = "";
            if (!StringUtils.isEmpty(swaggerExt.getBasePath()) && !"/".equals(swaggerExt.getBasePath())) {
                parentPath = parentPath + swaggerExt.getBasePath();
            }

            try {
                List<RequestHandler> requestHandlers = FluentIterable.from(this.handlerProviders).transformAndConcat(this.handlers()).toList();
                Iterator<RequestHandler> var4 = requestHandlers.iterator();

                while(true) {
                    RequestHandler requestHandler;
                    do {
                        if (!var4.hasNext()) {
                            return;
                        }

                        requestHandler = var4.next();
                    } while(!(requestHandler instanceof WebMvcRequestHandler));

                    WebMvcRequestHandler webMvcRequestHandler = (WebMvcRequestHandler)requestHandler;
                    Set<String> patterns = webMvcRequestHandler.getRequestMapping().getPatternsCondition().getPatterns();
                    Set<RequestMethod> restMethods = webMvcRequestHandler.getRequestMapping().getMethodsCondition().getMethods();
                    HandlerMethod handlerMethod = webMvcRequestHandler.getHandlerMethod();
                    Class<?> controllerClazz = ClassUtils.getUserClass(handlerMethod.getBeanType());
                    Method method = ClassUtils.getMostSpecificMethod(handlerMethod.getMethod(), controllerClazz);

                    String url;
                    for(Iterator<String> var12 = patterns.iterator(); var12.hasNext(); this.globalHandlerMappings.add(new RestHandlerMapping(parentPath + url, controllerClazz, method, restMethods))) {
                        url = var12.next();
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("url:" + url + "\r\nclass:" + controllerClazz.toString() + "\r\nmethod:" + method.toString());
                        }
                    }
                }
            } catch (Exception var14) {
                LOGGER.error(var14.getMessage(), var14);
            }
        }

    }

    /** @deprecated */
    @Deprecated
    private void initGlobalRequestMappingArray(WebApplicationContext wc, SwaggerExt swaggerExt) {
        if (this.globalHandlerMappings.size() == 0) {
            String parentPath = "";
            if (!StringUtils.isEmpty(swaggerExt.getBasePath()) && !"/".equals(swaggerExt.getBasePath())) {
                parentPath = parentPath + swaggerExt.getBasePath();
            }

            Map<String, HandlerMapping> requestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(wc, HandlerMapping.class, true, false);
            Iterator<HandlerMapping> var5 = requestMappings.values().iterator();

            while(true) {
                HandlerMapping handlerMapping;
                do {
                    if (!var5.hasNext()) {
                        return;
                    }
                    handlerMapping = var5.next();
                } while(!(handlerMapping instanceof RequestMappingHandlerMapping));
                RequestMappingHandlerMapping rmhMapping = (RequestMappingHandlerMapping)handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhMapping.getHandlerMethods();
                for (RequestMappingInfo rmi : handlerMethods.keySet()) {
                    PatternsRequestCondition prc = rmi.getPatternsCondition();
                    Set<RequestMethod> restMethods = rmi.getMethodsCondition().getMethods();
                    Set<String> patterns = prc.getPatterns();
                    HandlerMethod handlerMethod = (HandlerMethod) handlerMethods.get(rmi);

                    String url;
                    Class clazz;
                    Method method;
                    for (Iterator<String> var15 = patterns.iterator(); var15.hasNext(); this.globalHandlerMappings.add(new RestHandlerMapping(parentPath + url, clazz, method, restMethods))) {
                        url = var15.next();
                        clazz = ClassUtils.getUserClass(handlerMethod.getBeanType());
                        method = ClassUtils.getMostSpecificMethod(handlerMethod.getMethod(), clazz);
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("url:" + url + "\r\nclass:" + clazz.toString() + "\r\nmethod:" + method.toString());
                        }
                    }
                }
            }
        }

    }

    private void createPathInstance(RestHandlerMapping rhm, List<SwaggerBootstrapUiPath> targetPathLists) {
        if (rhm.getRequestMethods() != null && rhm.getRequestMethods().size() != 0) {
            Iterator<RequestMethod> var7 = rhm.getRequestMethods().iterator();

            while(var7.hasNext()) {
                RequestMethod requestMethod = var7.next();
                targetPathLists.add(new SwaggerBootstrapUiPath(rhm.getUrl(), requestMethod.name().toUpperCase(), this.getRestMethodOrder(rhm.getBeanOfMethod())));
            }
        } else {
            RequestMethod[] var3 = this.globalRequestMethods;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                RequestMethod requestMethod = var3[var5];
                targetPathLists.add(new SwaggerBootstrapUiPath(rhm.getUrl(), requestMethod.name().toUpperCase(), this.getRestMethodOrder(rhm.getBeanOfMethod())));
            }
        }

    }

    private int getRestTagOrder(Class<?> aClass, Api api) {
        int order = 2147483647;
        if (api != null) {
            int post = api.position();
            if (post == 0) {
                if (aClass != null) {
                    ApiSort annotation = (ApiSort) ClassUtils.getUserClass(aClass).getAnnotation(ApiSort.class);
                    if (annotation != null) {
                        order = annotation.value();
                    }
                }
            } else {
                order = post;
            }
        } else if (aClass != null) {
            ApiSort annotation = (ApiSort) ClassUtils.getUserClass(aClass).getAnnotation(ApiSort.class);
            if (annotation != null) {
                order = annotation.value();
            }
        }

        return order;
    }

    private int getRestMethodOrder(Method target) {
        int pathOrder = 2147483647;
        ApiOperation apiOperation = (ApiOperation)target.getAnnotation(ApiOperation.class);
        ApiOperationSort apiOperationSort;
        if (apiOperation != null) {
            if (apiOperation.position() != 0) {
                pathOrder = apiOperation.position();
            } else {
                apiOperationSort = (ApiOperationSort)target.getAnnotation(ApiOperationSort.class);
                if (apiOperationSort != null) {
                    pathOrder = apiOperationSort.value();
                }
            }
        } else {
            apiOperationSort = (ApiOperationSort)target.getAnnotation(ApiOperationSort.class);
            if (apiOperationSort != null) {
                pathOrder = apiOperationSort.value();
            }
        }

        return pathOrder;
    }

    private boolean checkExists(String tagName, Class<?> aClass) {
        boolean flag = false;
        if (!StringUtils.isEmpty(tagName)) {
            String regexStr = tagName.replaceAll("-", ".*?");
            Pattern pattern = Pattern.compile(regexStr, 2);
            if (pattern.matcher(aClass.getSimpleName()).matches()) {
                flag = true;
            }
        }

        return flag;
    }

    private String hostName(UriComponents uriComponents) {
        if ("DEFAULT".equals(hostNameOverride)) {
            String host = uriComponents.getHost();
            int port = uriComponents.getPort();
            if (port > -1) {
                return String.format("%s:%d", host, port);
            }
            return host;
        }
        return hostNameOverride;
    }

    private void extend(Swagger swagger) {
        // 响应返回参数增强
        for (Map.Entry<String, Model> entry : swagger.getDefinitions().entrySet()) {
            Model model = entry.getValue();
            String key = entry.getKey();
            if (key.contains("ApiResult") && !SwaggerUtil.getRealType(key).contains("ApiResult")) {
                Map<String, Property> props = model.getProperties();
                Property dataProp = props.get("data");
                Property newProp = SwaggerUtil.getNewProp(dataProp, SwaggerUtil.getRealType(key), swagger.getDefinitions());
                props.put("data", newProp);
            }
        }
    }
}
