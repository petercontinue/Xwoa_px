package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxwxusertable;
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
 * @since 2020-12-05
 */
@Repository
public interface IPxwxusertableDao extends BaseMapper<Pxwxusertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "unidid", property = "unidid"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "nickName", property = "nickName"),
            @Result(column = "headImage", property = "headImage"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "diqu", property = "diqu"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxwxusertable"
            + "</script>")
    List<Pxwxusertable> getAllList();

    @Select("<script>"+"SELECT * FROM pxwxusertable WHERE staffID =#{staffID}"+"</script>")
    Pxwxusertable GetWxuserByStaffID(String staffID);
}