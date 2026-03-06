package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Qiandanshenpisupplies;
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
public interface IQiandanshenpisuppliesDao extends BaseMapper<Qiandanshenpisupplies> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "qiandanshenpiID", property = "qiandanshenpiID"),
                @Result(column = "TeachingSuppliesID", property = "teachingSuppliesID"),
                @Result(column = "Name", property = "name"),
                @Result(column = "BuyPrice", property = "buyPrice"),
                @Result(column = "BuySum", property = "buySum"),
                @Result(column = "SumMoney", property = "sumMoney"),
                @Result(column = "fafangstate", property = "fafangstate"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  qiandanshenpisupplies"
            + "</script>")
    List<Qiandanshenpisupplies> getAllList();
}