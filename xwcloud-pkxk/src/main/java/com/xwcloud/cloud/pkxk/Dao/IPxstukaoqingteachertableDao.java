package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxstukaoqingteachertable;
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
 * @since 2020-11-23
 */
@Repository
public interface IPxstukaoqingteachertableDao extends BaseMapper<Pxstukaoqingteachertable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stukaoqingTabID", property = "stukaoqingTabID"),
            @Result(column = "teacherID", property = "teacherID"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  pxstukaoqingteachertable"
        + "</script>")
List<Pxstukaoqingteachertable> getAllList();

    /**
     * 条件查询
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxstukaoqingteachertable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxstukaoqingteachertable> selectstukaoteacherqing(@Param("ew") QueryWrapper queryWrapper);
}