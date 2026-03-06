package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesorderstable;
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
public interface IPxteachingsuppliesorderstableDao extends BaseMapper<Pxteachingsuppliesorderstable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "OrderNo", property = "orderNo"),
                @Result(column = "CreatDatetime", property = "creatDatetime"),
                @Result(column = "CreatStaffID", property = "creatStaffID"),
                @Result(column = "OrderMoney", property = "orderMoney"),
                @Result(column = "YouHuiMoney", property = "youHuiMoney"),
                @Result(column = "ShijiPayMoney", property = "shijiPayMoney"),
                @Result(column = "PayMoneyStyle", property = "payMoneyStyle"),
                @Result(column = "OrderState", property = "orderState"),
                @Result(column = "PayDate", property = "payDate"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachingsuppliesorderstable"
            + "</script>")
    List<Pxteachingsuppliesorderstable> getAllList();
}