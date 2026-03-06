package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscUserAddress;
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
public interface IWscUserAddressDao extends BaseMapper<WscUserAddress> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "addUserName", property = "addUserName"),
                @Result(column = "address", property = "address"),
                @Result(column = "addresstype", property = "addresstype"),
                @Result(column = "tel", property = "tel"),
                @Result(column = "isMoren", property = "isMoren"),
                @Result(column = "UserID", property = "userID"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_user_address"
            + "</script>")
    List<WscUserAddress> getAllList();
}