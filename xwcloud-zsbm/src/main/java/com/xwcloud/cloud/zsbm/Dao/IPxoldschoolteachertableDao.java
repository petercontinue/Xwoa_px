package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxoldschoolteachertable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxoldschoolteachertableDao extends BaseMapper<Pxoldschoolteachertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "oldSchoolTeacherID", property = "oldSchoolTeacherID"),
            @Result(column = "oldSchoolTeacherName", property = "oldSchoolTeacherName"),
            @Result(column = "oldSchoolTeacherTel", property = "oldSchoolTeacherTel"),
            @Result(column = "oldSchoolID", property = "oldSchoolID"),
    })
    @Select("<script>" +
            "SELECT * from  pxoldschoolteachertable"
            + "</script>")
    List<Pxoldschoolteachertable> getAllList();

    @Select("<script>"+"SELECT * FROM pxoldSchoolTeacherTable WHERE oldSchoolTeacherName=#{oldSchoolTeacherName} AND oldSchoolID=#{oldSchoolID}"+"</script>")
    Pxoldschoolteachertable GetOldschoolteacherBytnameAndsID(String oldSchoolTeacherName,Long oldSchoolID);
}