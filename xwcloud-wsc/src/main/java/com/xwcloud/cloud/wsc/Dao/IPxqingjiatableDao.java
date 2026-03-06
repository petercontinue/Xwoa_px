package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxqingjiatable;
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
 * @since 2021-05-06
 */
@Repository
public interface IPxqingjiatableDao extends BaseMapper<Pxqingjiatable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "stuid", property = "stuid"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qingjiaDateTime", property = "qingjiaDateTime"),
            @Result(column = "qjState", property = "qjState"),
            @Result(column = "cancelQjDateTime", property = "cancelQjDateTime"),
            @Result(column = "cancelQjReason", property = "cancelQjReason"),
            @Result(column = "shenheRen", property = "shenheRen"),
            @Result(column = "shenheState", property = "shenheState"),
            @Result(column = "shenheNopassReason", property = "shenheNopassReason"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "paikeID", property = "paikeID"),
            @Result(column = "banzhurenID", property = "banzhurenID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "shenheDateTime", property = "shenheDateTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxqingjiatable"
            + "</script>")
    List<Pxqingjiatable> getAllList();
}