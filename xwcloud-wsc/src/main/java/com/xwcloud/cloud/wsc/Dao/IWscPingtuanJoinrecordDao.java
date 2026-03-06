package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.WscPingtuanJoinRecordVo;
import com.xwcloud.cloud.model.entity.WscPingtuanJoinrecord;
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
public interface IWscPingtuanJoinrecordDao extends BaseMapper<WscPingtuanJoinrecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "pingtuanGoodsID", property = "pingtuangoodsid"),
                @Result(column = "goodsshuxinglistpriceID", property = "goodsshuxinglistpriceid"),
                @Result(column = "pingtuanFaqiRecordID", property = "pingtuanfaqirecordid"),
                @Result(column = "joinRenOrderID", property = "joinrenorderid"),
                @Result(column = "joinRenWxUserID", property = "joinrenwxuserid"),
                @Result(column = "addTime", property = "addtime"),
                @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_pingtuan_joinrecord"
            + "</script>")
    List<WscPingtuanJoinrecord> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.goodsName, c.originalPrice, f.userName\n" +
            "FROM wsc_pingtuan_joinrecord a\n" +
            "JOIN wsc_goods b ON a.pingtuanGoodsID=b.id\n" +
            "JOIN wsc_goodsshuxinglistprice c ON a.goodsshuxinglistpriceID=c.id\n" +
            "JOIN wsc_pingtuan_faqirecord d ON a.pingtuanFaqiRecordID=d.id\n" +
            "JOIN wsc_order e ON a.joinRenOrderID=e.id\n" +
            "JOIN wsc_user f ON a.joinRenWxUserID=f.id\n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<WscPingtuanJoinRecordVo> getWscPingtuanJoinRecordPage(Page page, @Param("ew") QueryWrapper wrapper);

    @Select("<script>"+"SELECT * FROM wsc_pingtuan_joinrecord AS a LEFT JOIN wsc_user AS b ON a.joinRenWxUserID = b.id " +
            "LEFT JOIN wsc_order AS o ON a.joinRenOrderID = o.id WHERE a.pingtuanGoodsID = ${wscgoodsID}"+"</script>")
    List<WscPingtuanJoinrecord> GetpingtuanJoinList(long wscgoodsID);

}