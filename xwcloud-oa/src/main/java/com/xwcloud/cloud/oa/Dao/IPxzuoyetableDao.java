package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxzuoyetable;
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
public interface IPxzuoyetableDao extends BaseMapper<Pxzuoyetable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "neirong", property = "neirong"),
                @Result(column = "classID", property = "classID"),
                @Result(column = "endDate", property = "endDate"),
                @Result(column = "zuoyeImg", property = "zuoyeImg"),
                @Result(column = "zuoyeMp3", property = "zuoyeMp3"),
                @Result(column = "zuoyeVideo", property = "zuoyeVideo"),
                @Result(column = "otherFile", property = "otherFile"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxzuoyetable"
            + "</script>")
    List<Pxzuoyetable> getAllList();
}