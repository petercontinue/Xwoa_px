package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxteachingsuppliesorderdetailtable;
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
 * @since 2020-11-21
 */
public interface IPxteachingsuppliesorderdetailtableDao extends BaseMapper<Pxteachingsuppliesorderdetailtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "OrderID", property = "OrderID"),
            @Result(column = "SuppliesID", property = "SuppliesID"),
            @Result(column = "SuppliesName", property = "SuppliesName"),
            @Result(column = "BuySum", property = "BuySum"),
            @Result(column = "BuyPrice", property = "BuyPrice"),
            @Result(column = "SuppliesTiaoma", property = "SuppliesTiaoma"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachingsuppliesorderdetailtable"
            + "</script>")
    List<Pxteachingsuppliesorderdetailtable> getAllList();
}