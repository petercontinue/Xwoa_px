package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;
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
public interface IPxtuisongtableDao extends BaseMapper<Pxtuisongtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "staffID", property = "staffID"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "haveclassDate", property = "haveclassDate"),
                @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
                @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
                @Result(column = "note", property = "note"),
                @Result(column = "tuisongTypeName", property = "tuisongTypeName"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "role", property = "role"),
                @Result(column = "wxstate", property = "wxstate"),
                @Result(column = "appread", property = "appread"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtuisongtable"
            + "</script>")
    List<Pxtuisongtable> getAllList();
}