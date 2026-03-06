package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.WscOrderVo;
import com.xwcloud.cloud.model.Vo.miaoshachenggongVO;
import com.xwcloud.cloud.model.entity.WscOrder;
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
 * @since 2021-01-17
 */
@Repository
public interface IWscOrderDao extends BaseMapper<WscOrder> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "orderNumber", property = "ordernumber"),
            @Result(column = "payMoney", property = "paymoney"),
            @Result(column = "payJifen", property = "payjifen"),
            @Result(column = "couponsID", property = "couponsid"),
            @Result(column = "type", property = "type"),
            @Result(column = "receiveName", property = "receivename"),
            @Result(column = "receiveDizhi", property = "receivedizhi"),
            @Result(column = "lianxiTel", property = "lianxitel"),
            @Result(column = "yxCampusIDs", property = "yxcampusids"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "orderDateTime", property = "orderdatetime"),
            @Result(column = "payStyle", property = "paystyle"),
            @Result(column = "payDateTime", property = "paydatetime"),
            @Result(column = "huodongID", property = "huodongid"),
            @Result(column = "orderFrom", property = "orderfrom"),
            @Result(column = "orderUserID", property = "orderuserid"),
            @Result(column = "orderState", property = "orderstate"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_order"
            + "</script>")
    List<WscOrder> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.nickName orderUserName,\n" +
            "(SELECT COUNT(cc.id) FROM wsc_ordergoods cc WHERE cc.orderNumber=a.orderNumber) goodsCount,\n" +
            "(SELECT cc.huodongName FROM wsc_huodong cc WHERE cc.id=(SELECT ee.huodongID FROM wsc_huodong_value ee WHERE huodongID=a.huodongID)) huodongName\n" +
            "FROM wsc_order a\n" +
            "JOIN wsc_user b ON b.id = a.orderUserID\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<WscOrderVo> getWscOrderPage(Page page, @Param("ew") QueryWrapper wrapper);

    @Select("<script>" +
            "SELECT a.id,a.orderNumber,a.beizhu,a.orderUserID,a.payJifen,a.payMoney,\n" +
            "a.payDateTime,a.orderDateTime,\n" +
            "a.receiveDizhi,a.receiveName,a.lianxiTel,b.userName,\n" +
            "(CASE a.payStyle\n" +
            "\tWHEN 0 THEN '余额支付'\n" +
            "\tWHEN 1 THEN '微信支付'\n" +
            "\tWHEN 2 THEN '积分支付'\n" +
            "\tELSE '-' END )payStyle,\n" +
            "(CASE a.type\n" +
            "\tWHEN 0 THEN '送货上门'\n" +
            "\tWHEN 1 THEN '自取'\n" +
            "\tWHEN 2 THEN '虚拟物品'\n" +
            "\tELSE '-' END )type,\n" +
            "(CASE a.orderState\n" +
            "\tWHEN 1 THEN '待支付'\n" +
            "\tWHEN 2 THEN '未发货'\n" +
            "\tWHEN 3 THEN '待收货'\n" +
            "\tWHEN 4 THEN '已完成'\n" +
            "\tWHEN 5 THEN '退款'\n" +
            "\tWHEN 6 THEN '已关闭'\n" +
            "\tELSE '-' END )orderState,\n" +
            "(SELECT COUNT(cc.id) FROM wsc_ordergoods cc WHERE cc.orderNumber=a.orderNumber) goodsCount,\n" +
            "(SELECT cc.huodongName FROM wsc_huodong cc WHERE cc.id=(SELECT ee.huodongID FROM wsc_huodong_value ee WHERE huodongID=(SELECT dd.huodongID FROM wsc_ordergoods dd WHERE dd.orderNumber=a.orderNumber LIMIT 1))) huodongName,\n" +
            "CONCAT('收货人：',a.receiveName,'，电话：',a.lianxiTel,'，地址：',a.receiveDizhi) receiveDizhi1,\n" +
            "(SELECT GROUP_CONCAT(CONCAT('商品：',IFNULL(bb.goodsName,'-'),'，购买数：',aa.nums,'，规格：',IF(aa.huodongID=0,IFNULL((SELECT GROUP_CONCAT(dd.shuxingMing SEPARATOR '-') FROM wsc_goodsshuxinglist dd WHERE FIND_IN_SET(dd.id,cc.goodsShuxingListAll)>0),'-'),IFNULL((SELECT bb.huodongName FROM wsc_huodong bb WHERE (SELECT ee.huodongID FROM wsc_huodong_value ee WHERE ee.id=aa.huodongID LIMIT 1)=bb.id LIMIT 1),'-'))) SEPARATOR '；')\n" +
            "FROM wsc_ordergoods aa \n" +
            "LEFT JOIN wsc_goods bb ON bb.id=aa.goodsID\n" +
            "JOIN wsc_goodsshuxinglistprice cc ON aa.goodsshuxinglistpriceID=cc.id)goodsName " +
            "FROM wsc_order a\n" +
            "JOIN wsc_user b ON a.orderUserID=b.id" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<HashMap<String, Object>> getWscOrderList(@Param("ew") QueryWrapper wrapper);

    /**
     * 分页查询流水信息
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.id AS liushuiID,b.orderNumber,a.shouruMoney,a.zhichuMoney,a.luruTime,a.liushuiZaiyao FROM pxliushuizhangtable AS a \n" +
            "LEFT JOIN wsc_order AS b ON a.orderNumber = b.orderNumber\n" +
            "WHERE a.shouzhiStyleID = 5" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<HashMap<String, Object>> GetsqleLiushuiPages(Page page, @Param("ew") QueryWrapper wrapper);

    /**
     * 获取订单
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "SELECT (select kehucompanyname from oa_kehu where id =a.qiyeID) qyName,\n" +
            "e.img1 goodsUrl,e.goodsName title,c.payMoney,c.nums,a.orderDateTime,a.id id,e.id goodID,e.goodsTypeID goodsTypeID,\n" +
            "(SELECT GROUP_CONCAT( dd.shuxingMing SEPARATOR '-' ) FROM wsc_goodsshuxinglist dd WHERE FIND_IN_SET( dd.id, d.goodsShuxingListAll )) guige\n" +
            "FROM wsc_order a\n" +
            "JOIN wsc_user b ON b.id = a.orderUserID\n" +
            "JOIN wsc_ordergoods c ON a.id = c.orderNumber \n" +
            " LEFT JOIN wsc_goodsshuxinglistprice d ON c.goodsshuxinglistpriceID = d.goodsShuxingListAll\n" +
            "JOIN wsc_goods e on c.goodsID=e.id \n" +
            "WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<HashMap<String, Object>> GetorderByUser(Page page, @Param("ew") QueryWrapper wrapper);

    /**
     * 查询秒杀成功信息
     *
     * @param qiyeID
     * @param goodsID
     * @return
     */
    @Select("<script>" + "SELECT u.nickName,u.headImage,b.payDateTime,b.payMoney FROM wsc_ordergoods AS a LEFT JOIN wsc_order AS b on a.orderNumber = b.orderNumber \n" +
            "LEFT JOIN wsc_user AS u ON b.orderUserID = u.id\n" +
            "WHERE a.qiyeID = #{qiyeID} AND a.goodsID = #{goodsID} AND b.huodongID = 3" + "</script>")
    List<miaoshachenggongVO> GetmiaoshachenggongInfo(long qiyeID, long goodsID);
}