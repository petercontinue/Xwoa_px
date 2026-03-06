package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.OA.OaLog;
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
 * @since 2021-07-03
 */
@Repository
public interface IOaLogDao extends BaseMapper<OaLog> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "systemContent", property = "systemContent"),
                @Result(column = "funcName", property = "funcName"),
                @Result(column = "staffID", property = "staffID"),
                @Result(column = "staffName", property = "staffName"),
                @Result(column = "logType", property = "logType"),
                @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  oa_log"
            + "</script>")
    List<OaLog> getAllList();
}