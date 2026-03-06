package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxyuekestufaqitable;
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
public interface IPxyuekestufaqitableDao extends BaseMapper<Pxyuekestufaqitable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "buxikechengID", property = "buxikechengID"),
                @Result(column = "buxiStyle", property = "buxiStyle"),
                @Result(column = "teacherID", property = "teacherID"),
                @Result(column = "haveClassDate", property = "haveClassDate"),
                @Result(column = "haveLessonStartTime", property = "haveLessonStartTime"),
                @Result(column = "haveLessonEndTime", property = "haveLessonEndTime"),
                @Result(column = "faqiyuekeStuLiuyan", property = "faqiyuekeStuLiuyan"),
                @Result(column = "yuekeShenheState", property = "yuekeShenheState"),
                @Result(column = "yuekeShenheDafu", property = "yuekeShenheDafu"),
                @Result(column = "faqiYuekeStuID", property = "faqiYuekeStuID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "shenheStaffID", property = "shenheStaffID"),
                @Result(column = "shenheDatetime", property = "shenheDatetime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyuekestufaqitable"
            + "</script>")
    List<Pxyuekestufaqitable> getAllList();
}