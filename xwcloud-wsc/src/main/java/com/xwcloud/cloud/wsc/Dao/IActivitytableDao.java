package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Activitytable;
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
 * @since 2021-07-09
 */
@Repository
public interface IActivitytableDao extends BaseMapper<Activitytable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "activityName", property = "activityName"),
            @Result(column = "activityDiscription", property = "activityDiscription"),
            @Result(column = "logo", property = "logo"),
            @Result(column = "Images", property = "images"),
            @Result(column = "type", property = "type"),
            @Result(column = "sponsor", property = "sponsor"),
            @Result(column = "isUser", property = "isUser"),
            @Result(column = "schoolName", property = "schoolName"),
            @Result(column = "campusAdress", property = "campusAdress"),
            @Result(column = "lianxiTel", property = "lianxiTel"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  activitytable"
            + "</script>")
    List<Activitytable> getAllList();
}