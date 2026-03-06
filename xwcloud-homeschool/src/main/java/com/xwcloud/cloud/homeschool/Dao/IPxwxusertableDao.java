package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.PxwxusertableVo;
import com.xwcloud.cloud.model.entity.Pxwxusertable;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxwxusertableDao extends BaseMapper<Pxwxusertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "unidid", property = "unidid"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "nickName", property = "nickName"),
            @Result(column = "headImage", property = "headImage"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "diqu", property = "diqu"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxwxusertable"
            + "</script>")
    List<Pxwxusertable> getAllList();

    @Select("<script>" +
            "SELECT\n" +
            "\ta.*,\n" +
            "\td.id,\n" +
            "\td.campusName,\n" +
            "\tb.zidingyiStuID,\n" +
            "\tb.stuName,\n" +
            "\tc.staffName AS banzhurenName,\n" +
            "\tb.parentTel,\n" +
            "\tb.activity,\n" +
            "\tb.id AS newstuID,\n" +
            "\t( SELECT nickName FROM wsc_user WHERE phoneNumber = b.parentTel LIMIT 1 ) AS wscName \n" +
            "FROM\n" +
            "\tpxstutable AS b\n" +
            "\tLEFT JOIN pxwxusertable AS a ON a.tel = b.parentTel\n" +
            "\tLEFT JOIN pxstafftable AS c ON b.banzhurenTeacherID = c.id\n" +
            "\tLEFT JOIN pxcampustable AS d ON b.campusID = d.id " +
            "WHERE b.buxiStateID !=1 and d.isOpen !=2"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxwxusertableVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT wxuser.*,stu.campusID,campus.campusName,stu.zidingyiStuID,stu.stuName," +
            "banzhuren.campusName as banzhurenName,stu.parentTel,stu.activity,stu.id as stuID " +
            "FROM pxstutable stu  " +
            "LEFT JOIN pxwxusertable wxuser ON stu.parentTel = wxuser.tel  " +
            "LEFT JOIN pxcampustable campus ON stu.campusID = campus.id " +
            "LEFT JOIN pxcampustable banzhuren ON banzhuren.id= stu.banzhurenTeacherID"+
            " WHERE campus.isOpen !=2" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxwxusertableVo> getJoinList( @Param("ew") Wrapper wrapper);



}