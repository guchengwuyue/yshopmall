package co.yixiang.common.util;



import co.yixiang.modules.user.vo.CityVo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName 树形工具类
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/22
 **/
public class CityTreeUtil {
    /**
     * 获得指定节点下所有归档
     *
     * @param list
     * @param parentId
     * @return
     */
    public static List<CityVo> list2TreeConverter(List<CityVo> list, int parentId) {
        List<CityVo> returnList = new ArrayList<>();

        for (CityVo res : list) {
            //判断对象是否为根节点

            if (res.getPid() == parentId) {
                //该节点为根节点,开始递归

                //通过递归为节点设置childList
                recursionFn(list, res);

                returnList.add(res);
            }
        }

        return returnList;
    }

    /**
     * 递归列表
     * 通过递归,给指定t节点设置childList
     *
     * @param list
     * @param t
     */
    public static void recursionFn(List<CityVo> list, CityVo t) {
        //只能获取当前t节点的子节点集,并不是所有子节点集
        List<CityVo> childsList = getChildList(list, t);

        //设置他的子集对象集
        t.setC(childsList);

        //迭代子集对象集

        //遍历完,则退出递归
        for (CityVo nextChild : childsList) {

            //判断子集对象是否还有子节点
            if (!CollectionUtils.isEmpty(childsList)) {
                //有下一个子节点,继续递归
                recursionFn(list, nextChild);
            }
        }
    }

    /**
     * 获得指定节点下的所有子节点
     *
     * @param list
     * @param t
     * @return
     */
    public static List<CityVo> getChildList(List<CityVo> list, CityVo t) {
        List<CityVo> childsList = new ArrayList<>();
        //遍历集合元素,如果元素的Parentid==指定元素的id,则说明是该元素的子节点
        for (CityVo t1 : list) {
            if (t1.getPid().equals(t.getV())) {
                childsList.add(t1);
            }
        }

        return childsList;
    }


}
