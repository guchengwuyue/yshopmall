/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.product.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.product.domain.YxStoreProductReply;
import co.yixiang.modules.product.service.dto.YxStoreProductReplyDto;
import co.yixiang.modules.product.service.dto.YxStoreProductReplyQueryCriteria;
import co.yixiang.modules.product.vo.ReplyCountVo;
import co.yixiang.modules.product.vo.YxStoreProductReplyQueryVo;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-12
*/
public interface YxStoreProductReplyService  extends BaseService<YxStoreProductReply>{

    /**
     * 评价数据
     * @param productId 商品id
     * @return ReplyCountVO
     */
    ReplyCountVo getReplyCount(long productId);

    /**
     * 处理评价
     * @param replyQueryVo replyQueryVo
     * @return YxStoreProductReplyQueryVo
     */
    YxStoreProductReplyQueryVo handleReply(YxStoreProductReplyQueryVo replyQueryVo);

    /**
     * 获取单条评价
     * @param productId 商品di
     * @return YxStoreProductReplyQueryVo
     */
    YxStoreProductReplyQueryVo getReply(long productId);

    /**
     * 获取评价列表
     * @param productId 商品id
     * @param type 0-全部 1-好评 2-中评 3-差评
     * @param page page
     * @param limit limit
     * @return list
     */
    List<YxStoreProductReplyQueryVo> getReplyList(long productId,int type,int page, int limit);

    Long getInfoCount(Integer oid, String unique);

    Long productReplyCount(long productId);

    Long replyCount(String unique);

    String replyPer(long productId);


    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxStoreProductReplyQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxStoreProductReplyDto>
    */
    List<YxStoreProductReply> queryAll(YxStoreProductReplyQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxStoreProductReplyDto> all, HttpServletResponse response) throws IOException;
}
