package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstuhuifangtable;
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
 * @since 2021-08-25
 */
@Repository
public interface IPxstuhuifangtableDao extends BaseMapper<Pxstuhuifangtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "hfType", property = "hfType"),
                @Result(column = "text", property = "text"),
                @Result(column = "hfstaffID", property = "hfstaffID"),
                @Result(column = "huifangTime", property = "huifangTime"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "addstaffID", property = "addstaffID"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstuhuifangtable"
            + "</script>")
    List<Pxstuhuifangtable> getAllList();
}