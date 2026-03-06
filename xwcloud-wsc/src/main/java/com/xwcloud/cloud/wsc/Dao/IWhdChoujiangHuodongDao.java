package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.WhdChoujiangHuodong;
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
 * @since 2021-01-17
 */
@Repository
public interface IWhdChoujiangHuodongDao extends BaseMapper<WhdChoujiangHuodong> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "choujiangHuodongName", property = "choujianghuodongname"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "jigouJianjie", property = "jigoujianjie"),
            @Result(column = "startTime", property = "starttime"),
            @Result(column = "endTime", property = "endtime"),
            @Result(column = "addTime", property = "addtime"),
            @Result(column = "addUser", property = "adduser"),
            @Result(column = "choujiangStyle", property = "choujiangstyle"),
            @Result(column = "cishu", property = "cishu"),
            @Result(column = "isUp", property = "isup"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  whd_choujiang_huodong"
            + "</script>")
    List<WhdChoujiangHuodong> getAllList();

    @Select("<script>" +
            "select a.*,b.staffName from whd_choujiang_huodong a \n" +
            "LEFT JOIN pxstafftable b on a.addUser=b.id " +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<HashMap<String, Object>> getcjhuodongPages(Page page, @Param("ew") QueryWrapper queryWrapper);
}