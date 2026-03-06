package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.PxsalarystaffposttableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystaffposttable;
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
 * @since 2020-11-25
 */
public interface IPxsalarystaffposttableDao extends BaseMapper<Pxsalarystaffposttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffPostID", property = "staffPostID"),
            @Result(column = "salaryStyleID", property = "salaryStyleID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxsalarystaffposttable"
            + "</script>")
    List<Pxsalarystaffposttable> getAllList();

    @Select("<script>" + "SELECT\n" +
            "\ta.campusID,\n" +
            "\tb.campusName,\n" +
            "\ta.id AS postID,\n" +
            "\ta.staffpostName,\n" +
            "\t\n" +
            "\t(SELECT GROUP_CONCAT(salaryStyle) FROM pxsalarystyletable WHERE id in(SELECT salaryStyleID FROM pxsalarystaffposttable WHERE staffPostID = a.id)) AS salaryStyle,\n" +
            "\t(SELECT GROUP_CONCAT(id) FROM pxsalarystyletable WHERE id in(SELECT salaryStyleID FROM pxsalarystaffposttable WHERE staffPostID = a.id)) AS sStyleID\n" +
            "FROM\n" +
            "\tpxstaffposttable AS a\n" +
            "\tLEFT JOIN pxcampustable AS b ON a.campusID = b.id " +
            " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<PxsalarystaffposttableVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT salarypost.*,post.id as postID,post.staffpostName,campus.campusName,GROUP_CONCAT(style.salaryStyle) as salaryStyle," +
            "GROUP_CONCAT(salarypost.salaryStyleID) as sStyleID," +
            "campus.id as campusID " +
            "FROM pxsalarystaffposttable salarypost " +
            "LEFT JOIN pxsalarystyletable style ON salarypost.salaryStyleID=style.id " +
            "LEFT JOIN pxstaffposttable post ON salarypost.staffPostID=post.id " +
            "LEFT JOIN pxcampustable campus ON campus.id = post.campusID" +
            " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            " GROUP BY salarypost.staffPostID"
            + "</script>")
    List<PxsalarystaffposttableVo> getJoinList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxsalarystyletable" +
            " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getGongzixiangmuList(@Param("ew") Wrapper wrapper);
}