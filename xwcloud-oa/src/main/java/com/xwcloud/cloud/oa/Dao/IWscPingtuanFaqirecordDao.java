package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscPingtuanFaqirecord;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-25
 */
@Repository
public interface IWscPingtuanFaqirecordDao extends BaseMapper<WscPingtuanFaqirecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "pingtuanGoodsID", property = "pingtuanGoodsID"),
                @Result(column = "goodsshuxinglistpriceID", property = "goodsshuxinglistpriceID"),
                @Result(column = "pingtuanFaqiRenWxUserID", property = "pingtuanFaqiRenWxUserID"),
                @Result(column = "faqiRenOrderID", property = "faqiRenOrderID"),
                @Result(column = "state", property = "state"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_pingtuan_faqirecord"
            + "</script>")
    List<WscPingtuanFaqirecord> getAllList();
}