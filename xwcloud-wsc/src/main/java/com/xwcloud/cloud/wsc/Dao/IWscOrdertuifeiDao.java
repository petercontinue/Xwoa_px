package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.entity.WscOrdertuifei;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-23
 */
@Repository
public interface IWscOrdertuifeiDao extends BaseMapper<WscOrdertuifei> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "orderNumber", property = "orderNumber"),
            @Result(column = "tuikuanTime", property = "tuikuanTime"),
            @Result(column = "tuikuanShuoming", property = "tuikuanShuoming"),
            @Result(column = "tuikuanRen", property = "tuikuanRen"),
            @Result(column = "tuikuanMsg", property = "tuikuanMsg"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "chuliTuihuoTime", property = "chuliTuihuoTime"),
            @Result(column = "chuliTuihuoShuoming", property = "chuliTuihuoShuoming"),
            @Result(column = "chuliTuihuoRen", property = "chuliTuihuoRen"),
            @Result(column = "chuliTuihuoMsg", property = "chuliTuihuoMsg"),
            @Result(column = "tuihuoState", property = "tuihuoState"),
            @Result(column = "tuikuanState", property = "tuikuanState"),
            @Result(column = "chuliTuikuanTime", property = "chuliTuikuanTime"),
            @Result(column = "chuliTuikuanShuoming", property = "chuliTuikuanShuoming"),
            @Result(column = "chuliTuikuanRen", property = "chuliTuikuanRen"),
            @Result(column = "chuliTuikuanMsg", property = "chuliTuikuanMsg"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_ordertuifei"
            + "</script>")
    List<WscOrdertuifei> getAllList();

    @Select("<script>" +
            "SELECT a.id,a.orderNumber,b.receiveName,b.lianxiTel,d.nickName,a.tuikuanShuoming,a.tuikuanTime,a.tuikuanState,a.tuihuoState,a.chuliTuikuanShuoming,a.chuliTuihuoShuoming,\n" +
            "(SELECT COUNT(cc.id) FROM wsc_ordergoods cc WHERE cc.orderNumber=a.orderNumber) goodsCount,b.huodongID,\n" +
            "(SELECT cc.huodongName FROM wsc_huodong cc WHERE cc.id=(SELECT ee.huodongID FROM wsc_huodong_value ee WHERE huodongID=b.huodongID)) huodongName,b.payStyle\n" +
            "FROM wsc_ordertuifei a\n" +
            "JOIN wsc_order b ON a.orderNumber=b.orderNumber\n" +
            "JOIN wsc_ordergoods c ON a.orderNumber=c.orderNumber\n" +
            "JOIN wsc_user d ON b.orderUserID=d.id\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<HashMap<String, Object>> getTuihuokuanPage(Page page, @Param("ew") QueryWrapper wrapper);
}