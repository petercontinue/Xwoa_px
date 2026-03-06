package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.PxsalarystyletableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystyletable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import scala.collection.immutable.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
public interface IPxsalarystyletableDao extends BaseMapper<Pxsalarystyletable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "salaryStyle", property = "salaryStyle"),
            @Result(column = "isJiaOrJianOrQiuhe", property = "isJiaOrJianOrQiuhe"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "lurudate", property = "lurudate"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxsalarystyletable"
            + "</script>")
    List<Pxsalarystyletable> getAllList();


    @Select("<script>" +
            "SELECT style.*,staff.staffName as staffName from  pxsalarystyletable style " +
            "LEFT JOIN pxstafftable staff ON style.staffID = staff.id "+
            "where 1=1"+
            "<if test='ew != null'>"+
            " AND "+
            " ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxsalarystyletableVo> getPage(Page page, @Param("ew") Wrapper wrapper);
}