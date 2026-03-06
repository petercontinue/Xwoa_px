package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.WscGoodstype;
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
public interface IWscGoodstypeDao extends BaseMapper<WscGoodstype> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "goodsType", property = "goodstype"),
            @Result(column = "typeLevel", property = "typelevel"),
            @Result(column = "fid", property = "fid"),
            @Result(column = "isShow", property = "isshow"),
            @Result(column = "qiyeID", property = "qiyeid"),
})
@Select("<script>" +
        "SELECT * from  wsc_goodstype"
        + "</script>")
List<WscGoodstype> getAllList();
}