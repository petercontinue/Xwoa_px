package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.OA.Vo.PxcampusVo;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-05
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
            @Result(column = "QRcodePrint", property = "QRcodePrint"),
            @Result(column = "QRcodeWx", property = "QRcodeWx"),
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

    @Results(id = "PxcampusInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "campusName", column = "campusName"),
            @Result(property = "campusAddress", column = "campusAddress"),
            @Result(property = "campusTel", column = "campusTel"),
            @Result(property = "ishaveMall", column = "ishaveMall"),
            @Result(property = "ishaveZhibo", column = "ishaveZhibo"),
            @Result(property = "QRcodePrint", column = "QRcodePrint"),
            @Result(property = "QRcodeWx", column = "QRcodeWx"),
            @Result(property = "accessToken", column = "accessToken"),
            @Result(property = "wxjiazhangADimg", column = "wxjiazhangADimg"),
            @Result(property = "wxjiazhangIsShowShoplink", column = "wxjiazhangIsShowShoplink"),
            @Result(property = "wxjiazhangShoplinkImg", column = "wxjiazhangShoplinkImg"),
            @Result(property = "isOpen", column = "isOpen"),
            @Result(property = "buyDateTime", column = "buyDateTime"),
            @Result(property = "nextPayTime", column = "nextPayTime"),
            @Result(property = "appID", column = "appID"),
            @Result(property = "appSecret", column = "appSecret"),
            @Result(property = "wxShanghuID", column = "wxShanghuID"),
            @Result(property = "wxShanghuKey", column = "wxShanghuKey"),
            @Result(property = "wxShanghuZhengshuAddr", column = "wxShanghuZhengshuAddr"),
            @Result(property = "qiyeID", column = "qiyeID"),
            @Result(property = "kehucompanyname", column = "kehucompanyname")
    })
    @Select("<script>" +
            "SELECT pxcampustable.*,\n" +
            "kehu.kehucompanyname \n" +
            "from pxcampustable\n" +
            "LEFT JOIN oa_kehu kehu on pxcampustable.qiyeID=kehu.id" + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<PxcampusVo> getAllPxcampusInfo(Page<PxcampusVo> page, @Param("ew") Wrapper wrapper);


    @ResultMap(value = "PxcampusInfo")
    @Select("SELECT pxcampustable.*, \n" +
            "kehu.kehucompanyname \n" +
            "from pxcampustable \n" +
            "LEFT JOIN oa_kehu kehu on pxcampustable.qiyeID=kehu.id \n" +
            "where pxcampustable.id=#{id}")
    PxcampusVo getOnePxcampusById(Long id);

}