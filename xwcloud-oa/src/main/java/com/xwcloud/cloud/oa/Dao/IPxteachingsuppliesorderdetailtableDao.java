package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesorderdetailtable;
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
 * @since 2021-08-25
 */
@Repository
public interface IPxteachingsuppliesorderdetailtableDao extends BaseMapper<Pxteachingsuppliesorderdetailtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "OrderID", property = "orderID"),
                @Result(column = "SuppliesID", property = "suppliesID"),
                @Result(column = "SuppliesName", property = "suppliesName"),
                @Result(column = "BuySum", property = "buySum"),
                @Result(column = "BuyPrice", property = "buyPrice"),
                @Result(column = "SuppliesTiaoma", property = "suppliesTiaoma"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachingsuppliesorderdetailtable"
            + "</script>")
    List<Pxteachingsuppliesorderdetailtable> getAllList();
}