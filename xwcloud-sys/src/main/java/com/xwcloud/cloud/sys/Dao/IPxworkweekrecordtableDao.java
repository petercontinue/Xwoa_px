package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.PxworkweekrecordVo;
import com.xwcloud.cloud.model.entity.Pxworkweekrecordtable;
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
 * @since 2020-10-25
 */
@Repository
public interface IPxworkweekrecordtableDao extends BaseMapper<Pxworkweekrecordtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "thisWeekRecord", property = "thisWeekRecord"),
            @Result(column = "nextWeekRecord", property = "nextWeekRecord"),
            @Result(column = "luruDate", property = "luruDate"),
            @Result(column = "imgsUrl", property = "imgsUrl"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxworkweekrecordtable"
            + "</script>")
    List<Pxworkweekrecordtable> getAllList();

    /**
     * 分页查询员工周工作总结
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "select a.id, a.startDate,a.endDate,a.thisWeekRecord,a.nextWeekRecord,a.luruDate,b.staffName,c.staffPostName,d.campusName from pxworkweekrecordtable as a \n" +
            "LEFT JOIN pxstafftable as b on a.staffID = b.id LEFT JOIN pxstaffposttable as c on b.staffPostID = c.id\n" +
            "LEFT JOIN pxcampustable as d on b.campusID = d.id" +  " WHERE 1=1"+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxworkweekrecordVo> Getworkweekrecords(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 按照自定义条件查询所有的周工作总结
     * @param wrapper
     * @return
     */
    @Select("<script>" + "select a.id, a.startDate,a.endDate,a.thisWeekRecord,a.nextWeekRecord,a.luruDate,b.staffName,c.staffPostName,d.campusName from pxworkweekrecordtable as a \n" +
            "LEFT JOIN pxstafftable as b on a.staffID = b.id LEFT JOIN pxstaffposttable as c on b.staffPostID = c.id\n" +
            "LEFT JOIN pxcampustable as d on b.campusID = d.id" +  " WHERE 1=1"+
            "<if test='ew != null and ew.SqlSegment != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxworkweekrecordVo> getworkweekrecordsList( @Param("ew") Wrapper wrapper);
}