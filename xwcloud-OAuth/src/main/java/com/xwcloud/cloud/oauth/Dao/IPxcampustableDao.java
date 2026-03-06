package com.xwcloud.cloud.oauth.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxcampustable;
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
 * @since 2021-08-16
 */
@Repository
public interface IPxcampustableDao extends BaseMapper<Pxcampustable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "campusName", property = "campusName"),
                @Result(column = "campusAddress", property = "campusAddress"),
                @Result(column = "campusTel", property = "campusTel"),
                @Result(column = "ishaveMall", property = "ishaveMall"),
                @Result(column = "ishaveZhibo", property = "ishaveZhibo"),
                @Result(column = "QRcodePrint", property = "qRcodePrint"),
                @Result(column = "QRcodeWx", property = "qRcodeWx"),
                @Result(column = "accessToken", property = "accessToken"),
                @Result(column = "wxjiazhangADimg", property = "wxjiazhangADimg"),
                @Result(column = "wxjiazhangIsShowShoplink", property = "wxjiazhangIsShowShoplink"),
                @Result(column = "wxjiazhangShoplinkImg", property = "wxjiazhangShoplinkImg"),
                @Result(column = "isOpen", property = "isOpen"),
                @Result(column = "buyDateTime", property = "buyDateTime"),
                @Result(column = "nextPayTime", property = "nextPayTime"),
                @Result(column = "appID", property = "appID"),
                @Result(column = "appSecret", property = "appSecret"),
                @Result(column = "wxShanghuID", property = "wxShanghuID"),
                @Result(column = "wxShanghuKey", property = "wxShanghuKey"),
                @Result(column = "wxShanghuZhengshuAddr", property = "wxShanghuZhengshuAddr"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxcampustable"
            + "</script>")
    List<Pxcampustable> getAllList();
}