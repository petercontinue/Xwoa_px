package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.gonggaoVO;
import com.xwcloud.cloud.model.entity.Pxgonggaotable;
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
 * @since 2020-12-22
 */
@Repository
public interface IPxgonggaotableDao extends BaseMapper<Pxgonggaotable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "gonggaoTitel", property = "gonggaotitel"),
            @Result(column = "gonggaoContent", property = "gonggaocontent"),
            @Result(column = "staffID", property = "staffid"),
            @Result(column = "gonggaoDate", property = "gonggaodate"),
            @Result(column = "fujian", property = "fujian"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  pxgonggaotable"
            + "</script>")
    List<Pxgonggaotable> getAllList();

    @Select("<script>"+"SELECT a.id,a.staffID,b.staffName,a.gonggaoTitel,a.gonggaoContent,a.gonggaoDate FROM pxgonggaotable AS a LEFT JOIN pxstafftable AS b ON a.staffID = b.id"+  " WHERE 1=1"+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<gonggaoVO> GetgonggaoPages(Page page, @Param("ew") Wrapper wrapper);
    @Select("<script>"+"SELECT a.id,a.staffID,b.staffName,a.gonggaoTitel,a.gonggaoContent,a.gonggaoDate FROM pxgonggaotable AS a LEFT JOIN pxstafftable AS b ON a.staffID = b.id"+  " WHERE 1=1"+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<gonggaoVO> GetgonggaoList(@Param("ew") Wrapper wrapper);
}