package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.getclassVo;
import com.xwcloud.cloud.model.Vo.kcInfoVo;
import com.xwcloud.cloud.model.entity.Pxkechengtable;

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
            @Result(column = "buyZonjia", property = "buyZonjia"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "ZSid", property = "ZSid"),
            @Result(column = "jifeiStyleID", property = "jifeiStyleID"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "bgColor", property = "bgColor"),
            @Result(column = "perdaysqj", property = "perdaysqj"),
            @Result(column = "perkeshiqj", property = "perkeshiqj"),
            @Result(column = "qingjiaTimes", property = "qingjiaTimes"),
            @Result(column = "iskoukeshi", property = "iskoukeshi"),
    })
    @Select("<script>" +
            "SELECT * from  pxkechengtable"
            + "</script>")
    List<Pxkechengtable> getAllList();

    /**
     * (余额消课获取课程)
     * 获取科目课程
     *
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as id,a.kechengName as kechengName,c.id as buxistyle,c.buxiStyleName as buxiStyleName,b.subjectName as subjectName,\n" +
            "concat(concat(d.campusName ,'_', b.subjectName),':',a.kechengName) as text\n" +
            "from pxkechengtable as a\n" +
            "LEFT JOIN pxsubjecttable as b on a.subjectID=b.id\n" +
            "LEFT JOIN pxbuxistyletable as c on a.buxiStyleID=c.id\n" +
            "LEFT JOIN pxcampustable as d on a.campusID=d.id\n" +
            "where a.isShow=1 and d.isOpen !=2 and a.qiyeID=#{qiyeID} \n"
            + "</script>")
    List<kcInfoVo> getnewkcInfoList(Long qiyeID);

    /**
     * 下拉获取课程
     *
     * @param campusID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT id id,kechengName name from  pxkechengtable where campusID=#{campusID} and qiyeID=#{qiyeID} "
            + "</script>")
    List<getclassVo> getkcBycampus(Long campusID, Long qiyeID);


    @Select("<script>" +
            "SELECT DISTINCT\n" +
            "a.id id,\n" +
            "concat(\n" +
            "concat( concat( '(', '', d.campusName ), '_', concat( b.subjectName, '', ')' ) ), ' ',a.kechengName ) name \n" +
            "FROM pxkechengtable AS a\n" +
            "\tLEFT JOIN pxbuxistyletable AS c ON a.buxiStyleID = c.id\n" +
            "\tLEFT JOIN pxsubjecttable AS b ON a.subjectID = b.id\n" +
            "\tLEFT JOIN pxcampustable AS d ON a.campusID = d.id " +
            "where 1=1 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<getclassVo> getKcToYueXiaoKe(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 根据科目查询任教老师信息
     * @param teachSubjectID
     * @param qiyeID
     * @return
     */
    @Select("<script>"+
            "SELECT a.staffID AS id,b.staffName AS name " +
            "FROM pxteachsubjecttable AS a " +
            "LEFT JOIN pxstafftable AS b ON a.staffID = b.id " +
            "WHERE a.teachSubjectID=#{teachSubjectID} AND a.qiyeID =#{qiyeID}\n"+
            "</script>")
    List<getclassVo>GetRenkeTeacherList(long teachSubjectID,long qiyeID);
}