package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.WscUserjiaoyi;

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
 * @since 2021-02-24
 */
@Repository
public interface IWscUserjiaoyiDao extends BaseMapper<WscUserjiaoyi> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "orderNumber", property = "orderNumber"),
            @Result(column = "wscUserID", property = "wscUserID"),
            @Result(column = "payMoney", property = "payMoney"),
            @Result(column = "giveMoney", property = "giveMoney"),
            @Result(column = "totalMoney", property = "totalMoney"),
            @Result(column = "style", property = "style"),
            @Result(column = "type", property = "type"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "okDate", property = "okDate"),
            @Result(column = "state", property = "state"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_userjiaoyi"
            + "</script>")
    List<WscUserjiaoyi> getAllList();

    /**
     * 分页查询商城充值信息
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.*,b.nickName as userName FROM wsc_userjiaoyi AS a LEFT JOIN wsc_user AS b ON a.wscUserID = b.id " +
            "WHERE a.type = 1 AND a.state = true" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<WscUserjiaoyi> GetwechatChongziPages(Page page, @Param("ew") QueryWrapper wrapper);


    @Select("<script>" + "SELECT a.*,b.nickName nickName " +
            "FROM wsc_userjiaoyi AS a " +
            "left join wsc_user b on a.wscUserID=b.id " + "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<HashMap<String, Object>> Getchongzhiliushui(@Param("ew") QueryWrapper wrapper);
}