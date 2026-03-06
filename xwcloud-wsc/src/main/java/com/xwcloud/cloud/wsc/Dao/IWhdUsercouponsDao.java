package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.entity.WhdUsercoupons;
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
 * @since 2021-05-25
 */
@Repository
public interface IWhdUsercouponsDao extends BaseMapper<WhdUsercoupons> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "couponsID", property = "couponsID"),
            @Result(column = "type", property = "type"),
            @Result(column = "userID", property = "userID"),
            @Result(column = "givashouming", property = "givashouming"),
            @Result(column = "isUse", property = "isUse"),
            @Result(column = "useDate", property = "useDate"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_usercoupons"
            + "</script>")
    List<WhdUsercoupons> getAllList();

    @Select("<script>" +
            "select a.id id ,a.type as usercoupontype,a.isUse isUse,b.couponsName couponsName,b.Money youhuiMoney,a.addDate,\n" +
            "b.manMoney manMoney,b.type type,b.stratDate stratDate,b.endDate endDate,c.nickName nickName,d.goodsType goodleibie  \n" +
            "from whd_usercoupons a \n" +
            "LEFT JOIN whd_coupons b on a.couponsID=b.id\n" +
            "LEFT JOIN wsc_user c on a.userID=c.id\n" +
            "LEFT JOIN wsc_goodstype d on b.GoodTypeID=d.id" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<HashMap<String, Object>> GetwscUsercouponsPage(Page page, @Param("ew") QueryWrapper wrapper);
}