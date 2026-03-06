package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.WscKanjiaFaqiRecordVo;
import com.xwcloud.cloud.model.Vo.faqikanjiaVO;
import com.xwcloud.cloud.model.entity.WscKanjiaFaqirecord;
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
public interface IWscKanjiaFaqirecordDao extends BaseMapper<WscKanjiaFaqirecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "kanjiaGoodsID", property = "kanjiagoodsid"),
                @Result(column = "goodsshuxinglistpriceID", property = "goodsshuxinglistpriceid"),
                @Result(column = "kanjiaFaqiRenWxUserID", property = "kanjiafaqirenwxuserid"),
                @Result(column = "minMoney", property = "minmoney"),
                @Result(column = "startMoney", property = "startmoney"),
                @Result(column = "currentMoney", property = "currentmoney"),
                @Result(column = "addTime", property = "addtime"),
                @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_kanjia_faqirecord"
            + "</script>")
    List<WscKanjiaFaqirecord> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.userName, c.goodsName, d.kanjiaSuccessPrice, d.originalPrice\n" +
            "FROM wsc_kanjia_faqirecord a\n" +
            "JOIN wsc_user b ON a.kanjiaFaqiRenWxUserID = b.id\n" +
            "JOIN wsc_goods c ON a.kanjiaGoodsID = c.id\n" +
            "JOIN wsc_goodsshuxinglistprice d ON a.goodsshuxinglistpriceID = d.id\n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<WscKanjiaFaqiRecordVo> getWscKanjiaFaqiRecordPage(Page page, @Param("ew") QueryWrapper wrapper);


    @Select("<script>" + "select a.*,b.*,(SELECT COUNT(*) FROM wsc_kanjia_bangkanrecord WHERE kanjiaFaqiID = a.id)AS bangkancount FROM wsc_kanjia_faqirecord AS a \n" +
            "LEFT JOIN wsc_user AS b ON a.kanjiaFaqiRenWxUserID = b.id \n" +
            "WHERE a.kanjiaGoodsID = #{kanjiaGoodsID} AND a.state = 0" + "</script>")
    List<WscKanjiaFaqirecord> GetfaqiKanjiaInfoByGoodsID(long kanjiaGoodsID);

    @Select("<script>" + "select a.*,b.*,(SELECT COUNT(*) FROM wsc_kanjia_bangkanrecord WHERE kanjiaFaqiID = a.id)AS bangkancount FROM wsc_kanjia_faqirecord AS a \n" +
            "LEFT JOIN wsc_user AS b ON a.kanjiaFaqiRenWxUserID = b.id \n" +
            "WHERE a.kanjiaGoodsID = #{kanjiaGoodsID} AND a.state != 0" + "</script>")
    List<WscKanjiaFaqirecord> GetfaqiKanjiaSuccessInfoByGoodsID(long kanjiaGoodsID);

    @Select("<script>"+"select (SELECT COUNT(*) FROM wsc_kanjia_faqirecord AS b WHERE b.addTime <![CDATA[>=]]> a.huodongStartTime AND b.addTime <![CDATA[<=]]> a.huodongEndTime " +
            "AND b.kanjiaGoodsID = a.id AND b.kanjiaFaqiRenWxUserID = #{wscUserID}) AS faqiCount FROM wsc_goods AS a WHERE a.id = #{goodsID}"+"</script>")
    List<faqikanjiaVO> panduanDangqianweixinyonghu(long wscUserID, long goodsID);

}