package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.WscTixian;
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
 * @since 2021-08-23
 */
@Repository
public interface IWscTixianDao extends BaseMapper<WscTixian> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "wsc_user_id", property = "wscUserId"),
            @Result(column = "realName", property = "realName"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "bankCard", property = "bankCard"),
            @Result(column = "bankName", property = "bankName"),
            @Result(column = "tixianMoney", property = "tixianMoney"),
            @Result(column = "shengqingTime", property = "shengqingTime"),
            @Result(column = "tixianShuoming", property = "tixianShuoming"),
            @Result(column = "tixianShengpiState", property = "tixianShengpiState"),
            @Result(column = "shengpiShuoming", property = "shengpiShuoming"),
            @Result(column = "tixianShengpiStaffID", property = "tixianShengpiStaffID"),
            @Result(column = "tixianShengpiTime", property = "tixianShengpiTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_tixian"
            + "</script>")
    List<WscTixian> getAllList();

    @Select("<script>" +
            "select a.*,b.nickName,c.staffName from wsc_tixian a\n" +
            "JOIN wsc_user b on a.wsc_user_id=b.id\n" +
            "left JOIN pxstafftable c on a.tixianShengpiStaffID=c.id " +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"
            + "</script>")
    Page<HashMap<String,Object>> gettixianPage(Page page, @Param("ew") QueryWrapper queryWrapper);
}