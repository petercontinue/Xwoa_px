package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglist;
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
public interface IWscGoodsshuxinglistDao extends BaseMapper<WscGoodsshuxinglist> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "goodsID", property = "goodsID"),
                @Result(column = "goodsGuigeTypeID", property = "goodsGuigeTypeID"),
                @Result(column = "shuxingMing", property = "shuxingMing"),
                @Result(column = "shuxingPaixu", property = "shuxingPaixu"),
                @Result(column = "isNeedChangImg", property = "isNeedChangImg"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_goodsshuxinglist"
            + "</script>")
    List<WscGoodsshuxinglist> getAllList();
}