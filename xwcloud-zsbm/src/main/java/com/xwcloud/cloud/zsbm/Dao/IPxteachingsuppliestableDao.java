package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.qiandanwpVO;
import com.xwcloud.cloud.model.Vo.teachingSuppliesVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliestable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
public interface IPxteachingsuppliestableDao extends BaseMapper<Pxteachingsuppliestable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "name", property = "name"),
            @Result(column = "typeId", property = "typeId"),
            @Result(column = "specs", property = "specs"),
            @Result(column = "StockNum", property = "StockNum"),
            @Result(column = "StockUnit", property = "StockUnit"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "yanshouStaffId", property = "yanshouStaffId"),
            @Result(column = "rukuShuoming", property = "rukuShuoming"),
            @Result(column = "buyPrice", property = "buyPrice"),
            @Result(column = "salePrice", property = "salePrice"),
            @Result(column = "kucunyujing", property = "kucunyujing"),
            @Result(column = "IsQiYong", property = "IsQiYong"),
            @Result(column = "changpinTiaoma", property = "changpinTiaoma"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachingsuppliestable"
            + "</script>")
    List<Pxteachingsuppliestable> getAllList();

    @Select("<script>" + "SELECT * FROM pxteachingsuppliestable WHERE name = #{Name}" + "</script>")
    Pxteachingsuppliestable GetTeachingSuppliesByName(String Name);

    @Update("<script>" + "UPDATE pxteachingsuppliestable SET StockNum = #{kucun} WHERE id= #{ID}" + "</script>")
    int UpdateteachingsuppliesKucun(Long ID, BigDecimal kucun);

    @Select("<script>" + "SELECT * FROM pxteachingsuppliestable WHERE 1=1"  +
            "<if test='changpinTiaoma!=null'>" +
            "AND changpinTiaoma=#{changpinTiaoma}" +
            "</if>"+
            "</script>")
    List<Pxteachingsuppliestable> getTeachingSuppliesByTiaoma(String changpinTiaoma);

    @Select("<script>" + "SELECT * FROM pxteachingsuppliestable AS a\n" +
            "LEFT JOIN pxteachingsuppliestypetable AS b ON a.typeId= b.id\n" +
            "LEFT JOIN pxcampustable AS d ON a.campusID=d.id " + "WHERE 1 = 1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<teachingSuppliesVo> GetTeachingSuppliesPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT * FROM pxteachingsuppliestable " + "WHERE 1 = 1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Pxteachingsuppliestable getTeachingSupplies(@Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT * FROM pxteachingsuppliestable AS a\n" +
            "LEFT JOIN pxteachingsuppliestypetable AS b ON a.typeId=b.id\n" +
            "LEFT JOIN pxcampustable AS c ON a.campusID = c.id" + " WHERE 1 = 1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<teachingSuppliesVo> GetTeachingSuppliesList(@Param("ew") Wrapper wrapper);

    /**
     * 查询所有物品信息
     * @param campusID
     * @param qiyeID
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxteachingsuppliestable AS a WHERE a.IsQiYong= true AND a.campusID=#{campusID} AND a.qiyeID = #{qiyeID}"+"</script>")
    List<qiandanwpVO> getAllWupingList(long campusID,long qiyeID);

}