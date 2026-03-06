package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxworkdayrecordtable;
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
public interface IPxworkdayrecordtableDao extends BaseMapper<Pxworkdayrecordtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "staffID", property = "staffID"),
                @Result(column = "LogDate", property = "logDate"),
                @Result(column = "LogContent", property = "logContent"),
                @Result(column = "ImgsUrl", property = "imgsUrl"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxworkdayrecordtable"
            + "</script>")
    List<Pxworkdayrecordtable> getAllList();
}