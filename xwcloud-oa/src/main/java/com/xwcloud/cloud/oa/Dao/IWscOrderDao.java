package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscOrder;
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
 * @since 2021-08-25
 */
@Repository
public interface IWscOrderDao extends BaseMapper<WscOrder> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "orderNumber", property = "orderNumber"),
            @Result(column = "payMoney", property = "payMoney"),
            @Result(column = "payJifen", property = "payJifen"),
            @Result(column = "couponsID", property = "couponsID"),
            @Result(column = "type", property = "type"),
            @Result(column = "receiveName", property = "receiveName"),
            @Result(column = "receiveDizhi", property = "receiveDizhi"),
            @Result(column = "lianxiTel", property = "lianxiTel"),
            @Result(column = "yxCampusIDs", property = "yxCampusIDs"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "orderDateTime", property = "orderDateTime"),
            @Result(column = "payStyle", property = "payStyle"),
            @Result(column = "payDateTime", property = "payDateTime"),
            @Result(column = "huodongID", property = "huodongID"),
            @Result(column = "orderFrom", property = "orderFrom"),
            @Result(column = "orderUserID", property = "orderUserID"),
            @Result(column = "orderState", property = "orderState"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_order"
            + "</script>")
    List<WscOrder> getAllList();

    @Select("<script>" +
            "UPdate wsc_order Set yxCampusIDs=REPLACE(yxCampusIDs,#{cxStr},'') where id = #{id} "
            + "</script>"
    )
    List<WscOrder> updateOrdercampus(String cxStr, Long id);


}