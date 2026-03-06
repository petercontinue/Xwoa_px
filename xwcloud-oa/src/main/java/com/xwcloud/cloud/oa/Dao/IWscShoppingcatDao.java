package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscShoppingcat;
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
public interface IWscShoppingcatDao extends BaseMapper<WscShoppingcat> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "goodsID", property = "goodsID"),
                @Result(column = "goodsShuxingPriceID", property = "goodsShuxingPriceID"),
                @Result(column = "huodongID", property = "huodongID"),
                @Result(column = "num", property = "num"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "addDateTime", property = "addDateTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_shoppingcat"
            + "</script>")
    List<WscShoppingcat> getAllList();
}