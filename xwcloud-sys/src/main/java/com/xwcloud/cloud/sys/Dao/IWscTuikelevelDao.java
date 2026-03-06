package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.WscTuikelevel;
import org.apache.ibatis.annotations.Param;
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
 * @since 2021-08-19
 */
@Repository
public interface IWscTuikelevelDao extends BaseMapper<WscTuikelevel> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "tuikeLevelName", property = "tuikeLevelName"),
            @Result(column = "tiaojianMoney", property = "tiaojianMoney"),
            @Result(column = "fjFanyongbi1", property = "fjFanyongbi1"),
            @Result(column = "zjFanyongbi1", property = "zjFanyongbi1"),
            @Result(column = "fjFanyongbi2", property = "fjFanyongbi2"),
            @Result(column = "zjFanyongbi2", property = "zjFanyongbi2"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_tuikeLevel"
            + "</script>")
    List<WscTuikelevel> getAllList();

    @Select("<script>" +
            "SELECT * from wsc_tuikeLevel " +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<WscTuikelevel> getpage(Page page, @Param("ew") QueryWrapper queryWrapper);
}