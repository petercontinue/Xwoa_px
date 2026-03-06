package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.PxgonggaojiazhangtableVo;
import com.xwcloud.cloud.model.entity.Pxgonggaojiazhangtable;
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
 * @since 2020-11-12
 */
public interface

IPxgonggaojiazhangtableDao extends BaseMapper<Pxgonggaojiazhangtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "bianLiangName", property = "bianLiangName"),
            @Result(column = "modifyValue", property = "modifyValue"),
            @Result(column = "ParameterContent", property = "ParameterContent"),
            @Result(column = "type", property = "type"),
            @Result(column = "tianjiastaff", property = "tianjiastaff"),
            @Result(column = "tianjiashijian", property = "tianjiashijian"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxgonggaojiazhangtable"
            + "</script>")
    List<Pxgonggaojiazhangtable> getAllList();

    @Select("<script>" +
            "SELECT jiazhanggonggao.*,staff.staffName as addstaffName " +
            "from  pxgonggaojiazhangtable jiazhanggonggao " +
            "LEFT JOIN pxstafftable staff ON jiazhanggonggao.tianjiastaff=staff.id "+
            " WHERE 1=1"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxgonggaojiazhangtableVo> getPage(Page page, @Param("ew") Wrapper wrapper);
}