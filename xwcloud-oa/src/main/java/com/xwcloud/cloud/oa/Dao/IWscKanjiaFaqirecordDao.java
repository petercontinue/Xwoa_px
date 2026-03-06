package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscKanjiaFaqirecord;
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
public interface IWscKanjiaFaqirecordDao extends BaseMapper<WscKanjiaFaqirecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "kanjiaGoodsID", property = "kanjiaGoodsID"),
                @Result(column = "goodsshuxinglistpriceID", property = "goodsshuxinglistpriceID"),
                @Result(column = "kanjiaFaqiRenWxUserID", property = "kanjiaFaqiRenWxUserID"),
                @Result(column = "minMoney", property = "minMoney"),
                @Result(column = "startMoney", property = "startMoney"),
                @Result(column = "currentMoney", property = "currentMoney"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "state", property = "state"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_kanjia_faqirecord"
            + "</script>")
    List<WscKanjiaFaqirecord> getAllList();
}