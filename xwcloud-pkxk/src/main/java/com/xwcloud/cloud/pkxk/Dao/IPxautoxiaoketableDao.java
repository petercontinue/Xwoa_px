package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxautoxiaoketable;
import org.apache.ibatis.annotations.Param;
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
 * @since 2020-11-23
 */
@Repository
public interface IPxautoxiaoketableDao extends BaseMapper<Pxautoxiaoketable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buxiID", property = "buxiid"),
            @Result(column = "classID", property = "classid"),
            @Result(column = "keshiNum", property = "keshinum"),
            @Result(column = "teaIDs", property = "teaids"),
            @Result(column = "teaNames", property = "teanames"),
            @Result(column = "state", property = "state"),
            @Result(column = "qiyeID", property = "qiyeid")
    })
    @Select("<script>" +
            "SELECT * from  pxautoxiaoketable"
            + "</script>")
    List<Pxautoxiaoketable> getAllList();


    /**
     * 条件查询
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxautoxiaoketable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxautoxiaoketable> selectAutoxk(@Param("ew") QueryWrapper queryWrapper);
}