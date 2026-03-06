package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.log.Operationlog;
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
 * @since 2021-03-11
 */
@Repository
public interface IOperationlogDao extends BaseMapper<Operationlog> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "oper_id", property = "operId"),
            @Result(column = "oper_modul", property = "operModul"),
            @Result(column = "oper_type", property = "operType"),
            @Result(column = "oper_desc", property = "operDesc"),
            @Result(column = "oper_requ_param", property = "operRequParam"),
            @Result(column = "oper_resp_param", property = "operRespParam"),
            @Result(column = "oper_user_id", property = "operUserId"),
            @Result(column = "oper_user_name", property = "operUserName"),
            @Result(column = "oper_method", property = "operMethod"),
            @Result(column = "oper_url", property = "operUrl"),
            @Result(column = "oper_ip", property = "operIp"),
            @Result(column = "oper_create_time", property = "operCreateTime"),
            @Result(column = "oper_ver", property = "operVer"),
    })
    @Select("<script>" +
            "SELECT * from  operationlog"
            + "</script>")
    List<Operationlog> getAllList();
}