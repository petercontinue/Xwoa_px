package com.xwcloud.cloud.wsc.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.WscGoodsAttributeVo;
import com.xwcloud.cloud.model.Vo.goodshuxingVO;
import com.xwcloud.cloud.model.Vo.guigeshuxingVo;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglist;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistprice;

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
public interface IWscGoodsshuxinglistDao extends BaseMapper<WscGoodsshuxinglist> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "goodsID", property = "goodsid"),
            @Result(column = "goodsGuigeTypeID", property = "goodsguigetypeid"),
            @Result(column = "shuxingMing", property = "shuxingming"),
            @Result(column = "shuxingPaixu", property = "shuxingpaixu"),
            @Result(column = "isNeedChangImg", property = "isneedchangimg"),
            @Result(column = "addStaffID", property = "addstaffid"),
            @Result(column = "addTime", property = "addtime"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_goodsshuxinglist"
            + "</script>")
    List<WscGoodsshuxinglist> getAllList();

    @Select("<script>" +
            "SELECT a.*,b.guigeTypeName goodsGuigeTypeName,c.staffName addStaffName\n" +
            "FROM wsc_goodsshuxinglist a\n" +
            "JOIN wsc_goodsguige b ON\ta.goodsGuigeTypeID=b.id\n" +
            "JOIN pxstafftable c ON\ta.addStaffID=c.id\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<WscGoodsAttributeVo> getGoodsAttributePage(Page page, @Param("ew") QueryWrapper wrapper);

    @Select("<script>" + "SELECT id,shuxingMing AS guigeTypeName FROM wsc_goodsshuxinglist " +
            "WHERE goodsID =#{goodsID} AND goodsGuigeTypeID = #{goodsGuigeTypeID} and qiyeID=#{qiyeID}" + "</script>")
    List<goodshuxingVO> GetGuigeNameList(long goodsID, long goodsGuigeTypeID, long qiyeID);


    @Select("<script>" +
            "SELECT * FROM wsc_goodsshuxinglist " +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"
            + "</script>")
    List<guigeshuxingVo> GetGuigeList(@Param("ew") QueryWrapper wrapper);

    @Select("<script>" +
            " select a.* from wsc_goodsshuxinglistprice as a" +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    WscGoodsshuxinglistprice getkucun(@Param("ew") Wrapper wrapper);

    @Select("<script>" + "select SUM(a.kcnum) AS kcnum from wsc_goodsshuxinglistprice as a" + "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, Object>> GetAllKucun(@Param("ew") Wrapper wrapper);

}