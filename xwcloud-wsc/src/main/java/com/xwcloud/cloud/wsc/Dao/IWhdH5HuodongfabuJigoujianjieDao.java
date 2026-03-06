package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.WhdH5HuodongfabuJigoujianjie;
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
 * @since 2021-04-02
 */
@Repository
public interface IWhdH5HuodongfabuJigoujianjieDao extends BaseMapper<WhdH5HuodongfabuJigoujianjie> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "jianjieNeirong", property = "jianjieNeirong"),
            @Result(column = "jianjieType", property = "jianjieType"),
            @Result(column = "jiajiePaixu", property = "jiajiePaixu"),
            @Result(column = "whd_h5_huodongfabu_id", property = "whdH5HuodongfabuId"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_h5_huodongfabu_jigoujianjie"
            + "</script>")
    List<WhdH5HuodongfabuJigoujianjie> getAllList();
}