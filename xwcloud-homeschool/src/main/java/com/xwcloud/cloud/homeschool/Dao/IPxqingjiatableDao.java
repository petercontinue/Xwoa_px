package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.PxqingjiatableVo;
import com.xwcloud.cloud.model.entity.Pxqingjiatable;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxqingjiatableDao extends BaseMapper<Pxqingjiatable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "stuid", property = "stuid"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qingjiaDateTime", property = "qingjiaDateTime"),
            @Result(column = "qjState", property = "qjState"),
            @Result(column = "cancelQjDateTime", property = "cancelQjDateTime"),
            @Result(column = "cancelQjReason", property = "cancelQjReason"),
            @Result(column = "shenheRen", property = "shenheRen"),
            @Result(column = "shenheState", property = "shenheState"),
            @Result(column = "shenheNopassReason", property = "shenheNopassReason"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "paikeID", property = "paikeID"),
            @Result(column = "banzhurenID", property = "banzhurenID"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  pxqingjiatable"
        + "</script>")
List<Pxqingjiatable> getAllList();

    @Select("<script>" +
            "SELECT qingjia.*,kecheng.kechengName as kechengName,class.className as className,stu.stuName as stuName," +
            "staff.staffName as staffName,campus.campusName as campusName " +
            "FROM pxqingjiatable as qingjia " +
            "LEFT JOIN pxkechengtable as kecheng ON qingjia.KechengID=kecheng.id " +
            "LEFT JOIN pxclasstable as class ON qingjia.classID = class.id " +
            "LEFT JOIN  pxstafftable as staff ON qingjia.banzhurenID=staff.id " +
            "LEFT JOIN pxstutable as stu ON qingjia.stuid = stu.id " +
            "LEFT JOIN pxcampustable as campus ON stu.campusID = campus.id"+
            " WHERE campus.isOpen !=2"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxqingjiatableVo> getPxqingjiatableJoinPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT qingjia.*,kecheng.kechengName as kechengName,class.className as className,stu.stuName as stuName," +
            "staff.staffName as staffName,campus.campusName as campusName " +
            "FROM pxqingjiatable as qingjia " +
            "LEFT JOIN pxkechengtable as kecheng ON qingjia.KechengID=kecheng.id " +
            "LEFT JOIN pxclasstable as class ON qingjia.classID = class.id " +
            "LEFT JOIN  pxstafftable as staff ON qingjia.banzhurenID=staff.id " +
            "LEFT JOIN pxstutable as stu ON qingjia.stuid = stu.id " +
            "LEFT JOIN pxcampustable as campus ON stu.campusID = campus.id"+
            " WHERE campus.isOpen !=2"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxqingjiatableVo> getPxqingjiatableJoinList( @Param("ew") Wrapper wrapper);

}