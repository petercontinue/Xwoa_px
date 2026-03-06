package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkeshiteachertable;
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
public interface IPxkeshiteachertableDao extends BaseMapper<Pxkeshiteachertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "teacherID", property = "teacherID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "buxiStyleID", property = "buxiStyleID"),
            @Result(column = "classTimeStyleID", property = "classTimeStyleID"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "ysStuNum", property = "ysStuNum"),
            @Result(column = "ssStuNum", property = "ssStuNum"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "teacherkaoqing", property = "teacherkaoqing"),
            @Result(column = "shuoMing", property = "shuoMing"),
            @Result(column = "zhujiao", property = "zhujiao"),
            @Result(column = "allstuNianji", property = "allstuNianji"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshiteachertable"
            + "</script>")
    List<Pxkeshiteachertable> getAllList();

    //按照上课时间获取班级的教师上课记录
    @Select("<script>" + "select * from pxkeshistutable where classID=#{classID} and haveClassDate=#{haveClassDate} and startLessonDateTime=#{startLessonDateTime} and endLessonDateTime=#{endLessonDateTime} and qiyeID=#{qiyeID} " + "</script>")
    List<Pxkeshiteachertable> Teachks(Long classID, Date haveClassDate, Time startLessonDateTime, Time endLessonDateTime,Long qiyeID);
}