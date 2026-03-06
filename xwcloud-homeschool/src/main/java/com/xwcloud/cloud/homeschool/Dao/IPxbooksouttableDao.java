package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.PxbooksouttableVo;
import com.xwcloud.cloud.model.entity.Pxbooksouttable;
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
public interface IPxbooksouttableDao extends BaseMapper<Pxbooksouttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "booksID", property = "booksID"),
            @Result(column = "outnum", property = "outnum"),
            @Result(column = "outstaffID", property = "outstaffID"),
            @Result(column = "outDate", property = "outDate"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbooksouttable"
            + "</script>")
    List<Pxbooksouttable> getAllList();


    @Select("<script>" +
            "SELECT booksout.*,book.booksName as bookName,campus.campusName,staff.staffName as addstaffName " +
            "from  pxbooksouttable booksout " +
            "LEFT JOIN pxbookstable book ON booksout.booksID=book.id " +
            "LEFT JOIN pxcampustable campus ON book.campusID=campus.id "+
            "LEFT JOIN pxstafftable staff ON booksout.outstaffID=staff.id "+
            " WHERE campus.isOpen !=2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxbooksouttableVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT booksout.*,book.booksName as bookName,campus.campusName,staff.staffName as addstaffName " +
            "from  pxbooksouttable booksout " +
            "LEFT JOIN pxbookstable book ON booksout.booksID=book.id " +
            "LEFT JOIN pxcampustable campus ON book.campusID=campus.id "+
            "LEFT JOIN pxstafftable staff ON booksout.outstaffID=staff.id "+
            " WHERE campus.isOpen !=2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxbooksouttableVo> getJoinList(@Param("ew") Wrapper wrapper);
}