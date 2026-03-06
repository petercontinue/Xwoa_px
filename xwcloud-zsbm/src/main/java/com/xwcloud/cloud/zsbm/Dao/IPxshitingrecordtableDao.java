package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.shitingLiushuiVo;
import com.xwcloud.cloud.model.entity.Pxshitingrecordtable;
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
 * @since 2020-11-24
 */
@Repository
public interface IPxshitingrecordtableDao extends BaseMapper<Pxshitingrecordtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "daofangID", property = "daofangID"),
            @Result(column = "chabanOr1v1", property = "chabanOr1v1"),
            @Result(column = "subjectID", property = "subjectID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "stTeacher", property = "stTeacher"),
            @Result(column = "yxStuID", property = "yxStuID"),
            @Result(column = "isAddStuNumToTeacher", property = "isAddStuNumToTeacher"),
            @Result(column = "shitingPrice", property = "shitingPrice"),
            @Result(column = "classRoomID", property = "classRoomID"),
            @Result(column = "shiTingManyiduID", property = "shiTingManyiduID"),
            @Result(column = "shiTingShuoming", property = "shiTingShuoming"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "paikeID", property = "paikeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxshitingrecordtable"
            + "</script>")
    List<Pxshitingrecordtable> getAllList();

    /**
     * 通过学生ID查询试听记录
     * @param stuID
     * @return
     */
    @Select("<script>" + "SELECT * FROM pxshitingrecordtable WHERE stuID=#{stuID}" + "</script>")
    List<Pxshitingrecordtable> GetShitingRecordsByStuID(long stuID);

    /**
     *通过意向学员ID删除试听记录
     * @param stuID
     * @return
     */
    @Select("<script>" + "DELETE FROM pxshitingrecordtable WHERE yxstuID=#{stuID}" + "</script>")
    int deleteShitongRecordsByStuID(long stuID);

    /**
     * 查询试听流水记录
     */
    @Select("<script>" +
            "SELECT a.*,c.campusName,b.stuName,e.classRoomID,f.classRoomName,d.className,e.haveClassDate,e.teacherNames stTeachers,l.payMoneyStyle payMoneyStyle,\n" +
            "CONCAT(TIME_FORMAT(e.startLessonDateTime,'%H:%i'),'-',TIME_FORMAT(e.endLessonDateTime,'%H:%i')) haveLessTime,h.staffName\n" +
            "FROM pxshitingrecordtable AS a\n" +
            "LEFT JOIN pxstutable AS b ON a.yxStuID = b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID = c.id\n" +
            "LEFT JOIN pxclasstable AS d ON a.classID = d.id\n" +
            "LEFT JOIN pxpaiketable AS e ON a.paikeID = e.id\n" +
            "LEFT JOIN pxclassroomtable AS f ON e.classRoomID = f.id\n" +
            "LEFT JOIN pxstafftable AS h ON a.addStaffID = h.id\n" +
            "LEFT JOIN pxliushuizhangtable l on a.liushuiID=l.id "+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<shitingLiushuiVo> GetShitingLiushuiPages(Page<shitingLiushuiVo> page, @Param("ew") QueryWrapper<shitingLiushuiVo> wrapper);

    /**
     * 查询试听流水
     */
    @Select("<script>" +
            "SELECT ifnull(a.shiTingShuoming, '')shiTingShuoming," +
            "(case a.shiTingManyiduID " +
            "when '1' then '不明确' " +
            "when '2' then '很不满意' " +
            "when '3' then '不满意' " +
            "when '4' then '基本满意' " +
            "when '5' then '很满意' else '' end)shiTingManyidu," +
            "if(a.chabanOr1v1=1,'插班试听','一对一')chabanOr1v1Value," +
            "ifnull(c.campusName,'')campusName," +
            "ifnull(b.stuName,'')stuName," +
            "e.classRoomID,f.classRoomName,d.className,e.haveClassDate,e.teacherNames stTeachers,\n" +
            "CONCAT(TIME_FORMAT(e.startLessonDateTime,'%H:%i'),'-',TIME_FORMAT(e.endLessonDateTime,'%H:%i')) haveLessTime\n" +
            "FROM pxshitingrecordtable AS a\n" +
            "LEFT JOIN pxstutable AS b ON a.yxStuID = b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID = c.id\n" +
            "LEFT JOIN pxclasstable AS d ON a.classID = d.id\n" +
            "LEFT JOIN pxpaiketable AS e ON a.paikeID = e.id\n" +
            "LEFT JOIN pxclassroomtable AS f ON e.classRoomID = f.id\n" +
            "LEFT JOIN pxstafftable AS h ON a.addStaffID = h.id\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment} " +
            "</if>" +
            "</where>" +
            "</script>")
    List<shitingLiushuiVo> GetShitingLiushuiList(@Param("ew") Wrapper wrapper);


}