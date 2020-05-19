package springfox.documentation.swagger2.web;

import com.github.xiaoymin.knife4j.spring.util.SwaggerUtil;
import com.google.common.base.Strings;
import io.swagger.models.Model;
import io.swagger.models.Swagger;
import io.swagger.models.properties.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.PropertySourcedMapping;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.swagger.common.HostNameProvider;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * Swagger2控制器
 *
 * @author LionCitys
 * @date 2020/01/07
 */
@Controller
@ApiIgnore
public class Swagger2Controller {
    private static final String DEFAULT_URL = "/v2/api-docs";
    private static final Logger LOGGER = LoggerFactory.getLogger(Swagger2Controller.class);
    private static final String HAL_MEDIA_TYPE = "application/hal+json";
    private final String hostNameOverride;
    private final DocumentationCache documentationCache;
    private final ServiceModelToSwagger2Mapper mapper;
    private final JsonSerializer jsonSerializer;

    @Autowired
    public Swagger2Controller(Environment environment, DocumentationCache documentationCache, ServiceModelToSwagger2Mapper mapper, JsonSerializer jsonSerializer) {
        this.hostNameOverride = environment.getProperty("springfox.documentation.swagger.v2.host", "DEFAULT");
        this.documentationCache = documentationCache;
        this.mapper = mapper;
        this.jsonSerializer = jsonSerializer;
    }

    @RequestMapping(
            value = {DEFAULT_URL},
            method = {RequestMethod.GET},
            produces = {APPLICATION_JSON_VALUE, HAL_MEDIA_TYPE}
    )
    @PropertySourcedMapping(
            value = "${springfox.documentation.swagger.v2.path}",
            propertyKey = "springfox.documentation.swagger.v2.path"
    )
    @ResponseBody
    public ResponseEntity getDocumentation(@RequestParam(value = "group",required = false) String swaggerGroup, HttpServletRequest servletRequest) {
        String groupName = (String) Optional.ofNullable(swaggerGroup).orElse("default");
        Documentation documentation = this.documentationCache.documentationByGroup(groupName);
        if (documentation == null) {
            LOGGER.warn("Unable to find specification for group {}", groupName);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            Swagger swagger = this.mapper.mapDocumentation(documentation);
            UriComponents uriComponents = HostNameProvider.componentsFrom(servletRequest, swagger.getBasePath());
            swagger.basePath(Strings.isNullOrEmpty(uriComponents.getPath()) ? "/" : uriComponents.getPath());
            if (Strings.isNullOrEmpty(swagger.getHost())) {
                swagger.host(this.hostName(uriComponents));
            }
            // 扩展
            swagger = extend(swagger);

            return new ResponseEntity(this.jsonSerializer.toJson(swagger), HttpStatus.OK);
        }
    }

    private String hostName(UriComponents uriComponents) {
        return getString(uriComponents, this.hostNameOverride);
    }

    private static String getString(UriComponents uriComponents, String hostNameOverride) {
        if ("DEFAULT".equals(hostNameOverride)) {
            String host = uriComponents.getHost();
            int port = uriComponents.getPort();
            return port > -1 ? String.format("%s:%d", host, port) : host;
        } else {
            return hostNameOverride;
        }
    }

    private Swagger extend(Swagger swagger) {
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
        return swagger;
    }
}
