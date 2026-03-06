package com.xwcloud.cloud.homeschool.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxroomknowingtable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxroomknowingtableDao extends BaseMapper<Pxroomknowingtable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "roomID", property = "roomID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "stubed", property = "stubed"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  pxroomknowingtable"
        + "</script>")
List<Pxroomknowingtable> getAllList();
}