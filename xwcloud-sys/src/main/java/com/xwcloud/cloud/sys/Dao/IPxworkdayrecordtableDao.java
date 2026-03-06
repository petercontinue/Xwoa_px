package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.workdayrecordVo;
import com.xwcloud.cloud.model.entity.Pxworkdayrecordtable;
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
public interface IPxworkdayrecordtableDao extends BaseMapper<Pxworkdayrecordtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "LogDate", property = "LogDate"),
            @Result(column = "LogContent", property = "LogContent"),
            @Result(column = "ImgsUrl", property = "ImgsUrl"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxworkdayrecordtable"
            + "</script>")
    List<Pxworkdayrecordtable> getAllList();

    /**
     * 分页查询员工工作日报
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>"+"select a.LogContent,a.ImgsUrl,a.LogDate,a.id,b.staffName,c.campusName,d.staffpostName from pxworkdayrecordtable as a \n" +
            "LEFT JOIN pxstafftable as b on a.staffID=b.id \n" +
            "LEFT JOIN pxcampustable as c on b.campusID = c.id\n" +
            "LEFT JOIN pxstaffposttable as d on b.staffPostID = d.id"+
            " WHERE 1=1"
            + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<workdayrecordVo> Getworkdayrecords(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"select a.LogContent,a.ImgsUrl,a.LogDate,a.id,b.staffName,c.campusName,d.staffpostName from pxworkdayrecordtable as a \n" +
            "LEFT JOIN pxstafftable as b on a.staffID=b.id \n" +
            "LEFT JOIN pxcampustable as c on b.campusID = c.id\n" +
            "LEFT JOIN pxstaffposttable as d on b.staffPostID = d.id"+  " WHERE 1=1"
            + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<workdayrecordVo> GetWorkdayrecordsList( @Param("ew") Wrapper wrapper);
}