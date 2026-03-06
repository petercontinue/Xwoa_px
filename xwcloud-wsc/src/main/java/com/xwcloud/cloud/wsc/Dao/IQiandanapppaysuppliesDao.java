package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Qiandanapppaysupplies;
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
 * @since 2021-05-28
 */
@Repository
public interface IQiandanapppaysuppliesDao extends BaseMapper<Qiandanapppaysupplies> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiandanAppayID", property = "qiandanAppayID"),
            @Result(column = "TeachingSuppliesID", property = "teachingSuppliesID"),
            @Result(column = "Name", property = "name"),
            @Result(column = "BuyPrice", property = "buyPrice"),
            @Result(column = "BuySum", property = "buySum"),
            @Result(column = "SumMoney", property = "sumMoney"),
            @Result(column = "fafangstate", property = "fafangstate"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  qiandanapppaysupplies"
            + "</script>")
    List<Qiandanapppaysupplies> getAllList();
}