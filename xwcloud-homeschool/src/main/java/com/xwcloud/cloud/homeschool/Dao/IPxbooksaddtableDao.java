package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.PxbooksaddtableVo;
import com.xwcloud.cloud.model.entity.Pxbooksaddtable;
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
 * @since 2020-11-13
 */
public interface IPxbooksaddtableDao extends BaseMapper<Pxbooksaddtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "booksID", property = "booksID"),
            @Result(column = "addnum", property = "addnum"),
            @Result(column = "addstaffID", property = "addstaffID"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbooksaddtable"
            + "</script>")
    List<Pxbooksaddtable> getAllList();

    @Select("<script>" +
            "SELECT booksadd.*,book.booksName as bookName,campus.campusName,staff.staffName as addstaffName " +
            "from  pxbooksaddtable booksadd " +
            "LEFT JOIN pxbookstable book ON booksadd.booksID=book.id " +
            "LEFT JOIN pxcampustable campus ON book.campusID=campus.id "+
            "LEFT JOIN pxstafftable staff ON booksadd.addstaffID=staff.id"+
            " WHERE campus.isOpen !=2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxbooksaddtableVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT booksadd.*,book.booksName,campus.campusName,staff.staffName as addstaffName" +
            "from  pxbooksaddtable booksadd " +
            "LEFT JOIN pxbookstable book ON booksadd.booksID=book.id " +
            "LEFT JOIN pxcampustable campus ON book.campusID=campus.id "+
            "LEFT JOIN pxstafftable staff ON booksadd.addstaffID=staff.id"+
            " WHERE campus.isOpen !=2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxbooksaddtableVo> getJoinList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * " +
            "from  pxbooksaddtable"
            + "</script>")
    List<HashMap<String,String>> getList();
}