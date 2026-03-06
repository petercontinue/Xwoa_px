package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.bixikechengxialaVO;
import com.xwcloud.cloud.model.entity.Pxkechengtable;

import com.xwcloud.cloud.model.Vo.kechengListVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
@Repository
public interface IPxkechengtableDao extends BaseMapper<Pxkechengtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "kechengName", property = "kechengName"),
            @Result(column = "subjectID", property = "subjectID"),
            @Result(column = "buxiStyleID", property = "buxiStyleID"),
            @Result(column = "is1v1KC", property = "is1v1KC"),
            @Result(column = "classTimeStyleID", property = "classTimeStyleID"),
            @Result(column = "kechengOriginalPrice", property = "kechengOriginalPrice"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "byMonthOrDay", property = "byMonthOrDay"),
            @Result(column = "buyZonjia", property = "buyZonjia"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "ZSid", property = "ZSid"),
            @Result(column = "jifeiStyleID", property = "jifeiStyleID"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "bgColor", property = "bgColor"),
            @Result(column = "kechengImg", property = "kechengImg"),
            @Result(column = "kechengbeizhu", property = "kechengbeizhu"),
            @Result(column = "kechengcontent", property = "kechengcontent"),
            @Result(column = "showInApp", property = "showInApp")
    })
    @Select("<script>" +
            "SELECT * from  pxkechengtable"
            + "</script>")
    List<Pxkechengtable> getAllList();

    //查询课程信息(分页)
    @Select("<script>" + "SELECT a.id,e.campusName,c.subjectName,a.kechengName,a.isShow,a.jifeiStyleID,d.buxiStyleName,a.kechengImg,a.kechengbeizhu,\n" +
            "                case when b.classTimeStyleName=-1 then '一次' when b.classTimeStyleName=-2 then '一天' else CONCAT(b.classTimeStyleName,'分钟') END AS classTimeStyleName,\n" +
            "               a.kechengOriginalPrice,a.kechengprice,a.keshiNum,a.buyZonjia,a.iskoukeshi,a.qingjiaTimes,(SELECT COUNT(*) FROM pxkechengcontenttable WHERE kechengID = a.id) AS kechengcontent\n" +
            "\t\t\t\t\t\t\t FROM pxkechengtable as a LEFT JOIN pxclasstimestyletable as b on a.classTimeStyleID = b.id\n" +
            "                       LEFT JOIN pxsubjecttable as c on a.subjectID = c.id\n" +
            "                        LEFT JOIN pxbuxistyletable as d on a.buxiStyleID = d.id\n" +
            "                        LEFT JOIN pxcampustable as e on a.campusID=e.id WHERE e.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<kechengListVo> getAllKechengPages(Page page, @Param("ew") Wrapper wrapper);

    //查询课程信息
    @Select("<script>" + "SELECT * FROM pxkechengtable as a LEFT JOIN pxclasstimestyletable as b on a.classTimeStyleID = b.id\n" +
            "LEFT JOIN pxsubjecttable as c on a.subjectID = c.id\n" +
            "LEFT JOIN pxbuxistyletable as d on a.buxiStyleID = d.id\n" +
            "LEFT JOIN pxcampustable as e on a.campusID=e.id" + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<kechengListVo> getAllKechengList(@Param("ew") Wrapper wrapper);

    @Update("<script>" + "UPDATE pxkechengtable SET isShow = #{State} where id= #{Id}" + "</script>")
    int UpdateKechengState(Long Id, boolean State);

    @Select("<script>" + "SELECT * FROM pxkechengtable WHERE id = #{Id} ORDER BY id LIMIT 0,1;  \n" + "</script>")
    Pxkechengtable GetKechengById(Long Id);

    /**
     * 根据校区，计费方式查询课程信息
     *
     * @param campusID
     * @param qiyeID
     * @param jifeiStyleID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id,a.kechengName,b.buxiStyleName,c.subjectName,a.keshiNum,a.buyZonjia AS zongjia,a.subjectID,a.buxiStyleID AS buxistyleid,a.byMonthOrDay byMonthOrDay  " +
            " FROM pxkechengtable AS a" +
            " LEFT JOIN pxbuxistyletable AS b ON a.buxiStyleID = b.id LEFT JOIN pxsubjecttable AS c ON a.subjectID = c.id " +
            "WHERE a.campusID = #{campusID} AND a.qiyeID = #{qiyeID} AND a.jifeiStyleID =#{jifeiStyleID} and a.isShow=1 " + "</script>")
    List<bixikechengxialaVO> GetBuxikechengByCampusID(long campusID, long qiyeID, Integer jifeiStyleID);


    //--------------意向学员

    @Select("<script>" +
            "SELECT a.*\n" +
            "FROM pxkechengtable a\n" +
            "JOIN pxbuxistyletable b ON a.buxiStyleID=b.id\n" +
            "WHERE b.buxiStyleName='一对一'\n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxkechengtable> getYxChabanKc(@Param("ew") QueryWrapper<Pxkechengtable> wrapper);
}