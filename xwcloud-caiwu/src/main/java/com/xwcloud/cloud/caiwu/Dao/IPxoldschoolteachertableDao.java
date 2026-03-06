package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxoldschoolteachertable;
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
 * @since 2020-12-02
 */
public interface IPxoldschoolteachertableDao extends BaseMapper<Pxoldschoolteachertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "oldSchoolTeacherID", property = "oldSchoolTeacherID"),
            @Result(column = "oldSchoolTeacherName", property = "oldSchoolTeacherName"),
            @Result(column = "oldSchoolTeacherTel", property = "oldSchoolTeacherTel"),
            @Result(column = "oldSchoolID", property = "oldSchoolID"),
    })
    @Select("<script>" +
            "SELECT * from  pxoldschoolteachertable"
            + "</script>")
    List<Pxoldschoolteachertable> getAllList();

    @Select("<script>" +
            "SELECT campus.campusName,stu.zidingyiStuID,stu.id,stu.stuName," +
            "grade.stuGradeName,oldteacher.oldSchoolTeacherName " +
            "FROM pxstutable stu \n" +
            "LEFT JOIN pxcampustable campus ON stu.campusID = campus.id \n" +
            "LEFT JOIN pxstugradetable grade ON stu.stuGradeID = grade.id\n" +
            "LEFT JOIN pxoldschoolteachertable oldteacher ON stu.oldSchoolTeacher = oldteacher.oldSchoolTeacherID "+
            "WHERE 1=1 "+
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String,String>> getOldteacherPage(Page page, @Param("ew")Wrapper wrapper);

    @Select("<script>" +
            "SELECT oldSchoolTeacherName,oldteacherID,ROUND((aaa.stuNum/num)*100,2) AS bili FROM (\n" +
            "                        SELECT oldSchoolTeacherName,oldteacherID,count(*) as stuNum FROM (SELECT campus.campusName,stu.zidingyiStuID,stu.stuName,grade.stuGradeName,\n" +
            "            oldteacher.oldSchoolTeacherName,oldteacher.oldSchoolTeacherID as oldteacherID FROM pxstutable stu \n" +
            "            LEFT JOIN pxcampustable campus ON stu.campusID = campus.id \n" +
            "            LEFT JOIN pxstugradetable grade ON stu.stuGradeID = grade.id\n" +
            "            LEFT JOIN pxoldschoolteachertable oldteacher ON stu.oldSchoolTeacher = oldteacher.oldSchoolTeacherID \n" +
            "            WHERE 1=1\n" +
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "\t\t\t\t\t\t) as tmp GROUP BY oldteacherID) as aaa \n" +
            "                        LEFT JOIN (SELECT COUNT(*) as num FROM (SELECT campus.campusName,stu.zidingyiStuID,stu.stuName,grade.stuGradeName,\n" +
            "            oldteacher.oldSchoolTeacherName,oldteacher.oldSchoolTeacherID as oldteacherID FROM pxstutable stu \n" +
            "            LEFT JOIN pxcampustable campus ON stu.campusID = campus.id \n" +
            "            LEFT JOIN pxstugradetable grade ON stu.stuGradeID = grade.id\n" +
            "            LEFT JOIN pxoldschoolteachertable oldteacher ON stu.oldSchoolTeacher = oldteacher.oldSchoolTeacherID \n" +
            "            WHERE 1=1\n" +
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "\t\t\t\t\t\t) as tmp) AS bbb ON 1=1"
            + "</script>")
    List<HashMap<String,String>> getOldteacherBili(@Param("ew")Wrapper wrapper);
}