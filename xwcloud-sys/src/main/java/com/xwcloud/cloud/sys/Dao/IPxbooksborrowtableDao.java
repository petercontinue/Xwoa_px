package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxbooksborrowtable;
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
 * @since 2021-07-30
 */
@Repository
public interface IPxbooksborrowtableDao extends BaseMapper<Pxbooksborrowtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "booksID", property = "booksID"),
            @Result(column = "people", property = "people"),
            @Result(column = "role", property = "role"),
            @Result(column = "borrownum", property = "borrownum"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "dostaffID", property = "dostaffID"),
            @Result(column = "doDate", property = "doDate"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbooksborrowtable"
            + "</script>")
    List<Pxbooksborrowtable> getAllList();


    @Select("<script>" +
            "select \t( CASE WHEN sum(tm.jienum)  IS NOT NULL THEN sum(tm.jienum)  ELSE 0 END ) from \n" +
            "(\n" +
            " select a.id,\n" +
            " COUNT(a.id) jienum,\n" +
            " (SELECT COUNT(b.id) from pxbooksreturntable b where a.booksID=b.booksID and a.people=b.people and a.role=b.role) as huannum\n" +
            " from pxbooksborrowtable a \n" +
            " where DATE_FORMAT(a.endDate,'%Y-%m-%d') &lt; DATE_FORMAT(now(),'%Y-%m-%d') \n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY a.people,a.role\n" +
            ") tm\n" +
            "where tm.jienum!=tm.huannum "
            + "</script>")
    String getnobreakList(@Param("ew") QueryWrapper queryWrapper);
}