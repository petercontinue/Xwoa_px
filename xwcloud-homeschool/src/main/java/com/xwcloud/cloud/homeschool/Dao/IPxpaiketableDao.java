package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-28
 */
@Repository
public interface IPxpaiketableDao extends BaseMapper<Pxpaiketable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "startLessonDateTime", property = "startlessondatetime"),
            @Result(column = "endLessonDateTime", property = "endlessondatetime"),
            @Result(column = "haveClassDate", property = "haveclassdate"),
            @Result(column = "teacherIDs", property = "teacherids"),
            @Result(column = "teacherNames", property = "teachernames"),
            @Result(column = "classID", property = "classid"),
            @Result(column = "classRoomID", property = "classroomid"),
            @Result(column = "weekN", property = "weekn"),
            @Result(column = "MaxStuNum", property = "maxstunum"),
            @Result(column = "tongke1v1KechengID", property = "tongke1v1kechengid"),
            @Result(column = "kechengID", property = "kechengid"),
            @Result(column = "kechengContent", property = "kechengcontent"),
            @Result(column = "dakaoqin", property = "dakaoqin"),
            @Result(column = "tags", property = "tags"),
            @Result(column = "canqingjiaBeforeHours", property = "canqingjiabeforehours"),
            @Result(column = "shuakaTimeArea", property = "shuakatimearea"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  pxpaiketable"
            + "</script>")
    List<Pxpaiketable> getAllList();
    @Select("<script>" +
            "SELECT stuclass.classID,buxikecheng.stuID from  pxbuxikechengtable buxikecheng " +
            "LEFT JOIN pxstuclasstable stuclass ON buxikecheng.id=stuclass.buxiID"+
            " WHERE 1=1" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String,String>> getClassID(@Param("ew") Wrapper wrapper);

}