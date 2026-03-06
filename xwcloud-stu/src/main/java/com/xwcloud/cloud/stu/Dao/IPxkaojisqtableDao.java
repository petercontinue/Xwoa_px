package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxkaojisqtable;

import com.xwcloud.cloud.model.Vo.kjSqVo;
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
 * @since 2020-11-24
 */
@Repository
public interface IPxkaojisqtableDao extends BaseMapper<Pxkaojisqtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuid", property = "stuid"),
            @Result(column = "kemuid", property = "kemuid"),
            @Result(column = "sqjibie", property = "sqjibie"),
            @Result(column = "shjibie", property = "shjibie"),
            @Result(column = "lururen", property = "lururen"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "shenheren", property = "shenheren"),
            @Result(column = "shenheDate", property = "shenheDate"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkaojisqtable"
            + "</script>")
    List<Pxkaojisqtable> getAllList();

    //分页获取考级申请
    @Select("<script>" +
            "select kj.id as id,pxcampustable.campusName as campusName,pxstugradetable.stuGradeName as stuGradeName,pxstutable.stuName as stuName,pxstutable.id stuID," +
            "pxsubjecttable.subjectName as subjectName,pxsubjecttable.id subjectID,kj.sqjibie as sqjibie,kj.shjibie as shjibie,pxstafftable.staffName as adduser," +
            "(SELECT staffName from pxstafftable where id=kj.shenheren) as shenheuser," +
            "kj.addDate as addDate,kj.shenheDate as shenheDate " +
            "from pxkaojisqtable as kj " +
            "LEFT JOIN pxstutable on kj.stuid=pxstutable.id " +
            "LEFT JOIN pxsubjecttable on kj.kemuid=pxsubjecttable.id " +
            "LEFT JOIN pxcampustable on pxstutable.campusID=pxcampustable.id " +
            "LEFT JOIN pxstugradetable on pxstutable.stuGradeID=pxstugradetable.id " +
            "LEFT JOIN pxstafftable on kj.lururen=pxstafftable.id " +
            "where pxcampustable.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<kjSqVo> getKJsqPage(Page page, @Param("ew") QueryWrapper queryWrapper);
}