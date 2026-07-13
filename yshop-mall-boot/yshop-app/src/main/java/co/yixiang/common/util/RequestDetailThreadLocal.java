package co.yixiang.common.util;

import co.yixiang.common.bean.RequestDetail;


/**
 * 记录请求详情信息到当前线程中
 * @author hupeng
 * @date 2020-04-30
 */
public class RequestDetailThreadLocal {

    private static ThreadLocal<RequestDetail> threadLocal = new ThreadLocal<>();

    /**
     * 设置请求信息到当前线程中
     *
     * @param requestDetail
     */
    public static void setRequestDetail(RequestDetail requestDetail) {
        threadLocal.set(requestDetail);
    }

    /**
     * 从当前线程中获取请求信息
     */
    public static RequestDetail getRequestDetail() {
        return threadLocal.get();
    }

    /**
     * 销毁
     */
    public static void remove() {
        threadLocal.remove();
    }

}
