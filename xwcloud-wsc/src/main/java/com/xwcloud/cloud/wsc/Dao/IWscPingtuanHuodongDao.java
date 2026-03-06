package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscPingtuanHuodong;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-03
 */
@Repository
public interface IWscPingtuanHuodongDao extends BaseMapper<WscPingtuanHuodong> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "huodongTitle", property = "huodongTitle"),
            @Result(column = "huodongImg", property = "huodongImg"),
            @Result(column = "huodongshuoming", property = "huodongshuoming"),
            @Result(column = "liulangTimes", property = "liulangTimes"),
            @Result(column = "fenxiangTimes", property = "fenxiangTimes"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_pingtuan_huodong"
            + "</script>")
    List<WscPingtuanHuodong> getAllList();
}