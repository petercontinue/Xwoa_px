package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.WscGoodsVo;
import com.xwcloud.cloud.model.entity.WscGoods;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistpricePingtuan;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Repository
public interface IWscGoodsDao extends BaseMapper<WscGoods> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "goodsName", property = "goodsname"),
                @Result(column = "goodsTypeID", property = "goodstypeid"),
                @Result(column = "basicPrice", property = "basicprice"),
                @Result(column = "jifenPrice", property = "jifenprice"),
                @Result(column = "huodongID", property = "huodongid"),
                @Result(column = "huodongStartTime", property = "huodongstarttime"),
                @Result(column = "huodongEndTime", property = "huodongendtime"),
                @Result(column = "onlyTimeBuyPrice", property = "onlytimebuyprice"),
                @Result(column = "kanjiaOniceMinNum", property = "kanjiaoniceminnum"),
                @Result(column = "kanjiaOniceMaxNum", property = "kanjiaonicemaxnum"),
                @Result(column = "kanjiaSuccessPrice", property = "kanjiasuccessprice"),
                @Result(column = "goodInfo", property = "goodinfo"),
                @Result(column = "img1", property = "img1"),
                @Result(column = "img2", property = "img2"),
                @Result(column = "img3", property = "img3"),
                @Result(column = "img4", property = "img4"),
                @Result(column = "img5", property = "img5"),
                @Result(column = "addUser", property = "adduser"),
                @Result(column = "addTime", property = "addtime"),
                @Result(column = "shangjiaState", property = "shangjiastate"),
                @Result(column = "text", property = "text"),
                @Result(column = "fxNum", property = "fxnum"),
                @Result(column = "maxNum", property = "maxnum"),
                @Result(column = "goodsShuomingOne", property = "goodsshuomingone"),
                @Result(column = "goodsShuomingOneIsBold", property = "goodsshuomingoneisbold"),
                @Result(column = "goodsShuomingOneFontColor", property = "goodsshuomingonefontcolor"),
                @Result(column = "goodsShuomingTwo", property = "goodsshuomingtwo"),
                @Result(column = "goodsShuomingTwoIsBold", property = "goodsshuomingtwoisbold"),
                @Result(column = "goodsShuomingTwoFontColor", property = "goodsshuomingtwofontcolor"),
                @Result(column = "cartNum", property = "cartnum"),
                @Result(column = "campusIDs", property = "campusids"),
                @Result(column = "style", property = "style"),
                @Result(column = "paixu", property = "paixu"),
                @Result(column = "qiyeID", property = "qiyeid"),
                @Result(column = "isAutoQiandan", property = "isautoqiandan"),
                @Result(column = "fidFanyongBili", property = "fidfanyongbili"),
                @Result(column = "gfidFanyongBili", property = "gfidfanyongbili"),
                @Result(column = "fabuTo", property = "fabuto"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_goods"
            + "</script>")
    List<WscGoods> getAllList();

    /**
     * 分页查询商品信息
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.*,b.goodsType,c.huodongName,(SELECT price FROM wsc_goodsshuxinglistprice WHERE goodsID = a.id ORDER BY price LIMIT 1) AS priceShuxing, \n" +
            "(SELECT jifenPrice FROM wsc_goodsshuxinglistprice WHERE goodsID = a.id ORDER BY jifenPrice LIMIT 1) AS jifenPriceShuxing,a.goodsTypeID goodsTypeID,\n" +
            "(SELECT onlyTimeBuyPrice FROM wsc_goodsshuxinglistprice WHERE goodsID = a.id ORDER BY onlyTimeBuyPrice LIMIT 1) AS onlyTimeBuyPriceShuxing,\n" +
            "(SELECT kanjiaSuccessPrice FROM wsc_goodsshuxinglistprice WHERE goodsID = a.id ORDER BY kanjiaSuccessPrice LIMIT 1) AS kanjiaSuccessPriceShuxing,\n" +
            "(SELECT COUNT(*) FROM wsc_shoppingcat WHERE goodsID = a.id) AS gwcshuliang ,\n" +
            "(SELECT (case WHEN COUNT(id)>0 THEN COUNT(id) ELSE 0 END) from wsc_ordergoods where goodsID=a.id) Sales "+
            "FROM wsc_goods AS a  \n" +
            "LEFT JOIN wsc_goodstype AS b ON a.goodsTypeID = b.id \n" +
            "LEFT JOIN wsc_huodong AS c ON a.huodongID = c.id"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<WscGoodsVo> getWscGoodsPage(Page page, @Param("ew") QueryWrapper wrapper);


    /**
     * 查询微商城活动的商品信息
     * @param wrapper
     * @return
     */
    @Select("<script>"+
            "SELECT a.*,a.huodongEndTime as huodongEnddate,(UNIX_TIMESTAMP(a.huodongEndTime)-UNIX_TIMESTAMP(NOW())) as shijianchuo,a.goodsTypeID,b.goodsType,c.huodongName,(SELECT price FROM wsc_goodsshuxinglistprice WHERE goodsID = a.id ORDER BY price LIMIT 1) AS priceShuxing, \n" +
            "            (SELECT jifenPrice FROM wsc_goodsshuxinglistprice WHERE goodsID = a.id ORDER BY jifenPrice LIMIT 1) AS jifenPriceShuxing,\n" +
            "            (SELECT onlyTimeBuyPrice FROM wsc_goodsshuxinglistprice WHERE goodsID = a.id ORDER BY onlyTimeBuyPrice LIMIT 1) AS onlyTimeBuyPriceShuxing,\n" +
            "            (SELECT kanjiaSuccessPrice FROM wsc_goodsshuxinglistprice WHERE goodsID = a.id ORDER BY kanjiaSuccessPrice LIMIT 1) AS kanjiaSuccessPriceShuxing,\n" +
            "            (SELECT COUNT(*) FROM wsc_shoppingcat WHERE goodsID = a.id) AS gwcshuliang ,\n" +
            "            (SELECT (case WHEN COUNT(id)>0 THEN COUNT(id) ELSE 0 END) from wsc_ordergoods where goodsID=a.id) Sales, \n" +
            "(SELECT MIN(onlyTimeBuyPrice) FROM wsc_goodsshuxinglistprice WHERE goodsID = a.id) AS qiangoujia" +
            "            FROM wsc_goods AS a  \n" +
            "            LEFT JOIN wsc_goodstype AS b ON a.goodsTypeID = b.id \n" +
            "            LEFT JOIN wsc_huodong AS c ON a.huodongID = c.id"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    List<WscGoodsVo> GetAllGoodsListByHuodongID( @Param("ew") QueryWrapper wrapper);

    @Select("<script>"+"SELECT a.* from  wsc_goodsshuxinglistprice_pingtuan AS a\n" +
            "LEFT JOIN wsc_goodsshuxinglistprice AS b ON a.goodsShuxingListPriceID = b.id\n" +
            "LEFT JOIN wsc_goods AS c ON b.goodsID = c.id  WHERE b.goodsID = #{goodsID}"+"</script>")
    List<WscGoodsshuxinglistpricePingtuan> getshuxinglistpingtuanPricebygoodsID(long goodsID);

    @Select("<script>"+"SELECT a.*,(SELECT (case WHEN COUNT(id)>0 THEN COUNT(id) ELSE 0 END) from wsc_ordergoods where goodsID=a.id) Sales FROM wsc_goods AS a  " +
            "WHERE a.id =#{goodsID}"+"</script>")
    List<HashMap<String, Object>> GetGoodsDetail(String goodsID);

}