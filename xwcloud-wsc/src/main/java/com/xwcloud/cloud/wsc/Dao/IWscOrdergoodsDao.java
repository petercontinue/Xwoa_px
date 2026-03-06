package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.WscOrderGoodsVo;
import com.xwcloud.cloud.model.entity.WscGoods;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistprice;
import com.xwcloud.cloud.model.entity.WscOrdergoods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Repository
public interface IWscOrdergoodsDao extends BaseMapper<WscOrdergoods> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "orderNumber", property = "ordernumber"),
            @Result(column = "goodsID", property = "goodsid"),
            @Result(column = "goodsshuxinglistpriceID", property = "goodsshuxinglistpriceid"),
            @Result(column = "pingtuanFaqiRenID", property = "pingtuanfaqirenid"),
            @Result(column = "huodongID", property = "huodongid"),
            @Result(column = "nums", property = "nums"),
            @Result(column = "payMoney", property = "paymoney"),
            @Result(column = "pingjia", property = "pingjia"),
            @Result(column = "pingjiaType", property = "pingjiatype"),
            @Result(column = "pingjiaDate", property = "pingjiadate"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_ordergoods"
            + "</script>")
    List<WscOrdergoods> getAllList();


    @Select("<script>" +
            "select a.*, b.userName ptFaqirenName\n" +
            "from wsc_ordergoods a\n" +
            "left join wsc_user b on a.pingtuanFaqiRenID=b.id\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    @Results(id = "WscOrderGoodsVoMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "orderNumber", property = "ordernumber"),
            @Result(column = "goodsID", property = "goodsid"),
            @Result(column = "goodsshuxinglistpriceID", property = "goodsshuxinglistpriceid"),
            @Result(column = "pingtuanFaqiRenID", property = "pingtuanfaqirenid"),
            @Result(column = "huodongID", property = "huodongid"),
            @Result(column = "nums", property = "nums"),
            @Result(column = "payMoney", property = "paymoney"),
            @Result(column = "pingjia", property = "pingjia"),
            @Result(column = "pingjiaType", property = "pingjiatype"),
            @Result(column = "pingjiaDate", property = "pingjiadate"),
            @Result(column = "qiyeID", property = "qiyeid"),
            @Result(column = "goodsID", property = "goods", javaType = WscGoods.class, one = @One(select = "IWscOrdergoodsDao.getWscGoodsByID")),
            @Result(column = "goodsshuxinglistpriceID", property = "goodsshuxinglistprice", javaType = WscGoodsshuxinglistprice.class,
                    one = @One(select = "IWscOrdergoodsDao.getWscGoodsAttrPriceByID")),
            @Result(column = "ptFaqirenName", property = "ptFaqirenName"),
    })
//    @ResultMap({"BaseResultMap", "WscOrderGoodsVoMap"})
    Page<WscOrderGoodsVo> getWscOrderGoodsPage(Page page, @Param("ew") QueryWrapper wrapper);

    @Select("select * from wsc_goods where id = #{id}")
    WscGoods getWscGoodsByID(long id);

    @Select("select * from wsc_goodsshuxinglistprice where id = #{id}")
    WscGoods getWscGoodsAttrPriceByID(long id);

    @Select("<script>" +
            "SELECT IFNULL(bb.goodsName,'-')goodsName,\n" +
            "IF(aa.huodongID=0,IFNULL((SELECT GROUP_CONCAT(dd.shuxingMing SEPARATOR '-') FROM wsc_goodsshuxinglist dd WHERE FIND_IN_SET(dd.id,cc.goodsShuxingListAll)>0),'-'),IFNULL((SELECT bb.huodongName FROM wsc_huodong bb WHERE (SELECT ee.huodongID FROM wsc_huodong_value ee WHERE aa.huodongID=ee.id LIMIT 1)=bb.id LIMIT 1),'-'))goodsParam,\n" +
            "aa.nums,aa.pingjia,aa.pingjiaDate,aa.pingjiaType,aa.payMoney\n" +
            "FROM wsc_ordergoods aa \n" +
            "LEFT JOIN wsc_goods bb ON bb.id=aa.goodsID\n" +
            "JOIN wsc_goodsshuxinglistprice cc ON aa.goodsshuxinglistpriceID=cc.id\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<HashMap<String, Object>> getOrderGoodsDetailByOrderNumberPage(Page page, @Param("ew") QueryWrapper wrapper);

    /**
     * 分页查询评价信息
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT b.orderNumber,d.goodsName," +
            "(SELECT GROUP_CONCAT(shuxingMing) FROM wsc_goodsshuxinglist WHERE id REGEXP (SELECT REPLACE ((SELECT goodsShuxingListAll " +
            "FROM wsc_goodsshuxinglistprice WHERE id=e.id ),',','|'))) AS shuxingName,a.pingjia,a.pingjiaType,a.pingjiaDate,c.nickName " +
            "FROM wsc_ordergoods AS a \n" +
            "LEFT JOIN wsc_order AS b ON a.orderNumber = b.orderNumber\n" +
            "LEFT JOIN wsc_user AS c ON b.orderUserID = c.id\n" +
            "LEFT JOIN wsc_goods AS d ON a.goodsID = d.id\n" +
            "LEFT JOIN wsc_goodsshuxinglistprice AS e ON a.goodsShuxingListPriceID = e.id " + "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<HashMap<String, Object>> GetAllOrderPingjiaPage(Page page, @Param("ew") QueryWrapper wrapper);


    @Select("<script>"+
            "SELECT a.pingjia,a.pingjiaDate,a.pingjiaType,c.nickName,c.headImage\n" +
            "from wsc_ordergoods a \n" +
            "join wsc_order b on a.orderNumber=b.orderNumber\n" +
            "join wsc_user c on b.orderUserID=c.id\n" +
            "where 1=1 \n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<HashMap<String,Object>>getonegoodpingjia(@Param("ew") QueryWrapper queryWrapper);
}