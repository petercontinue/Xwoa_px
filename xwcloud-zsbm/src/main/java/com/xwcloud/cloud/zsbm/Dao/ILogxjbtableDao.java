package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.log.Logxjbtable;
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
public interface ILogxjbtableDao extends BaseMapper<Logxjbtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "systemContent", property = "systemContent"),
                @Result(column = "funcName", property = "funcName"),
                @Result(column = "staffID", property = "staffID"),
                @Result(column = "staffName", property = "staffName"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "stuName", property = "stuName"),
                @Result(column = "logType", property = "logType"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  logxjbtable"
            + "</script>")
    List<Logxjbtable> getAllList();
}