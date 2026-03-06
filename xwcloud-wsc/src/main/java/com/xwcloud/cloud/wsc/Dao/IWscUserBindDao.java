package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscUserBind;
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
 * @since 2021-08-10
 */
@Repository
public interface IWscUserBindDao extends BaseMapper<WscUserBind> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "wscuserid", property = "wscuserid"),
                @Result(column = "stuId", property = "stuId"),
                @Result(column = "phoneNumber", property = "phoneNumber"),
                @Result(column = "parentTelRelation", property = "parentTelRelation"),
                @Result(column = "role", property = "role"),
                @Result(column = "isCanLoginPxsys", property = "isCanLoginPxsys"),
                @Result(column = "isReceivePxmsg", property = "isReceivePxmsg"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_user_bind"
            + "</script>")
    List<WscUserBind> getAllList();
}