package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxfazhengtable;
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
public interface IPxfazhengtableDao extends BaseMapper<Pxfazhengtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "Stuid", property = "stuid"),
                @Result(column = "ZSid", property = "zSid"),
                @Result(column = "FZstaff", property = "fZstaff"),
                @Result(column = "FZdate", property = "fZdate"),
                @Result(column = "FZImage", property = "fZImage"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxfazhengtable"
            + "</script>")
    List<Pxfazhengtable> getAllList();
}