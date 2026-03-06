package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscTuikeBuy;
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
 * @since 2021-08-19
 */
@Repository
public interface IWscTuikeBuyDao extends BaseMapper<WscTuikeBuy> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "wsc_user_id", property = "wscUserId"),
                @Result(column = "realName", property = "realName"),
                @Result(column = "phone", property = "phone"),
                @Result(column = "oldTuikeLevelID", property = "oldTuikeLevelID"),
                @Result(column = "buyTuikeLevelID", property = "buyTuikeLevelID"),
                @Result(column = "paymoney", property = "paymoney"),
                @Result(column = "buyTime", property = "buyTime"),
                @Result(column = "shuoming", property = "shuoming"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_tuike_buy"
            + "</script>")
    List<WscTuikeBuy> getAllList();
}