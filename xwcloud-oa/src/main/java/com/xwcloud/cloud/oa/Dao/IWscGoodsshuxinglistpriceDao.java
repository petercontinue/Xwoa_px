package com.xwcloud.cloud.oa.Dao;

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
 * @since 2021-08-25
 */
@Repository
public interface IWscGoodsshuxinglistpriceDao extends BaseMapper<WscGoodsshuxinglistprice> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "goodsID", property = "goodsID"),
                @Result(column = "goodsShuxingListAll", property = "goodsShuxingListAll"),
                @Result(column = "originalPrice", property = "originalPrice"),
                @Result(column = "price", property = "price"),
                @Result(column = "jifenPrice", property = "jifenPrice"),
                @Result(column = "onlyTimeBuyPrice", property = "onlyTimeBuyPrice"),
                @Result(column = "kanjiaSuccessPrice", property = "kanjiaSuccessPrice"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "kcnum", property = "kcnum"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_goodsshuxinglistprice"
            + "</script>")
    List<WscGoodsshuxinglistprice> getAllList();
}