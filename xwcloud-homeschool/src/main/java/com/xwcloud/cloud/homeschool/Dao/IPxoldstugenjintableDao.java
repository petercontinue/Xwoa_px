package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxoldstugenjintable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxoldstugenjintableDao extends BaseMapper<Pxoldstugenjintable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "genjinContent", property = "genjinContent"),
            @Result(column = "genjintime", property = "genjintime"),
            @Result(column = "adduser", property = "adduser"),
            @Result(column = "addtime", property = "addtime"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  pxoldstugenjintable"
        + "</script>")
List<Pxoldstugenjintable> getAllList();
}