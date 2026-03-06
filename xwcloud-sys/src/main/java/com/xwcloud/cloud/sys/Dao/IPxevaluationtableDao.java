package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxevaluationtable;
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
 * @since 2021-07-29
 */
@Repository
public interface IPxevaluationtableDao extends BaseMapper<Pxevaluationtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "note", property = "note"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "keshiTeachTabID", property = "keshiTeachTabID"),
            @Result(column = "stuid", property = "stuid"),
            @Result(column = "teacherid", property = "teacherid"),
            @Result(column = "addtime", property = "addtime"),
            @Result(column = "images", property = "images"),
            @Result(column = "pjmp3Url", property = "pjmp3Url"),
            @Result(column = "pjvideoUrl", property = "pjvideoUrl"),
            @Result(column = "type", property = "type"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxevaluationtable"
            + "</script>")
    List<Pxevaluationtable> getAllList();
}