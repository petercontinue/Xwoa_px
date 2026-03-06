package com.xwcloud.cloud.homeschool.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxzuoyetable;
import com.xwcloud.cloud.model.Vo.PxzuoyetableVo;
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
public interface IPxzuoyetableDao extends BaseMapper<Pxzuoyetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "neirong", property = "neirong"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "zuoyeImg", property = "zuoyeImg"),
            @Result(column = "zuoyeMp3", property = "zuoyeMp3"),
            @Result(column = "zuoyeVideo", property = "zuoyeVideo"),
            @Result(column = "otherFile", property = "otherFile"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxzuoyetable"
            + "</script>")
    List<Pxzuoyetable> getAllList();

    @Select("<script>" +
            "SELECT zuoye.*,staff.staffName as teacherName,class.className as className," +
            "(select count(*) from pxzuoyestujiaotable where zuoyeID=zuoye.id) as submitDetails " +
            "FROM pxzuoyetable as zuoye " +
            "LEFT JOIN pxstafftable as staff ON zuoye.addStaffID = staff.id " +
            "LEFT JOIN pxclasstable class ON zuoye.classID=class.id "+
            " WHERE 1=1"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxzuoyetableVo> getPage(Page page,@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT zuoye.*,staff.staffName as teacherName,class.className as className," +
            "(select count(*) from pxzuoyestujiaotable where zuoyeID=zuoye.id) as submitDetails " +
            "FROM pxzuoyetable as zuoye " +
            "LEFT JOIN pxstafftable as staff ON zuoye.addStaffID = staff.id " +
            "LEFT JOIN pxclasstable class ON zuoye.classID=class.id "+
            " WHERE 1=1"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxzuoyetableVo> getJoinList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT stuzuoye.*,stu.stuName FROM pxzuoyestujiaotable stuzuoye " +
            "LEFT JOIN pxstutable stu ON stuzuoye.stuID = stu.id"+
            " WHERE 1=1"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String,String>> getZuoyeDetaile(Page page, @Param("ew") Wrapper wrapper);
}