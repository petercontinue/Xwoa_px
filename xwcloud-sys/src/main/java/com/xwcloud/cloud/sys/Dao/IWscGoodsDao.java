package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscGoods;
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
 * @since 2021-07-29
 */
@Repository
public interface IWscGoodsDao extends BaseMapper<WscGoods> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "goodsName", property = "goodsName"),
            @Result(column = "goodsTypeID", property = "goodsTypeID"),
            @Result(column = "basicPrice", property = "basicPrice"),
            @Result(column = "jifenPrice", property = "jifenPrice"),
            @Result(column = "huodongID", property = "huodongID"),
            @Result(column = "huodongStartTime", property = "huodongStartTime"),
            @Result(column = "huodongEndTime", property = "huodongEndTime"),
            @Result(column = "onlyTimeBuyPrice", property = "onlyTimeBuyPrice"),
            @Result(column = "kanjiaOniceMinNum", property = "kanjiaOniceMinNum"),
            @Result(column = "kanjiaOniceMaxNum", property = "kanjiaOniceMaxNum"),
            @Result(column = "kanjiaSuccessPrice", property = "kanjiaSuccessPrice"),
            @Result(column = "goodInfo", property = "goodInfo"),
            @Result(column = "img1", property = "img1"),
            @Result(column = "img2", property = "img2"),
            @Result(column = "img3", property = "img3"),
            @Result(column = "img4", property = "img4"),
            @Result(column = "img5", property = "img5"),
            @Result(column = "addUser", property = "addUser"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "shangjiaState", property = "shangjiaState"),
            @Result(column = "text", property = "text"),
            @Result(column = "fxNum", property = "fxNum"),
            @Result(column = "maxNum", property = "maxNum"),
            @Result(column = "goodsShuomingOne", property = "goodsShuomingOne"),
            @Result(column = "goodsShuomingOneIsBold", property = "goodsShuomingOneIsBold"),
            @Result(column = "goodsShuomingOneFontColor", property = "goodsShuomingOneFontColor"),
            @Result(column = "goodsShuomingTwo", property = "goodsShuomingTwo"),
            @Result(column = "goodsShuomingTwoIsBold", property = "goodsShuomingTwoIsBold"),
            @Result(column = "goodsShuomingTwoFontColor", property = "goodsShuomingTwoFontColor"),
            @Result(column = "cartNum", property = "cartNum"),
            @Result(column = "campusIDs", property = "campusIDs"),
            @Result(column = "style", property = "style"),
            @Result(column = "paixu", property = "paixu"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "isAutoQiandan", property = "isAutoQiandan"),
            @Result(column = "fidFanyongBili", property = "fidFanyongBili"),
            @Result(column = "gfidFanyongBili", property = "gfidFanyongBili"),
            @Result(column = "fabuTo", property = "fabuTo"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_goods"
            + "</script>")
    List<WscGoods> getAllList();

    @Select("<script>" +
            "SELECT * from (SELECT\n" +
            "\ta.id,(\n" +
            "\tSELECT\n" +
            "\t\t( CASE WHEN sum( b.kcnum ) IS NOT NULL THEN sum( b.kcnum ) ELSE 0 END ) \n" +
            "\tFROM\n" +
            "\t\twsc_goodsshuxinglistprice b \n" +
            "\tWHERE\n" +
            "\t\tb.goodsID = a.id \n" +
            "\t) kcnum\n" +
            "FROM\n" +
            "\twsc_goods a \n" +
            "WHERE\n" +
            "\t1 = 1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            ") te where te.kcnum &lt; 5 " +
            " GROUP BY  te.id "
            + "</script>")
    List<HashMap<String, Object>> getnoKCGoods(@Param("ew") QueryWrapper queryWrapper);

}