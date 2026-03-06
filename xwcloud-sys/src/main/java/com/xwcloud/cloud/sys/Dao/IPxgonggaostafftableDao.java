package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxgonggaostafftable;
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
 * @since 2020-12-23
 */
@Repository
public interface IPxgonggaostafftableDao extends BaseMapper<Pxgonggaostafftable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "gonggaoId", property = "gonggaoid"),
            @Result(column = "jieshouDate", property = "jieshoudate"),
            @Result(column = "IsRead", property = "isread"),
            @Result(column = "staffID", property = "staffid"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  pxgonggaostafftable"
            + "</script>")
    List<Pxgonggaostafftable> getAllList();
}