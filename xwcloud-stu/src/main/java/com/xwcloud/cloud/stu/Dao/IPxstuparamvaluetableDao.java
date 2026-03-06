package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstuparamvaluetable;

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
public interface IPxstuparamvaluetableDao extends BaseMapper<Pxstuparamvaluetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "stuParamTypeID", property = "stuParamTypeID"),
            @Result(column = "paramValue", property = "paramValue"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstuparamvaluetable"
            + "</script>")
    List<Pxstuparamvaluetable> getAllList();

    @Select("<script>" +
            "SELECT * from  pxstuparamvaluetable where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxstuparamvaluetable> selectstuparamvalue(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" + "select * from pxstuparamvaluetable where stuParamTypeID=#{tid} and stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxstuparamvaluetable> getstuParam(Long tid,Long stuID, Long qiyeID);
}