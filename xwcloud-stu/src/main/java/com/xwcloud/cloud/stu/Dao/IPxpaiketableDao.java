package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Repository
public interface IPxpaiketableDao extends BaseMapper<Pxpaiketable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "classRoomID", property = "classRoomID"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "MaxStuNum", property = "MaxStuNum"),
            @Result(column = "tongke1v1KechengID", property = "tongke1v1KechengID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "dakaoqin", property = "dakaoqin"),
            @Result(column = "tags", property = "tags"),
            @Result(column = "canqingjiaBeforeHours", property = "canqingjiaBeforeHours"),
            @Result(column = "shuakaTimeArea", property = "shuakaTimeArea"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxpaiketable"
            + "</script>")
    List<Pxpaiketable> getAllList();

    /**
     * 按条件获取
     */
    @Select("<script>" + "select * from pxpaiketable where classID=#{classID} and haveClassDate>#{hvdate} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxpaiketable> getTk(Long classID, Date hvdate, Long qiyeID);

    @Select("<script>" + "select * from pxpaiketable where classID=#{classID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxpaiketable> getpkBYClassID(Long classID, Long qiyeID);

    @Select("<script>" + "select * from pxpaiketable where classID=#{classID} and dakaoqin !=true and qiyeID=#{qiyeID}" + "</script>")
    List<Pxpaiketable> getBykq(Long classID, Long qiyeID);


    @Select("<script>" + "select * from pxpaiketable where kechengID=#{kechengID} and qiyeID=#{qiyeID} ORDER BY haveClassDate,startLessonDateTime" + "</script>")
    List<Pxpaiketable> getBykc(Long kechengID, Long qiyeID);
}
