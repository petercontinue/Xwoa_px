package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
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
public interface IPxkeshistutableDao extends BaseMapper<Pxkeshistutable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "classID", property = "classID"),
                @Result(column = "campusID", property = "campusID"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "tongkekechengID", property = "tongkekechengID"),
                @Result(column = "kechengContent", property = "kechengContent"),
                @Result(column = "StuGradeID", property = "stuGradeID"),
                @Result(column = "teacherIDs", property = "teacherIDs"),
                @Result(column = "teacherNames", property = "teacherNames"),
                @Result(column = "haveClassDate", property = "haveClassDate"),
                @Result(column = "weekN", property = "weekN"),
                @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
                @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
                @Result(column = "keshiNum", property = "keshiNum"),
                @Result(column = "buxiStyleID", property = "buxiStyleID"),
                @Result(column = "classTimeStyleID", property = "classTimeStyleID"),
                @Result(column = "kechengPrice", property = "kechengPrice"),
                @Result(column = "stuKaoqingStyle", property = "stuKaoqingStyle"),
                @Result(column = "dakaoqingStyle", property = "dakaoqingStyle"),
                @Result(column = "shuoMing", property = "shuoMing"),
                @Result(column = "adduser", property = "adduser"),
                @Result(column = "addtime", property = "addtime"),
                @Result(column = "zhujiao", property = "zhujiao"),
                @Result(column = "buxikechengID", property = "buxikechengID"),
                @Result(column = "shareBuxiID", property = "shareBuxiID"),
                @Result(column = "banzhurenStaffID", property = "banzhurenStaffID"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshistutable"
            + "</script>")
    List<Pxkeshistutable> getAllList();
}