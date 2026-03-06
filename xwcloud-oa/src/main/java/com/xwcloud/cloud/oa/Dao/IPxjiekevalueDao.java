package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxjiekevalue;
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
public interface IPxjiekevalueDao extends BaseMapper<Pxjiekevalue> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "jiekeID", property = "jiekeID"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "buxiID", property = "buxiID"),
                @Result(column = "remainkeshi", property = "remainkeshi"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "kechengPrice", property = "kechengPrice"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxjiekevalue"
            + "</script>")
    List<Pxjiekevalue> getAllList();
}