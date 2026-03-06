package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.PxevaluationtableVo;
import com.xwcloud.cloud.model.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxevaluationtableDao extends BaseMapper<Pxevaluationtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "note", property = "note"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengID", property = "pxkechengtable", one = @One(select = "getPxkechengtable", fetchType = FetchType.EAGER)),
            @Result(column = "classID", property = "classID"),
            @Result(column = "classID", property = "pxclasstable", one = @One(select = "getPxclasstable", fetchType = FetchType.EAGER)),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "keshiTeachTabID", property = "keshiTeachTabID"),
            @Result(column = "keshiTeachTabID", property = "pxkeshiteachertable", one = @One(select = "getPxkeshiteachertable", fetchType = FetchType.EAGER)),
            @Result(column = "stuid", property = "stuid"),
            @Result(column = "stuid", property = "pxstutable", one = @One(select = "getPxstutable", fetchType = FetchType.EAGER)),
            @Result(column = "teacherid", property = "teacherid"),
            @Result(column = "teacherid", property = "pxstafftable", one = @One(select = "getPxstafftable", fetchType = FetchType.EAGER)),
            @Result(column = "addtime", property = "addtime"),
            @Result(column = "images", property = "images"),
            @Result(column = "pjmp3Url", property = "pjmp3Url"),
            @Result(column = "pjvideoUrl", property = "pjvideoUrl"),
            @Result(column = "type", property = "type"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxevaluationtable"
            + "</script>")
    List<PxevaluationtableVo> getAllList();

    @Select("<script>" +
            "SELECT * from  pxevaluationtable" +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    @ResultMap("BaseResultMap")
    Page<PxevaluationtableVo> getPxevaluationtablePage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
//            "select evalua.*,campus.id as campusID,campus.campusName as campusName,stu.zidingyiStuID as zidingyiStuID," +
//            "stu.stuName as stuName,class.className as className,kecheng.kechengName as kechengName,teacher.staffName as staffName " +
//            " from pxevaluationtable as evalua " +
//            "left join pxkechengtable as kecheng on evalua.KechengID=kecheng.id " +
//            "LEFT JOIN pxclasstable as class on evalua.classID=class.id " +
//            "LEFT JOIN pxkeshiteachertable as keshi on evalua.keshiTeachTabID = keshi.id " +
//            "LEFT JOIN pxstafftable as teacher ON evalua.TeacherID = teacher.id " +
//            "LEFT JOIN pxstutable stu ON evalua.stuid=stu.id " +
//            "LEFT JOIN pxcampustable campus ON class.campusID = campus.id" +
//            " WHERE campus.isOpen !=2 "+
            "SELECT\n" +
            "\ta.id pkid,a.*,\n" +
            "\tc.*,\n" +
            "\tf.className,\n" +
            "\th.kechengName,\n" +
            "\tg.id xkcampusID," +

            "\tg.campusName,\n" +
            "\tc.id stuID,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\td.id \n" +
            "\tFROM\n" +
            "\t\tpxevaluationtable d \n" +
            "\tWHERE\n" +
            "\t\ta.haveClassDate = d.haveClassDate \n" +
            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
            "\t\tAND a.classID = d.classID \n" +
            "\t\tAND c.id = d.Stuid \n" +
            "\t) pjID, \n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tnote \n" +
            "\tFROM\n" +
            "\t\tpxevaluationtable d \n" +
            "\tWHERE\n" +
            "\t\ta.haveClassDate = d.haveClassDate \n" +
            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
            "\t\tAND a.classID = d.classID \n" +
            "\t\tAND c.id = d.Stuid \n" +
            "\t) note,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\taddTime \n" +
            "\tFROM\n" +
            "\t\tpxevaluationtable d \n" +
            "\tWHERE\n" +
            "\t\ta.haveClassDate = d.haveClassDate \n" +
            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
            "\t\tAND a.classID = d.classID \n" +
            "\t\tAND c.id = d.Stuid \n" +
            "\t) noteaddTime, \n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\td.images \n" +
            "\tFROM\n" +
            "\t\tpxevaluationtable d \n" +
            "\tWHERE\n" +
            "\t\ta.haveClassDate = d.haveClassDate \n" +
            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
            "\t\tAND a.classID = d.classID \n" +
            "\t\tAND c.id = d.Stuid \n" +
            "\t) pjimages, \n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\td.pjmp3Url \n" +
            "\tFROM\n" +
            "\t\tpxevaluationtable d \n" +
            "\tWHERE\n" +
            "\t\ta.haveClassDate = d.haveClassDate \n" +
            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
            "\t\tAND a.classID = d.classID \n" +
            "\t\tAND c.id = d.Stuid \n" +
            "\t) pjmp3Url, \n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\td.pjvideoUrl \n" +
            "\tFROM\n" +
            "\t\tpxevaluationtable d \n" +
            "\tWHERE\n" +
            "\t\ta.haveClassDate = d.haveClassDate \n" +
            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
            "\t\tAND a.classID = d.classID \n" +
            "\t\tAND c.id = d.Stuid \n" +
            "\t) pjvideoUrl, \n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tteacherid \n" +
            "\tFROM\n" +
            "\t\tpxevaluationtable d \n" +
            "\tWHERE\n" +
            "\t\ta.haveClassDate = d.haveClassDate \n" +
            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
            "\t\tAND a.classID = d.classID \n" +
            "\t\tAND c.id = d.Stuid \n" +
            "\t) pjteacherid \n" +
            "FROM\n" +
            "\tpxpaiketable a\n" +
            "\tLEFT JOIN pxxuanketable b ON a.id = b.paikeID\n" +
            "\tLEFT JOIN pxstutable c ON b.stuID = c.id\n" +
            "\tLEFT JOIN pxclasstable f ON a.classID = f.id\n" +
            "\tLEFT JOIN pxkechengtable h ON a.kechengID = h.id\n" +
            "\tLEFT JOIN pxcampustable g ON f.campusID = g.id \n" +
            "where g.isOpen!=2 and c.id is not null and a.haveClassDate &gt; (SELECT DATE_ADD(NOW(), INTERVAL -1 MONTH)) and ((SELECT COUNT(e.id) from pxkeshistutable e where a.haveClassDate=e.haveClassDate and a.startLessonDateTime=e.startLessonDateTime and a.endLessonDateTime=e.endLessonDateTime and b.stuID=e.stuID)) &gt; 0" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getPxevaluationtableJoinPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "select evalua.*,campus.id as campusID,campus.campusName as campusName,stu.zidingyiStuID as zidingyiStuID," +
            "stu.stuName as stuName,class.className as className,kecheng.kechengName as kechengName," +
            "teacher.staffName as staffName  from pxevaluationtable as evalua " +
            "left join pxkechengtable as kecheng on evalua.KechengID=kecheng.id " +
            "LEFT JOIN pxclasstable as class on evalua.classID=class.id " +
            "LEFT JOIN pxkeshiteachertable as keshi on evalua.keshiTeachTabID = keshi.id " +
            "LEFT JOIN pxstafftable as teacher ON evalua.TeacherID = teacher.id " +
            "LEFT JOIN pxstutable stu ON evalua.stuid=stu.id " +
            "LEFT JOIN pxcampustable as campus ON stu.campusID = campus.id " +
            " WHERE campus.isOpen !=2" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxevaluationtableVo> getPxevaluationtableJoinList(@Param("ew") Wrapper wrapper);

    // 查询课程表
    @Select("SELECT * FROM pxkechengtable where id = #{id}")
    Pxkechengtable getPxkechengtable(@Param("id") String id);

    // 查询班级表
    @Select("SELECT * FROM pxclasstable where id = #{id}")
    Pxclasstable getPxclasstable(@Param("id") String id);

    // 查询教师上课时间表
    @Select("SELECT * FROM pxkeshiteachertable where id = #{id}")
    Pxclasstable getPxkeshiteachertable(@Param("id") String id);

    // 查询教师上课时间表
    @Select("SELECT * FROM pxstafftable where id = #{id}")
    Pxclasstable getPxstafftable(@Param("id") String id);

    // 查询学生表
    @Select("SELECT * FROM pxstutable where id = #{id}")
    Pxclasstable getPxstutable(@Param("id") String id);

    // 查询校区表
    @Select("<script>" +
            "SELECT * FROM pxcampustable " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxcampustable> getCampusList(@Param("ew") Wrapper wrapper);

    // 查询班级表
    @Select("<script>" +
            "SELECT * FROM pxclasstable " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxclasstable> getClassList(@Param("ew") Wrapper wrapper);

    // 查询学生表
    @Select("<script>" +
            "SELECT stu.* FROM pxkeshistutable keshi " +
            "LEFT JOIN pxstutable stu ON keshi.stuID = stu.id" +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxstutable> getStuList(@Param("ew") Wrapper wrapper);

    // 查询时间段表
    @Select("<script>" +
            "select DISTINCT classID,campusID," +
            "CONCAT(DATE_FORMAT(haveClassDate,'%Y-%m-%d'),' ',startLessonDateTime,'~',endLessonDateTime) as Period, " +
            "haveClassDate,startLessonDateTime,endLessonDateTime " +
            "FROM pxkeshiteachertable" +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getPeriodList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT staff.* FROM pxkeshiteachertable laoshikeshi " +
            "LEFT JOIN pxstafftable staff ON laoshikeshi.teacherID=staff.id" +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxstafftable> getStaffList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxkeshiteachertable  " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    Pxkeshiteachertable getTeacherkeshi(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxstafftable  " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getPublicStaffList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxclassroomtable  " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getClassRoomList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxstutable  " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getPublicStuList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxstaffposttable  " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getPublicStaffPostList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxsubjecttable  " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getPublicKemuList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxstugradetable  " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getStugradeList(@Param("ew") Wrapper wrapper);

}