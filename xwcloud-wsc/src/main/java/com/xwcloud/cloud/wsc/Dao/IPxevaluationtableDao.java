package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxevaluationtable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-29
 */
@Repository
public interface IPxevaluationtableDao extends BaseMapper<Pxevaluationtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "note", property = "note"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "classID", property = "classID"),
                @Result(column = "haveClassDate", property = "haveClassDate"),
                @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
                @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
                @Result(column = "weekN", property = "weekN"),
                @Result(column = "keshiTeachTabID", property = "keshiTeachTabID"),
                @Result(column = "stuid", property = "stuid"),
                @Result(column = "teacherid", property = "teacherid"),
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
    List<Pxevaluationtable> getAllList();



    @Select("<script>" +
            "select evalua.*,campus.id as campusID,campus.campusName as campusName,stu.zidingyiStuID as zidingyiStuID," +
            "stu.stuName as stuName,class.className as className,kecheng.kechengName as kechengName,teacher.staffName as staffName " +
            " from pxevaluationtable as evalua " +
            "left join pxkechengtable as kecheng on evalua.KechengID=kecheng.id " +
            "LEFT JOIN pxclasstable as class on evalua.classID=class.id " +
            "LEFT JOIN pxkeshiteachertable as keshi on evalua.keshiTeachTabID = keshi.id " +
            "LEFT JOIN pxstafftable as teacher ON evalua.TeacherID = teacher.id " +
            "LEFT JOIN pxstutable stu ON evalua.stuid=stu.id " +
            "LEFT JOIN pxcampustable campus ON class.campusID = campus.id" +
            " WHERE campus.isOpen !=2 "+
//            "SELECT\n" +
//            "\ta.id pkid,a.*,\n" +
//            "\tc.*,\n" +
//            "\tf.className,\n" +
//            "\th.kechengName,\n" +
//            "\tg.campusName,\n" +
//            "\tc.id stuID,\n" +
//            "\t(\n" +
//            "\tSELECT\n" +
//            "\t\td.id \n" +
//            "\tFROM\n" +
//            "\t\tpxevaluationtable d \n" +
//            "\tWHERE\n" +
//            "\t\ta.haveClassDate = d.haveClassDate \n" +
//            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
//            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
//            "\t\tAND a.classID = d.classID \n" +
//            "\t\tAND c.id = d.Stuid \n" +
//            "\t) pjID, \n" +
//            "\t(\n" +
//            "\tSELECT\n" +
//            "\t\tnote \n" +
//            "\tFROM\n" +
//            "\t\tpxevaluationtable d \n" +
//            "\tWHERE\n" +
//            "\t\ta.haveClassDate = d.haveClassDate \n" +
//            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
//            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
//            "\t\tAND a.classID = d.classID \n" +
//            "\t\tAND c.id = d.Stuid \n" +
//            "\t) note,\n" +
//            "\t(\n" +
//            "\tSELECT\n" +
//            "\t\taddTime \n" +
//            "\tFROM\n" +
//            "\t\tpxevaluationtable d \n" +
//            "\tWHERE\n" +
//            "\t\ta.haveClassDate = d.haveClassDate \n" +
//            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
//            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
//            "\t\tAND a.classID = d.classID \n" +
//            "\t\tAND c.id = d.Stuid \n" +
//            "\t) noteaddTime, \n" +
//            "\t(\n" +
//            "\tSELECT\n" +
//            "\t\td.images \n" +
//            "\tFROM\n" +
//            "\t\tpxevaluationtable d \n" +
//            "\tWHERE\n" +
//            "\t\ta.haveClassDate = d.haveClassDate \n" +
//            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
//            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
//            "\t\tAND a.classID = d.classID \n" +
//            "\t\tAND c.id = d.Stuid \n" +
//            "\t) pjimages, \n" +
//            "\t(\n" +
//            "\tSELECT\n" +
//            "\t\td.pjmp3Url \n" +
//            "\tFROM\n" +
//            "\t\tpxevaluationtable d \n" +
//            "\tWHERE\n" +
//            "\t\ta.haveClassDate = d.haveClassDate \n" +
//            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
//            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
//            "\t\tAND a.classID = d.classID \n" +
//            "\t\tAND c.id = d.Stuid \n" +
//            "\t) pjmp3Url, \n" +
//            "\t(\n" +
//            "\tSELECT\n" +
//            "\t\td.pjvideoUrl \n" +
//            "\tFROM\n" +
//            "\t\tpxevaluationtable d \n" +
//            "\tWHERE\n" +
//            "\t\ta.haveClassDate = d.haveClassDate \n" +
//            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
//            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
//            "\t\tAND a.classID = d.classID \n" +
//            "\t\tAND c.id = d.Stuid \n" +
//            "\t) pjvideoUrl, \n" +
//            "\t(\n" +
//            "\tSELECT\n" +
//            "\t\tteacherid \n" +
//            "\tFROM\n" +
//            "\t\tpxevaluationtable d \n" +
//            "\tWHERE\n" +
//            "\t\ta.haveClassDate = d.haveClassDate \n" +
//            "\t\tAND a.startLessonDateTime = d.startLessonDateTime \n" +
//            "\t\tAND a.endLessonDateTime = d.endLessonDateTime \n" +
//            "\t\tAND a.classID = d.classID \n" +
//            "\t\tAND c.id = d.Stuid \n" +
//            "\t) pjteacherid \n" +
//            "FROM\n" +
//            "\tpxpaiketable a\n" +
//            "\tLEFT JOIN pxxuanketable b ON a.id = b.paikeID\n" +
//            "\tLEFT JOIN pxstutable c ON b.stuID = c.id\n" +
//            "\tLEFT JOIN pxclasstable f ON a.classID = f.id\n" +
//            "\tLEFT JOIN pxkechengtable h ON a.kechengID = h.id\n" +
//            "\tLEFT JOIN pxcampustable g ON f.campusID = g.id \n" +
//            "where g.isOpen!=2 and c.id is not null and a.haveClassDate &gt; (SELECT DATE_ADD(NOW(), INTERVAL -1 MONTH)) and ((SELECT COUNT(e.id) from pxkeshistutable e where a.haveClassDate=e.haveClassDate and a.startLessonDateTime=e.startLessonDateTime and a.endLessonDateTime=e.endLessonDateTime and b.stuID=e.stuID)) &gt; 0" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getPxevaluationtableJoinPage(Page page, @Param("ew") Wrapper wrapper);
}