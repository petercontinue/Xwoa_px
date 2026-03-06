package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxczhuodongtable;
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
 * @since 2021-05-10
 */
@Repository
public interface IPxczhuodongtableDao extends BaseMapper<Pxczhuodongtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "zongmoney", property = "zongmoney"),
            @Result(column = "huodongmoney", property = "huodongmoney"),
            @Result(column = "type", property = "type"),
            @Result(column = "Sdate", property = "sdate"),
            @Result(column = "Edate", property = "edate"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxczhuodongtable"
            + "</script>")
    List<Pxczhuodongtable> getAllList();
}