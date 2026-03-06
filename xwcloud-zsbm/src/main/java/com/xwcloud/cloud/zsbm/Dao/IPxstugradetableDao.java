package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstugradetable;
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
 * @since 2021-06-08
 */
@Repository
public interface IPxstugradetableDao extends BaseMapper<Pxstugradetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuGradeName", property = "stuGradeName"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstugradetable "
            + "</script>")
    List<Pxstugradetable> getAllList();

    @Select("<script>" +
            "SELECT * from  pxstugradetable where qiyeID=#{qiyeID} limit 1"
            + "</script>")
    Pxstugradetable getOne(Long qiyeID);
}