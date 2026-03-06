package com.xwcloud.cloud.overall.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.log.Logxiugaijilutable;
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
 * @since 2020-11-10
 */
@Repository
public interface ILogxiugaitableDao extends BaseMapper<Logxiugaijilutable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "stuName", property = "stuName"),
            @Result(column = "xiugaiqianxinxi", property = "xiugaiqianxinxi"),
            @Result(column = "xiugaihouxinxi", property = "xiugaihouxinxi"),
            @Result(column = "xiugaistaffID", property = "xiugaistaffID"),
            @Result(column = "xiugaidateTime", property = "xiugaidateTime"),
            @Result(column = "type", property = "type"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  logxiugaijilutable"
        + "</script>")
List<Logxiugaijilutable> getAllList();
}