package com.github.xiaoymin.knife4j.spring.util;

import io.swagger.models.Model;
import io.swagger.models.properties.*;
import io.swagger.models.properties.PropertyBuilder.PropertyId;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LionCitys
 * @modified By：
 * @version: 1.0
 * @date 2020/01/07
 */
public class SwaggerUtil {

    /**
     * 判断是否Swagger基本类型
     *
     * @param type 类型
     * @return boolean
     */
    private static boolean isBaseType(String type) {
        return SwaggerUtil.getSwaggerProperty(type) != null;
    }

    /**
     * 获取Swagger支持的类型
     *
     * @return {@link Map<String,  AbstractProperty >}
     */
    private static Map<String, AbstractProperty> getPropMap() {
        Map<String, AbstractProperty> map = new HashMap<>(8);
        map.put("integer", new IntegerProperty());
        map.put("int", new IntegerProperty());
        map.put("long", new LongProperty());
        map.put("string", new StringProperty());
        map.put("object", new ObjectProperty());
        map.put("array", new ArrayProperty());
        map.put("boolean", new BooleanProperty());
        map.put("date", new DateTimeProperty());
        return map;
    }

    /**
     * 通过java类型获取Swagger类型
     *
     * @param type javaType
     * @return swaggerType
     */
    private static AbstractProperty getSwaggerProperty(String type) {
        type = type.toLowerCase();
        return SwaggerUtil.getPropMap().get(type);
    }

    private static boolean isMap(String type) {
        type = type.toLowerCase();
        return type.startsWith("map");
    }

    private static boolean isIterable(String type) {
        type = type.toLowerCase();
        return type.startsWith("list") || type.startsWith("set");
    }

    /**
     * 获取非基本类型的T<br>
     * new String[] { "A<List<C1>>", "A<C2>", "A<B<String,<String,List<C4>>>>" }
     *
     * @param type 类型
     * @return C1, C2, C3, C4
     */
    public static String getRef(String type) {
        try {
            String m = type.substring(type.lastIndexOf("«") + 1, type.indexOf("»"));
            String[] cc = m.split(",");
            for (String c : cc) {
                if (!SwaggerUtil.isBaseType(c)) {
                    return c;
                }
            }
            return type;
        } catch (Exception ignored) {

        }
        return "!!Unknown T!!";
    }

    /**
     * 获取对象类型，主要是剥离第一层<>
     *
     * @param type JsonResult<Map<Operator, List<Map<String, Customer>>>>
     * @return Map<Operator, List < Map < String, Customer>>>
     */
    public static String getRealType(String type) {
        try {
            return type.substring(type.indexOf("«") + 1, type.lastIndexOf("»"));
        } catch (Exception ignored) {

        }
        return type;
    }

