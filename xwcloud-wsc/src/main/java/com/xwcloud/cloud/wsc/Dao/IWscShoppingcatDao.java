package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.entity.WscShoppingcat;
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
public interface IWscShoppingcatDao extends BaseMapper<WscShoppingcat> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "goodsid", property = "goodsid"),
            @Result(column = "goodsShuxingPriceID", property = "goodsShuxingPriceID"),
            @Result(column = "huodongID", property = "huodongID"),
            @Result(column = "num", property = "num"),
            @Result(column = "addUser", property = "addUser"),
            @Result(column = "addDateTime", property = "addDateTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_shoppingcat"
            + "</script>")
    List<WscShoppingcat> getAllList();

    @Select("<script>" +
            "SELECT *\n" +
            "FROM (SELECT b.goodsName,\n" +
            "\t( SELECT GROUP_CONCAT( dd.shuxingMing SEPARATOR '-' ) FROM wsc_goodsshuxinglist dd WHERE FIND_IN_SET( dd.id, c.goodsShuxingListAll ) > 0 ) guige,\n" +
            "\tIFNULL( c.price, b.basicPrice ) price,\n" +
            "\td.nickName,\n" +
            "\ta.addDateTime ,\n" +
            "\ta.qiyeID,\n" +
            "e.staffName as addUserName " +
            "FROM\n" +
            "\twsc_shoppingcat a\n" +
            "\tJOIN wsc_goods b ON a.goodsID = b.id\n" +
            "\tJOIN wsc_goodsshuxinglistprice c ON a.goodsShuxingPriceID = c.id\n" +
            "\tJOIN wsc_user d ON a.addUser = d.id \n" +
            "join pxstafftable e on a.addUser= d.id " +
            ")a\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<HashMap<String, Object>> getShoppingCatPage(Page page, @Param("ew") QueryWrapper wrapper);

    @Select("<script>" +
            "select a.id id ,a.goodsID,b.img1,b.goodsName,a.num,'false' as selectedAll,b.goodsTypeID goodsTypeID,\n" +
            "( SELECT GROUP_CONCAT( dd.shuxingMing SEPARATOR '-' ) FROM wsc_goodsshuxinglist dd WHERE FIND_IN_SET( dd.id, c.goodsShuxingListAll ) > 0 ) guige,\n" +
            "IFNULL( c.price, b.basicPrice ) price\n" +
            "from wsc_shoppingcat a \n" +
            "LEFT JOIN wsc_goods  b on a.goodsID =b.id\n" +
            "left join wsc_goodsshuxinglistprice c on a.goodsShuxingPriceID=c.id\n" +
            "LEFT JOIN wsc_user d on a.addUser=d.id " +
            " WHERE 1=1  " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<HashMap<String, Object>> getshoppingcartByApp(Page page, @Param("ew") QueryWrapper wrapper);
}