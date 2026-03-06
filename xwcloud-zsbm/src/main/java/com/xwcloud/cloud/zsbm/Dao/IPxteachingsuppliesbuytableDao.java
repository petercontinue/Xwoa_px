package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.caigoushenqingVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesbuytable;
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
 * @since 2020-11-23
 */
@Repository
public interface IPxteachingsuppliesbuytableDao extends BaseMapper<Pxteachingsuppliesbuytable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "shangpingName", property = "shangpingName"),
            @Result(column = "shangpingTypeID", property = "shangpingTypeID"),
            @Result(column = "guigeID", property = "guigeID"),
            @Result(column = "buyNum", property = "buyNum"),
            @Result(column = "danwei", property = "danwei"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "isShenhe", property = "isShenhe"),
            @Result(column = "shenheNopassReason", property = "shenheNopassReason"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachingsuppliesbuytable"
            + "</script>")
    List<Pxteachingsuppliesbuytable> getAllList();

    @Select("<script>" + "SELECT a.campusID,f.campusName,s.staffName,a.beizhu,\n" +
            "                                        a.id,\n" +
            "                                        a.shangpingName,\n" +
            "                                        a.guigeID,\n" +
            "                                        b.typeName,\n" +
            "                                        a.addDate,\n" +
            "                                        a.shangpingTypeID,\n" +
            "                                        a.addStaffID,\n" +
            "                                        a.isshenhe,\n" +
            "                                       a.buyNum,\n" +
            "                                        a.danwei,\n" +
            "                                       a.shenheNopassReason\n" +
            "                                       FROM pxteachingsuppliesbuytable AS a \n" +
            "            LEFT JOIN pxteachingsuppliestypetable AS b ON a.shangpingTypeID=b.id\n" +
            "            LEFT JOIN pxstafftable AS s ON a.addStaffId=s.id\n" +
            "            LEFT JOIN pxcampustable AS f ON a.campusID=f.id WHERE f.isOpen !=2"
            + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<caigoushenqingVo> GetTeachingSuppliesbuyPages(Page page, @Param("ew") Wrapper wrapper);
}