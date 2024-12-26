//package co.yixiang.config;
//
///**
// * @author ：LionCity
// * @date ：Created in 2020-12-21 13:38
// * @description：
// * @modified By：
// * @version:
// */
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import co.yixiang.utils.StringUtils;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//
//@Component
//@Order(-9999)
//public class CorsFilter extends HttpFilter {
//
//    /**
//     *
//     */
//    private static final long serialVersionUID = -8387103310559517243L;
//
//    @Override
//    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//
//        String origin = req.getHeader(HttpHeaders.ORIGIN);
//
//        if (!StringUtils.isEmpty(origin)){
//            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
//            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, x-requested-with, Content-Type, Accept, Authorization");
//            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, OPTIONS, DELETE");
//            res.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Cache-Control, Content-Language, Content-Type, Expires, Last-Modified, Pragma");
//            res.addHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "60");
//        }
//        super.doFilter(req, res, chain);
//    }
//}
