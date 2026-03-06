package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxtskaiguandefaulttable;
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
 * @since 2020-12-04
 */
@Repository
public interface IPxtskaiguandefaulttableDao extends BaseMapper<Pxtskaiguandefaulttable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "TSTypeName", property = "TSTypeName"),
            @Result(column = "value", property = "value"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "type", property = "type"),
})
@Select("<script>" +
        "SELECT * from  pxtskaiguandefaulttable"
        + "</script>")
List<Pxtskaiguandefaulttable> getAllList();
}