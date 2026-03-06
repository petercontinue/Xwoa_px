package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.outjiluVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesouttable;
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
 * @since 2020-11-14
 */
@Repository
public interface IPxteachingsuppliesouttableDao extends BaseMapper<Pxteachingsuppliesouttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "suppliesId", property = "suppliesId"),
            @Result(column = "outStaffId", property = "outStaffId"),
            @Result(column = "outReason", property = "outReason"),
            @Result(column = "luruStaffId", property = "luruStaffId"),
            @Result(column = "outDate", property = "outDate"),
            @Result(column = "outNum", property = "outNum"),
            @Result(column = "outNum_before", property = "outnumBefore"),
            @Result(column = "type", property = "type"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachingsuppliesouttable"
            + "</script>")
    List<Pxteachingsuppliesouttable> getAllList();

    @Select("<script>"+"SELECT a.id,e.campusName,b.name,c.typeName,b.specs,a.outNum,b.stockUnit,a.outReason,a.outDate,d.staffName,f.staffName as ystaffName ,b.id AS suppliesID,a.type,a.outNum_before AS stockNum FROM pxteachingsuppliesouttable AS a\n" +
            "LEFT JOIN pxteachingsuppliestable AS b ON a.suppliesId=b.id\n" +
            "LEFT JOIN pxteachingsuppliestypetable AS c ON b.typeId = c.id\n" +
            "LEFT JOIN pxstafftable AS d ON a.luruStaffId = d.id\n" +
            "LEFT JOIN pxcampustable AS e ON b.campusID=e.id\n" +
            "LEFT JOIN pxstafftable AS f ON a.outStaffId = f.id WHERE 1=1"
            + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<outjiluVo> GetTeachingSuppliesOutPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT a.id,e.campusName,b.name,c.typeName,b.specs,a.outNum,b.stockUnit,a.outReason,a.outDate,d.staffName,f.staffName as ystaffName ,b.id AS suppliesID,a.type,a.outNum_before AS stockNum" +
            ",(a.outNum_before-a.outNum) AS outafterNum,(a.outNum_before+a.outNum) AS rukuafterNum FROM pxteachingsuppliesouttable AS a\n" +
            "LEFT JOIN pxteachingsuppliestable AS b ON a.suppliesId=b.id\n" +
            "LEFT JOIN pxteachingsuppliestypetable AS c ON b.typeId = c.id\n" +
            "LEFT JOIN pxstafftable AS d ON a.luruStaffId = d.id\n" +
            "LEFT JOIN pxcampustable AS e ON b.campusID=e.id\n" +
            "LEFT JOIN pxstafftable AS f ON a.outStaffId = f.id WHERE 1=1"
            + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<outjiluVo> GetTeachingSuppliesOutList( @Param("ew") Wrapper wrapper);
}