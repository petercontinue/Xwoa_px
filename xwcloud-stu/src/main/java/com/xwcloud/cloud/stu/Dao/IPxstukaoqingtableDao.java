package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.countykqVo;
import com.xwcloud.cloud.model.entity.Pxstukaoqingtable;
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
 * @since 2020-11-23
 */
@Repository
public interface IPxstukaoqingtableDao extends BaseMapper<Pxstukaoqingtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "haveclassDate", property = "haveclassDate"),
            @Result(column = "startClassDateTime", property = "startClassDateTime"),
            @Result(column = "endClassDateTime", property = "endClassDateTime"),
            @Result(column = "kaoqingStyle", property = "kaoqingStyle"),
            @Result(column = "kaoqingBeizhu", property = "kaoqingBeizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstukaoqingtable"
            + "</script>")
    List<Pxstukaoqingtable> getAllList();

    @Select("<script>" + "select * from pxstukaoqingtable where stuID=#{stuID} and qiyeID=#{qiyeID} " + "</script>")
    List<Pxstukaoqingtable> allStukaoqing(Long stuID, Long qiyeID);

    @Select("<script>" + "select count(id) as ykqCount from pxstukaoqingtable where haveclassDate=#{haveclassDate} and startClassDateTime=#{startClassDateTime} and endClassDateTime=#{endClassDateTime} and classID=#{classID} and teacherIDs=#{teacherIDs} and qiyeID=#{qiyeID}" + "</script>")
    countykqVo ykqCount(Date haveclassDate, Time startClassDateTime, Time endClassDateTime, Long classID, String teacherIDs, Long qiyeID);

    @Select("<script>" + "select * from pxstukaoqingtable where haveclassDate=#{haveclassDate} and startClassDateTime=#{startClassDateTime} and endClassDateTime=#{endClassDateTime} and classID=#{classID} and teacherIDs=#{teacherIDs} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxstukaoqingtable> getykq(Date haveclassDate, Time startClassDateTime, Time endClassDateTime, Long classID, String teacherIDs, Long qiyeID);
}