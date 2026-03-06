package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdCoupons;
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
public interface IWhdCouponsDao extends BaseMapper<WhdCoupons> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "couponsName", property = "couponsName"),
                @Result(column = "Money", property = "money"),
                @Result(column = "manMoney", property = "manMoney"),
                @Result(column = "type", property = "type"),
                @Result(column = "GoodTypeID", property = "goodTypeID"),
                @Result(column = "stratDate", property = "stratDate"),
                @Result(column = "endDate", property = "endDate"),
                @Result(column = "giveType", property = "giveType"),
                @Result(column = "minGiveMoney", property = "minGiveMoney"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addDate", property = "addDate"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_coupons"
            + "</script>")
    List<WhdCoupons> getAllList();
}