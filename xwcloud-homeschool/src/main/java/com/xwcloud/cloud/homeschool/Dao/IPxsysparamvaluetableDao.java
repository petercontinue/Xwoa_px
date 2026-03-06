package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxsysparamvaluetable;
import org.apache.ibatis.annotations.Param;
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
 * @since 2021-08-07
 */
@Repository
public interface IPxsysparamvaluetableDao extends BaseMapper<Pxsysparamvaluetable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "sysparamTypeID", property = "sysparamTypeID"),
                @Result(column = "modifyValue", property = "modifyValue"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxsysparamvaluetable"
            + "</script>")
    List<Pxsysparamvaluetable> getAllList();

    @Select("<script>" +
            "SELECT * from  pxsysparamvaluetable where qiyeID=#{qiyeID} and sysparamTypeID=#{sysparamTypeID} "
            + "</script>")
    Pxsysparamvaluetable getsysvalue(Long qiyeID,Long sysparamTypeID);

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxsysparamvaluetable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxsysparamvaluetable> selectsysvalue(@Param("ew") QueryWrapper queryWrapper);
}