package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Qiandanapppayzafei;
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
public interface IQiandanapppayzafeiDao extends BaseMapper<Qiandanapppayzafei> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiandanAppayID", property = "qiandanAppayID"),
            @Result(column = "qianDanOtherMoneyID", property = "qianDanOtherMoneyID"),
            @Result(column = "onePrice", property = "onePrice"),
            @Result(column = "nums", property = "nums"),
            @Result(column = "zongMoney", property = "zongMoney"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  qiandanapppayzafei"
            + "</script>")
    List<Qiandanapppayzafei> getAllList();
}