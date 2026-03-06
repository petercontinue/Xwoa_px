package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstugradetable;
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
 * @since 2021-03-03
 */
@Repository
public interface IPxstugradetableDao extends BaseMapper<Pxstugradetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuGradeName", property = "stuGradeName"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstugradetable"
            + "</script>")
    List<Pxstugradetable> getAllList();

    @Select("<script>" +
            "SELECT * from  pxstugradetable where qiyeID=#{qiyeID} limit 1"
            + "</script>")
    Pxstugradetable getOne(Long qiyeID);

    @Select("<script>" +
            "SELECT * from  Pxstugradetable where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxstugradetable> selectstuGrade(@Param("ew") QueryWrapper queryWrapper);
}