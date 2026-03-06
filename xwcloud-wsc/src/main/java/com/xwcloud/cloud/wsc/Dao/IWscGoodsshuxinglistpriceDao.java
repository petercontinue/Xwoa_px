package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistprice;
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
 * @since 2021-01-17
 */
@Repository
public interface IWscGoodsshuxinglistpriceDao extends BaseMapper<WscGoodsshuxinglistprice> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "goodsID", property = "goodsid"),
            @Result(column = "goodsShuxingListAll", property = "goodsshuxinglistall"),
            @Result(column = "originalPrice", property = "originalprice"),
            @Result(column = "price", property = "price"),
            @Result(column = "jifenPrice", property = "jifenprice"),
            @Result(column = "onlyTimeBuyPrice", property = "onlytimebuyprice"),
            @Result(column = "kanjiaSuccessPrice", property = "kanjiasuccessprice"),
            @Result(column = "qiyeID", property = "qiyeid"),
})
@Select("<script>" +
        "SELECT * from  wsc_goodsshuxinglistprice"
        + "</script>")
List<WscGoodsshuxinglistprice> getAllList();
}