package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxoldschooltable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-01
 */
public interface IPxoldschooltableDao extends BaseMapper<Pxoldschooltable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "oldSchoolID", property = "oldSchoolID"),
            @Result(column = "oldSchoolName", property = "oldSchoolName"),
    })
    @Select("<script>" +
            "SELECT * from  pxoldschooltable"
            + "</script>")
    List<Pxoldschooltable> getAllList();

    @Select("<script>" +
            "SELECT campus.campusName," +
            "stu.id stuID," +
            "stu.zidingyiStuID," +
            "stu.stuName," +
            "grade.stuGradeName," +
            "oldschool.oldSchoolName " +
            "FROM pxstutable stu " +
            "LEFT JOIN pxoldschooltable oldschool ON stu.oldSchool= oldschool.oldSchoolID " +
            "LEFT JOIN pxcampustable campus ON stu.campusID = campus.id " +
            "LEFT JOIN pxstugradetable grade ON stu.stuGradeID = grade.id "+
            "WHERE 1=1 "+
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String,String>> getOldschoolDetail(Page page,@Param("ew")Wrapper wrapper);

    @Select("<script>" +
            "SELECT aaa.oldschoolName,aaa.oldSchoolID,ROUND((aaa.stuNum/bbb.num)*100,2) AS bili \n" +
            "\t\t\t\t\t\tFROM (\n" +
            "            SELECT oldschoolName,oldSchoolID,count(*) as stuNum FROM (\n" +
            "\t\t\t\t\t\tSELECT campus.campusName,stu.zidingyiStuID,stu.stuName,grade.stuGradeName,oldschool.oldSchoolName, \n" +
            "            oldschool.oldSchoolID\n" +
            "            FROM pxstutable stu \n" +
            "            LEFT JOIN pxoldschooltable oldschool ON stu.oldSchool= oldschool.oldSchoolID \n" +
            "            LEFT JOIN pxcampustable campus ON stu.campusID = campus.id \n" +
            "            LEFT JOIN pxstugradetable grade ON stu.stuGradeID = grade.id \n" +
            "            WHERE 1=1 \n" +
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "            ) as tmp GROUP BY tmp.oldSchoolID) as aaa\n" +
            "            LEFT JOIN (SELECT COUNT(*) as num FROM (SELECT campus.campusName,stu.zidingyiStuID,stu.stuName,grade.stuGradeName,oldschool.oldSchoolName, \n" +
            "            oldschool.oldSchoolID\n" +
            "            FROM pxstutable stu \n" +
            "            LEFT JOIN pxoldschooltable oldschool ON stu.oldSchool= oldschool.oldSchoolID \n" +
            "            LEFT JOIN pxcampustable campus ON stu.campusID = campus.id \n" +
            "            LEFT JOIN pxstugradetable grade ON stu.stuGradeID = grade.id \n" +
            "            WHERE 1=1 \n" +
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "\t\t\t\t\t\t) tmp) AS bbb ON 1=1"
            + "</script>")
    List<HashMap<String,String>> getOldschoolBili(@Param("ew")Wrapper wrapper);
}