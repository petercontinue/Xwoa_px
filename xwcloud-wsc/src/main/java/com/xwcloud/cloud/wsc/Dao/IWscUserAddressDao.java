package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.WscUserAddress;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-19
 */
@Repository
public interface IWscUserAddressDao extends BaseMapper<WscUserAddress> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "UserID", property = "userID"),
            @Result(column = "address", property = "address"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "isMoren", property = "isMoren"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "addresstype", property = "addresstype"),
            @Result(column = "addUserName", property = "addUserName"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_user_address"
            + "</script>")
    List<WscUserAddress> getAllList();

    @Select("<script>" +
            "SELECT a.*,a.addUserName," +
            "(case WHEN a.addresstype is not null THEN c.addtype ELSE '' END) addType " +
            "from  wsc_user_address a \n" +
            "left join wsc_user b on a.userID=b.id " +
            "left join wsc_addresstype c on a.addresstype=c.id " +
            " WHERE 1=1  " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String, Object>> getpage(Page page, @Param("ew") QueryWrapper queryWrapper);
}