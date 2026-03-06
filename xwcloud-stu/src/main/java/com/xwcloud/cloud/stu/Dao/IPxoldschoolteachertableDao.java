package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxoldschoolteachertable;
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
 * @since 2020-11-23
 */
@Repository
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

    @Select("<script>" + "select * from pxoldschoolteachertable where oldSchoolTeacherName=#{oldSchoolTeacherName} and oldSchoolID=#{oldSchoolID} and qiyeID=#{qiyeID} " + "</script>")
    List<Pxoldschoolteachertable> getoldTeacher(String oldSchoolTeacherName, Long oldSchoolID, Long qiyeID);
}