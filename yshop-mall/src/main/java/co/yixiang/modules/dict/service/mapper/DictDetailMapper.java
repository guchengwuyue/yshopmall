/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.dict.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.dict.domain.DictDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author hupeng
* @date 2020-05-14
*/
@Repository
public interface DictDetailMapper extends CoreMapper<DictDetail> {

    @Select("<script>SELECT d.* from dict_detail d LEFT JOIN dict t on d.dict_id = t.id where d.is_del=0 <if test = \"label !=null\" > and d.label LIKE concat('%', #{label}, '%') </if> <if test = \"dictName != ''||dictName !=null\" > AND t.name = #{dictName} order by d.sort asc</if></script>")
    List<DictDetail> selectDictDetailList(@Param("label") String label,@Param("dictName") String dictName);
}
