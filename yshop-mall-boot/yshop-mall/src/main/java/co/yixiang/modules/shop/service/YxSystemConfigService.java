/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.shop.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.domain.YxSystemConfig;
import co.yixiang.modules.shop.service.dto.YxSystemConfigDto;
import co.yixiang.modules.shop.service.dto.YxSystemConfigQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-12
*/
public interface YxSystemConfigService  extends BaseService<YxSystemConfig>{

    /**
     * 获取配置值
     * @param name 配置名
     * @return string
     */
    String getData(String name);

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxSystemConfigQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxSystemConfigDto>
    */
    List<YxSystemConfig> queryAll(YxSystemConfigQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxSystemConfigDto> all, HttpServletResponse response) throws IOException;

    YxSystemConfig findByKey(String store_brokerage_statu);
}
