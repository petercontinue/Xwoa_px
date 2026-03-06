package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxclassroomtable;
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
 * @since 2020-11-07
 */
@Repository
public interface IPxclassroomtableDao extends BaseMapper<Pxclassroomtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "classRoomName", property = "classRoomName"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "recordInStaffID", property = "recordInStaffID"),
            @Result(column = "recordInTime", property = "recordInTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "ischongtu", property = "ischongtu"),
    })
    @Select("<script>" +
            "SELECT * from  pxclassroomtable"
            + "</script>")
    List<Pxclassroomtable> getAllList();

    @Select("<script>" +
            "SELECT a.id, CONCAT(b.campusName,'_',a.classRoomName) classRoomName\n" +
            "FROM pxclassroomtable a\n" +
            "JOIN pxcampustable b ON a.campusID=b.id\n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<Pxclassroomtable> getClassRoom(@Param("ew") QueryWrapper<Pxclassroomtable> wrapper);
}