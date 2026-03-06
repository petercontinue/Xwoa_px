package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabutable;
import com.xwcloud.cloud.model.Vo.PxyueketeacherfabutableVo;
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
public interface IPxyueketeacherfabutableDao extends BaseMapper<Pxyueketeacherfabutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "haveLessonDate", property = "haveLessonDate"),
            @Result(column = "startLessonTime", property = "startLessonTime"),
            @Result(column = "endLessonTime", property = "endLessonTime"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "KechengID", property = "KechengID"),
            @Result(column = "classroomID", property = "classroomID"),
            @Result(column = "minSuccessYuekeStuNum", property = "minSuccessYuekeStuNum"),
            @Result(column = "maxStuNum", property = "maxStuNum"),
            @Result(column = "paikeID", property = "paikeID"),
            @Result(column = "yuekeState", property = "yuekeState"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "yuekeTitle",property = "yuekeTitle"),
            @Result(column = "yuekeshuoming",property = "yuekeshuoming"),
            @Result(column = "yuekeImg",property = "yuekeImg"),
            @Result(column = "liulanTimes",property = "liulanTimes"),
            @Result(column = "fenxiangTimes",property = "fenxiangTimes")
    })
    @Select("<script>" +
            "SELECT * from  pxyueketeacherfabutable"
            + "</script>")
    List<Pxyueketeacherfabutable> getAllList();

    @Select("<script>" +
            "SELECT teacherfabu.*,kecheng.kechengName,class.className,campus.campusName,buxistyle.buxiStyleName,(SELECT  COUNT(*) FROM pxyueketeacherfabustutable WHERE yuekeTeachFabuID = teacherfabu.id) as current " +
            "FROM pxyueketeacherfabutable as teacherfabu " +
            "LEFT JOIN pxclasstable as class ON teacherfabu.classID = class.id " +
            "LEFT JOIN pxkechengtable as kecheng on teacherfabu.KechengID = kecheng.id " +
            "LEFT JOIN pxcampustable AS campus ON teacherfabu.campusID = campus.id " +
            "LEFT JOIN pxbuxistyletable as buxistyle ON kecheng.buxiStyleID = buxistyle.id"+
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxyueketeacherfabutableVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT teacherfabu.*,kecheng.kechengName,class.className,campus.campusName,buxistyle.buxiStyleName," +
            "(SELECT  COUNT(*) FROM pxyueketeacherfabustutable WHERE yuekeTeachFabuID = teacherfabu.id) as current " +
            "FROM pxyueketeacherfabutable as teacherfabu " +
            "LEFT JOIN pxclasstable as class ON teacherfabu.classID = class.id " +
            "LEFT JOIN pxkechengtable as kecheng on teacherfabu.KechengID = kecheng.id " +
            "LEFT JOIN pxcampustable AS campus ON teacherfabu.campusID = campus.id " +
            "LEFT JOIN pxbuxistyletable as buxistyle ON kecheng.buxiStyleID = buxistyle.id"+
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<PxyueketeacherfabutableVo> getJoinList( @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT kecheng.*,classtime.classTimeStyleName FROM pxteachsubjecttable tsub \n" +
            "LEFT JOIN pxkechengtable kecheng ON tsub.teachSubjectID=kecheng.subjectID\n" +
            "LEFT JOIN pxclasstimestyletable classtime ON classtime.id = kecheng.classTimeStyleID"+
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String,String>> getKechengList(@Param("ew") Wrapper wrapper);

}