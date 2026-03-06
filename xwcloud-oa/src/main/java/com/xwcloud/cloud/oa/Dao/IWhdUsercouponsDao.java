package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdUsercoupons;
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
public interface IWhdUsercouponsDao extends BaseMapper<WhdUsercoupons> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "couponsID", property = "couponsID"),
                @Result(column = "type", property = "type"),
                @Result(column = "userID", property = "userID"),
                @Result(column = "givashouming", property = "givashouming"),
                @Result(column = "isUse", property = "isUse"),
                @Result(column = "useDate", property = "useDate"),
                @Result(column = "addDate", property = "addDate"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_usercoupons"
            + "</script>")
    List<WhdUsercoupons> getAllList();
}