package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxpowerbuttontable;
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
 * @since 2020-12-07
 */
@Repository
public interface IPxpowerbuttontableDao extends BaseMapper<Pxpowerbuttontable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buttonName", property = "buttonName"),
            @Result(column = "onclicks", property = "onclicks"),
            @Result(column = "icons", property = "icons"),
            @Result(column = "btnClass", property = "btnClass"),
    })
    @Select("<script>" +
            "SELECT * from  pxpowerbuttontable"
            + "</script>")
    List<Pxpowerbuttontable> getAllList();
}