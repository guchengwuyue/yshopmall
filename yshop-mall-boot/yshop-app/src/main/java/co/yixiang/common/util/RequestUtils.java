package co.yixiang.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName 获取Request工具类
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/26
 **/
public class RequestUtils {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes ra= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return ra.getRequest();
    }

}
