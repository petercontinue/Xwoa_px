package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.cxCampusVO;
import com.xwcloud.cloud.model.entity.Pxcampustable;
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
 * @since 2020-10-24
 */
@Repository
public interface IPxcampustableDao extends BaseMapper<Pxcampustable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "campusName", property = "campusName"),
            @Result(column = "campusAddress", property = "campusAddress"),
            @Result(column = "campusTel", property = "campusTel"),
            @Result(column = "QRcodePrint", property = "QRcodePrint"),
            @Result(column = "QRcodeWx", property = "QRcodeWx"),
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

    /**
     * 获取校区下拉查询内容
     *
     * @return
     */
    @Select("<script>" + "SELECT id,campusName AS name FROM pxcampustable WHERE isOpen!=2" + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<cxCampusVO> GetSearchCampusList(@Param("ew") Wrapper wrapper);


    @Select("<script>" +
            "select a.campusName,a.nextPayTime,b.khShowJigouName,b.kehucontractname,b.kehutelphone,c.workName,c.worktel\n" +
            "from pxcampustable  a \n" +
            "join oa_kehu b on a.qiyeID=b.id\n" +
            "left join oa_staff c on b.aftersalestaffID=c.id \n" +
            "where a.isOpen!=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, Object>> getjigou(@Param("ew") QueryWrapper queryWrapper);
}