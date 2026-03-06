package com.xwcloud.cloud.homeschool.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxyuekestufaqitable;
import com.xwcloud.cloud.model.Vo.PxyuekestufaqitableVo;
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
 * @since 2020-11-04
 */
public interface IPxyuekestufaqitableDao extends BaseMapper<Pxyuekestufaqitable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "buxikechengID", property = "buxikechengID"),
            @Result(column = "buxiStyle", property = "buxiStyle"),
            @Result(column = "teacherID", property = "teacherID"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "haveLessonStartTime", property = "haveLessonStartTime"),
            @Result(column = "haveLessonEndTime", property = "haveLessonEndTime"),
            @Result(column = "faqiyuekeStuLiuyan", property = "faqiyuekeStuLiuyan"),
            @Result(column = "yuekeShenheState", property = "yuekeShenheState"),
            @Result(column = "yuekeShenheDafu", property = "yuekeShenheDafu"),
            @Result(column = "faqiYuekeStuID", property = "faqiYuekeStuID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "shenheStaffID", property = "shenheStaffID"),
            @Result(column = "shenheDatetime", property = "shenheDatetime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyuekestufaqitable"
            + "</script>")
    List<Pxyuekestufaqitable> getAllList();

    @Select("<script>" +
            "SELECT stuyueke.*," +
            "kecheng.kechengName as kechengName, " +
            "stu.stuName as stuName, " +
            "staff.staffName AS banzhurenName, " +
            "teacher.staffName as teacherName " +
            "from  pxyuekestufaqitable as stuyueke " +
            "LEFT JOIN pxkechengtable as kecheng ON stuyueke.kechengID=kecheng.id " +
            "LEFT JOIN pxstutable as stu ON stuyueke.faqiYuekeStuID = stu.id " +
            "LEFT JOIN pxstafftable AS staff ON stu.banzhurenTeacherID=staff.id " +
            "LEFT JOIN pxstafftable as teacher ON stuyueke.teacherID = teacher.id"+
            " WHERE 1=1"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxyuekestufaqitableVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT stuyueke.*," +
            "kecheng.kechengName as kechengName, " +
            "stu.stuName as stuName, " +
            "staff.staffName AS banzhurenName, " +
            "teacher.staffName as teacherName " +
            "from  pxyuekestufaqitable as stuyueke " +
            "LEFT JOIN pxkechengtable as kecheng ON stuyueke.kechengID=kecheng.id " +
            "LEFT JOIN pxstutable as stu ON stuyueke.faqiYuekeStuID = stu.id " +
            "LEFT JOIN pxstafftable AS staff ON stu.banzhurenTeacherID=staff.id " +
            "LEFT JOIN pxstafftable as teacher ON stuyueke.teacherID = teacher.id"+
            " WHERE 1=1"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxyuekestufaqitableVo> getJoinList( @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxclassroomtable"+
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String,String>> getClassRoomList(@Param("ew") Wrapper wrapper);
}