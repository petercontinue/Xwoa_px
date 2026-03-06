package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.jinxiaocunXSliushuiVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesorderstable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-21
 */
@Repository
public interface IPxteachingsuppliesorderstableDao extends BaseMapper<Pxteachingsuppliesorderstable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "OrderNo", property = "OrderNo"),
            @Result(column = "CreatDatetime", property = "CreatDatetime"),
            @Result(column = "CreatStaffID", property = "CreatStaffID"),
            @Result(column = "OrderMoney", property = "OrderMoney"),
            @Result(column = "YouHuiMoney", property = "YouHuiMoney"),
            @Result(column = "ShijiPayMoney", property = "ShijiPayMoney"),
            @Result(column = "PayMoneyStyle", property = "PayMoneyStyle"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachingsuppliesorderstable"
            + "</script>")
    List<Pxteachingsuppliesorderstable> getAllList();

    /**
     * 分页查询进销存销售流水
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxteachingsuppliesorderdetailtable AS a\n" +
            "LEFT JOIN pxteachingsuppliesorderstable AS b ON a.OrderID = b.id\n" +
            "LEFT JOIN pxteachingsuppliestable AS c ON a.SuppliesID = c.id\n" +
            "LEFT JOIN pxcampustable AS d ON c.campusID = d.id\n" +
            "LEFT JOIN pxstafftable AS e ON b.CreatStaffID = e.id "+
            "WHERE 1 = 1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<jinxiaocunXSliushuiVo> getXiaoshouLiushuiDays(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 查询所有进销存销售流水（不分页）
     * @param wrapper
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxteachingsuppliesorderdetailtable AS a\n" +
            "LEFT JOIN pxteachingsuppliesorderstable AS b ON a.OrderID = b.id\n" +
            "LEFT JOIN pxteachingsuppliestable AS c ON a.SuppliesID = c.id\n" +
            "LEFT JOIN pxcampustable AS d ON c.campusID = d.id\n" +
            "LEFT JOIN pxstafftable AS e ON b.CreatStaffID = e.id "+
            "WHERE 1 = 1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<jinxiaocunXSliushuiVo> GetAllXiaoshouliushuiList(@Param("ew") Wrapper wrapper);

    /**
     * 查询当天的进销存销售流水
     * @param page
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxteachingsuppliesorderdetailtable AS a\n" +
            "            LEFT JOIN pxteachingsuppliesorderstable AS b ON a.OrderID = b.id\n" +
            "            LEFT JOIN pxteachingsuppliestable AS c ON a.SuppliesID = c.id\n" +
            "            LEFT JOIN pxcampustable AS d ON c.campusID = d.id\n" +
            "            LEFT JOIN pxstafftable AS e ON b.CreatStaffID = e.id \n" +
            "\t\t\t\t\t\twhere to_days(CreatDatetime) = to_days(now()) AND a.qiyeID=#{qiyeID}"+"</script>")
    Page<jinxiaocunXSliushuiVo> GetTodayXiaoshouliushui(Page page,long qiyeID);
}