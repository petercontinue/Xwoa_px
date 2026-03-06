package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.WscPingtuanFaqiRecordVo;
import com.xwcloud.cloud.model.entity.WscPingtuanFaqirecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
public interface IWscPingtuanFaqirecordDao extends BaseMapper<WscPingtuanFaqirecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "pingtuanGoodsID", property = "pingtuangoodsid"),
                @Result(column = "goodsshuxinglistpriceID", property = "goodsshuxinglistpriceid"),
                @Result(column = "pingtuanFaqiRenWxUserID", property = "pingtuanfaqirenwxuserid"),
                @Result(column = "faqiRenOrderID", property = "faqirenorderid"),
                @Result(column = "addTime", property = "addtime"),
                @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_pingtuan_faqirecord"
            + "</script>")
    List<WscPingtuanFaqirecord> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.userName, c.goodsName, d.originalPrice\n" +
            "FROM wsc_pingtuan_faqirecord a\n" +
            "JOIN wsc_user b ON a.pingtuanFaqiRenWxUserID=b.id\n" +
            "JOIN wsc_goods c ON a.pingtuanGoodsID=c.id\n" +
            "JOIN wsc_goodsshuxinglistprice d ON a.goodsshuxinglistpriceID=d.id\n" +
            "JOIN wsc_order e ON a.faqiRenOrderID=e.id\n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<WscPingtuanFaqiRecordVo> getWscPingtuanFaqiPage(Page page, @Param("ew") QueryWrapper queryWrapper);


    @Select("<script>"+"SELECT a.*,b.nickName,b.headImage,(SELECT COUNT(*) FROM wsc_pingtuan_joinrecord AS c WHERE c.pingtuanFaqiRecordID = a.id) AS joinCount  FROM wsc_pingtuan_faqirecord AS a LEFT JOIN wsc_user " +
            "AS b ON a.pingtuanFaqiRenWxUserID = b.id WHERE a.pingtuanGoodsID=#{wscgoodsID}"+"</script>")
    List<WscPingtuanFaqirecord> GetFaqipingtuanlist(long wscgoodsID);

    @Select("<script>"+"SELECT a.*,b.nickName,b.headImage FROM wsc_pingtuan_faqirecord AS a LEFT JOIN wsc_user " +
            "AS b ON a.pingtuanFaqiRenWxUserID = b.id WHERE a.pingtuanGoodsID=#{wscgoodsID} AND a.state = 1"+"</script>")
    List<WscPingtuanFaqirecord> GetFaqipingtuanSuccesslist(long wscgoodsID);
}