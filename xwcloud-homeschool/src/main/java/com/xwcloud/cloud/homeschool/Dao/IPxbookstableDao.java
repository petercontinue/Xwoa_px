package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.PxbookstableVo;
import com.xwcloud.cloud.model.entity.Pxbookstable;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
public interface IPxbookstableDao extends BaseMapper<Pxbookstable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "booksName", property = "booksName"),
            @Result(column = "allnum", property = "allnum"),
            @Result(column = "cunnum", property = "cunnum"),
            @Result(column = "iSdaance", property = "iSdaance"),
            @Result(column = "iSdisc", property = "iSdisc"),
            @Result(column = "author", property = "author"),
            @Result(column = "press", property = "press"),
            @Result(column = "chubanDate", property = "chubanDate"),
            @Result(column = "banci", property = "banci"),
            @Result(column = "isbn", property = "isbn"),
            @Result(column = "bookLocationCode", property = "bookLocationCode"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbookstable"
            + "</script>")
    List<Pxbookstable> getAllList();

    @Select("<script>" +
            "SELECT books.*, (allnum-cunnum) as alreadyNum,campus.campusName " +
            "FROM pxbookstable books " +
            "LEFT JOIN pxcampustable campus ON books.campusID=campus.id " +
            " WHERE campus.isOpen !=2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxbookstableVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT books.*, (allnum-cunnum) as alreadyNum,campus.campusName " +
            "FROM pxbookstable books " +
            "LEFT JOIN pxcampustable campus ON books.campusID=campus.id " +
            " WHERE campus.isOpen !=2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxbookstableVo> getJoinList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * " +
            "FROM pxcampustable campus " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxcampustable> getCampusList(@Param("ew") Wrapper wrapper);
}