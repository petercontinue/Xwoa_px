package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.OA.OaManyidu;
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
 * @since 2021-07-08
 */
@Repository
public interface IOaManyiduDao extends BaseMapper<OaManyidu> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "manyiduName", property = "manyiduName"),
    })
    @Select("<script>" +
            "SELECT * from  oa_manyidu"
            + "</script>")
    List<OaManyidu> getAllList();
}