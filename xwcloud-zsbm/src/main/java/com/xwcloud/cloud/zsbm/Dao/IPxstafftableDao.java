package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-11
 */
@Repository
public interface IPxstafftableDao extends BaseMapper<Pxstafftable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffName", property = "staffName"),
            @Result(column = "staffTel", property = "staffTel"),
            @Result(column = "password", property = "password"),
            @Result(column = "staffSex", property = "staffSex"),
            @Result(column = "staffBirthday", property = "staffBirthday"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "staffPostID", property = "staffPostID"),
            @Result(column = "staffState", property = "staffState"),
            @Result(column = "photo", property = "photo"),
            @Result(column = "QQ", property = "qq"),
            @Result(column = "email", property = "email"),
            @Result(column = "wx", property = "wx"),
            @Result(column = "douyin", property = "douyin"),
            @Result(column = "joinTime", property = "joinTime"),
            @Result(column = "shuoMing", property = "shuoMing"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "unionid", property = "unionid"),
            @Result(column = "phoneMac", property = "phoneMac"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "showInApp", property = "showInApp"),
    })
    @Select("<script>" +
            "SELECT * from  pxstafftable"
            + "</script>")
    List<Pxstafftable> getAllList();

    @Select("<script>" +
            "SELECT a.id id,concat( b.campusName,'_', a.staffName) name from pxstafftable a" +
            " left join pxcampustable b on a.campusID=b.id " +
            "where a.staffState=1 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
   List<HashMap<String,Object>> getCampusTostaff(@Param("ew") QueryWrapper queryWrapper);

    //---------------------意向学员-------------------------------------
    @Select("<script>" +
            "SELECT DISTINCT a.*\n" +
            "FROM pxstafftable a\n" +
            "JOIN pxteachsubjecttable b ON a.id=b.staffID\n" +
            "WHERE a.qiyeID=#{qiyeID} AND b.teachSubjectID=#{subjectID}" +
            "</script>")
    List<Pxstafftable> getSubjectTeacher(Long subjectID, Long qiyeID);
}