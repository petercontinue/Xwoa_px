package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxqiandansupplies;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxqiandansuppliesDao extends BaseMapper<Pxqiandansupplies> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "TeachingSuppliesID", property = "TeachingSuppliesID"),
            @Result(column = "Name", property = "Name"),
            @Result(column = "BuyPrice", property = "BuyPrice"),
            @Result(column = "BuySum", property = "BuySum"),
            @Result(column = "QiandaninfoID", property = "QiandaninfoID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "SumMoney", property = "SumMoney"),
            @Result(column = "IsTuiFei", property = "IsTuiFei"),
            @Result(column = "TuiMoney", property = "TuiMoney"),
            @Result(column = "fafangstate", property = "fafangstate"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandansupplies"
            + "</script>")
    List<Pxqiandansupplies> getAllList();


    /***
     * 检测可退商品
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "SELECT  a.* from pxqiandansupplies a \n" +
            "LEFT JOIN pxteachingsuppliestable b on a.TeachingSuppliesID=b.id\n" +
            "LEFT JOIN pxqiandaninfotable c on a.QiandaninfoID =c.id " +
            "where 1=1 and ((a.TuiMoney is not NULL and a.TuiMoney &lt; a.SumMoney) or a.TuiMoney is null) " +
            "<if test='ew != null'>" +
            " AND  ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxqiandansupplies> tuisplist(@Param("ew") Wrapper wrapper);


    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(qdsp.BuySum) is not null THEN SUM(qdsp.BuySum) ELSE 0 END )  FROM pxqiandansupplies qdsp " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    String getBuysupplies(@Param("ew") Wrapper wrapper);
}