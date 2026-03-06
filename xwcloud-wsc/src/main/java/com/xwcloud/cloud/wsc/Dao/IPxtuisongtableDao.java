package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.Vo.messageVO;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-21
 */
@Repository
public interface IPxtuisongtableDao extends BaseMapper<Pxtuisongtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "note", property = "note"),
            @Result(column = "tuisongTypeName", property = "tuisongTypeName"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "role", property = "role"),
            @Result(column = "wxstate", property = "wxstate"),
            @Result(column = "appread", property = "appread"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtuisongtable"
            + "</script>")
    List<Pxtuisongtable> getAllList();

    @Select("<script>" + "SELECT e.tuisongType,a.note,a.addTime,a.wxstate,a.appread FROM pxtuisongtable AS a \n" +
            "LEFT JOIN pxtuisongtypetable AS e ON a.tuisongTypeName = e.id\n" +
            "LEFT JOIN pxtuisongtypetable AS b ON a.tuisongTypeName = b.id \n" +
            "LEFT JOIN wsc_user_bind AS c ON a.stuID = c.stuId\n" +
            "LEFT JOIN wsc_user AS d ON c.wscuserid = d.id" + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<messageVO> GetAllMessagePages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT b.tuisongType,a.note,a.addTime,a.wxstate,a.appread FROM pxtuisongtable AS a \n" +
            "LEFT JOIN pxtuisongtypetable AS b ON a.tuisongTypeName = b.id\n" +
            "LEFT JOIN pxstafftable AS s ON a.stuID = s.id" + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<messageVO> GetAllMessageStaffPages(Page page, @Param("ew") Wrapper wrapper);
}