    /**
     * 判断是否存在非基本类型<参照类型>
     *
     * @param type 类型
     * @return boolean
     */
    private static boolean hasRef(String type) {
        if (type.indexOf("»") > 0) {
            try {
                String m = type.substring(type.lastIndexOf("«") + 1, type.indexOf("»"));
                String[] cc = m.split(",");
                for (String c : cc) {
                    if (!SwaggerUtil.isBaseType(c)) {
                        return true;
                    }
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        } else {
            return !SwaggerUtil.isBaseType(type);
        }
    }

    /**
     * 递归处理泛型类型 <br>
     * JsonResult<Map<Map<Long, Operator>, List<Map<String, Customer>>>>
     *
     * @param dataProp    数据支撑
     * @param type        类型
     * @param definitions 定义
     * @return {@link Property}
     */
    public static Property getNewProp(Property dataProp, String type, Map<String, Model> definitions) {
        Property newProp = null;
        Model model = definitions.get(type);
        Map<String, Property> props = null;
        if (null != model) {
            props = model.getProperties();
        }
        if (null == props) {
            props = new HashMap<>(2);
        }
        String realType = SwaggerUtil.getRealType(type);
        if (SwaggerUtil.isMap(type)) {
            String[] realTypes = SwaggerUtil.splitByComma(realType);

            Map<PropertyId, Object> argsK = new HashMap<>(2);
            argsK.put(PropertyId.DESCRIPTION, "Map的键");
            argsK.put(PropertyId.TYPE, realTypes[0].toLowerCase());
            AbstractProperty prop0 = SwaggerUtil.getSwaggerProperty(realTypes[0]);
            Property propK = PropertyBuilder.build(null == prop0 ? "object" : prop0.getType(),
                    null == prop0 ? null : prop0.getFormat(), argsK);
            propK.setName("key");

            Map<PropertyId, Object> argsV = new HashMap<>(2);
            argsV.put(PropertyId.DESCRIPTION, "Map的值");
            argsV.put(PropertyId.TYPE, realTypes[1].toLowerCase());
            AbstractProperty prop1 = SwaggerUtil.getSwaggerProperty(realTypes[1]);
            Property propV = PropertyBuilder.build(null == prop1 ? "object" : prop1.getType(),
                    null == prop1 ? null : prop1.getFormat(), argsV);
            propV.setName("value");

            if (!realType.equals(type)) {
                propK = SwaggerUtil.getNewProp(propK, realTypes[0], definitions);
                propV = SwaggerUtil.getNewProp(propV, realTypes[1], definitions);
            }

            props.put(propK.getName(), propK);
            props.put(propV.getName(), propV);

            newProp = new RefProperty();
            BeanUtils.copyProperties(dataProp, newProp);
            ((RefProperty) newProp).set$ref(type);
        } else if (SwaggerUtil.isIterable(type)) {
            String ref = SwaggerUtil.getRealType(type);
            newProp = new ArrayRefProperty();
            BeanUtils.copyProperties(dataProp, newProp);
            ((ArrayRefProperty) newProp).set$ref(ref);
            ((ArrayRefProperty) newProp).setType(ArrayRefProperty.TYPE);
            if (!realType.equals(type)) {
                SwaggerUtil.getNewProp(dataProp, realType, definitions);
            }
        } else if (SwaggerUtil.isBaseType(type)) {
            Map<PropertyId, Object> args = new HashMap<>(2);
            args.put(PropertyId.DESCRIPTION, dataProp.getDescription());
            args.put(PropertyId.TYPE, type.toLowerCase());
            AbstractProperty prop = SwaggerUtil.getSwaggerProperty(type);
            newProp = PropertyBuilder.build(prop.getType(), prop.getFormat(), args);
            newProp.setName(dataProp.getName());
        } else if (SwaggerUtil.hasRef(type)) {
            newProp = new RefProperty();
            BeanUtils.copyProperties(dataProp, newProp);
            ((RefProperty) newProp).set$ref(type);
        }
        if (null != model) {
            model.setProperties(props);
        }

        return newProp;
    }

    private static String[] splitByComma(String str) {
        int index = 0;
        int has = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ("«".equals(c + "")) {
                has++;
            }
            if ("»".equals(c + "")) {
                has--;
            }
            if (",".equals(c + "") && has == 0) {
                index = i;
            }
        }

        String[] arr = new String[2];
        arr[0] = str.substring(0, index);
        arr[1] = str.substring(index + 1);
        return arr;
    }

    public static void main(String[] args) {
        String[] ss = new String[]{"A«List«C1»»", "A«C2»", "A«B«String,«String,List«C4»»»»"};
        for (String s : ss) {
            String c = SwaggerUtil.getRealType(s);
            System.out.println(c);
        }

        String[] s2 = new String[]{"A,B«List«C1»»", "Map«A,B»,C«List«D»»",
                "Map«Map«A,B»,C«List«D»»,Map«A,B»,C«List«D»»»,C«List«D»»"};
        for (String s : s2) {
            String[] arr = SwaggerUtil.splitByComma(s);
            System.out.println(arr[0]);
            System.out.println(arr[1]);
        }
    }
}
