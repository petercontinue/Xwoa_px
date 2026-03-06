package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-22
 */
@Repository
public interface IPxkeshistutableDao extends BaseMapper<Pxkeshistutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "StuGradeID", property = "StuGradeID"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "buxiStyleID", property = "buxiStyleID"),
            @Result(column = "classTimeStyleID", property = "classTimeStyleID"),
            @Result(column = "kechengPrice", property = "kechengPrice"),
            @Result(column = "stuKaoqingStyle", property = "stuKaoqingStyle"),
            @Result(column = "dakaoqingStyle", property = "dakaoqingStyle"),
            @Result(column = "shuoMing", property = "shuoMing"),
            @Result(column = "adduser", property = "adduser"),
            @Result(column = "addtime", property = "addtime"),
            @Result(column = "zhujiao", property = "zhujiao"),
            @Result(column = "buxikechengID", property = "buxikechengID"),
            @Result(column = "banzhurenStaffID", property = "banzhurenStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshistutable"
            + "</script>")
    List<Pxkeshistutable> getAllList();

    //按照学员ID获取学员课时
    @Select("<script>" + "select * from pxkeshistutable where stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxkeshistutable> getkeshistu(Long stuID,Long qiyeID);

    //按照上课时间获取班级内学员课耗
    @Select("<script>" + "select * from pxkeshistutable where classID=#{classID} and haveClassDate=#{haveClassDate} and startLessonDateTime=#{startLessonDateTime} and endLessonDateTime=#{endLessonDateTime} and stuID !=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxkeshistutable> otherStuks(Long classID, Date haveClassDate, Time startLessonDateTime, Time endLessonDateTime, Long stuID,Long qiyeID);


    //按照补习课程ID获取
    @Select("<script>" + "select * from pxkeshistutable where buxikechengID=#{bxkcID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxkeshistutable> getBybxID(Long bxkcID,Long qiyeID);

    //按照学员|课程、上课时间获取
    @Select("<script>" + "select * from pxkeshistutable where stuID=#{stuID} and kechengID=#{kechengID} and haveClassDate=#{haveClassDate} and startLessonDateTime=#{startLessonDateTime} and endLessonDateTime=#{endLessonDateTime} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxkeshistutable> getByStuKcDateTime(Long stuID, Long kechengID, Date haveClassDate, Time startLessonDateTime, Time endLessonDateTime ,Long qiyeID);

    //按照学员、课程获取
    @Select("<script>" + "select * from pxkeshistutable where stuID=#{stuID} and kechengID=#{kechengID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxkeshistutable> getByStuKc(Long stuID, Long kechengID,Long qiyeID);
}