package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.OA.OaParameter;
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
 * @since 2021-07-19
 */
@Repository
public interface IOaParameterDao extends BaseMapper<OaParameter> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "bianLiangName", property = "bianLiangName"),
                @Result(column = "modifyValue", property = "modifyValue"),
                @Result(column = "shuoming", property = "shuoming"),
                @Result(column = "type", property = "type"),
                @Result(column = "paixu", property = "paixu"),
    })
    @Select("<script>" +
            "SELECT * from  oa_parameter"
            + "</script>")
    List<OaParameter> getAllList();
}