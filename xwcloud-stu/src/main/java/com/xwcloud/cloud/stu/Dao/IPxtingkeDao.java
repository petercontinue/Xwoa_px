package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxtingke;

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
 * @since 2021-01-27
 */
@Repository
public interface IPxtingkeDao extends BaseMapper<Pxtingke> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "type", property = "type"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "tingkeUser", property = "tingkeUser"),
            @Result(column = "tingkeTime", property = "tingkeTime"),
            @Result(column = "tingkeshuoming", property = "tingkeshuoming"),
            @Result(column = "fukeTime", property = "fukeTime"),
            @Result(column = "fukeshuoming", property = "fukeshuoming"),
            @Result(column = "fukeUser", property = "fukeUser"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtingke"
            + "</script>")
    List<Pxtingke> getAllList();


    @Select("<script>" +
            "SELECT * from  pxtingke where stuID=#{stuID} and qiyeID=#{qiyeID} ORDER BY tingkeTime DESC "
            + "</script>")
    List<Pxtingke> getstuTk(Long stuID, Long qiyeID);
}