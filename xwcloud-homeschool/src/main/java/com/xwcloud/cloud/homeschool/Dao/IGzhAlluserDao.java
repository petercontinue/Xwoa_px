package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.GzhAlluser;
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
 * @since 2021-08-11
 */
@Repository
public interface IGzhAlluserDao extends BaseMapper<GzhAlluser> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "openid", property = "openid"),
                @Result(column = "nickname", property = "nickname"),
                @Result(column = "subscribe_time", property = "subscribeTime"),
                @Result(column = "unionid", property = "unionid"),
                @Result(column = "subscribe", property = "subscribe"),
    })
    @Select("<script>" +
            "SELECT * from  gzh_AllUser"
            + "</script>")
    List<GzhAlluser> getAllList();
}