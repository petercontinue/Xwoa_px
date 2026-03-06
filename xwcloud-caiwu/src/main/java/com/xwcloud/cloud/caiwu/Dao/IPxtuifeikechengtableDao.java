package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxtuifeikechengtable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxtuifeikechengtableDao extends BaseMapper<Pxtuifeikechengtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "tfBuxiID", property = "tfBuxiID"),
            @Result(column = "tfKechengID", property = "tfKechengID"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "tfqianRemainkeshi", property = "tfqianRemainkeshi"),
            @Result(column = "tfkeshi", property = "tfkeshi"),
            @Result(column = "tfhouRemainkeshi", property = "tfhouRemainkeshi"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "tuifeiID", property = "tuifeiID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtuifeikechengtable "
            + "</script>")
    List<Pxtuifeikechengtable> getAllList();
}