package com.xwcloud.cloud.overall.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.log.Logxjbtable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import com.xwcloud.cloud.model.log.*;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
@Repository
public interface ILogxttableDao extends BaseMapper<Logxjbtable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "systemContent", property = "systemContent"),
            @Result(column = "funcName", property = "funcName"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "logType", property = "logType"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  logxjbtable"
        + "</script>")
List<Logxjbtable> getAllList();
}