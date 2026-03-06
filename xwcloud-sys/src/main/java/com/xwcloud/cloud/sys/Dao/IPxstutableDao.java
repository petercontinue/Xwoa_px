package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstutable;
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
 * @author yinqi
 * @since 2020-10-18
 */
@Repository
public interface IPxstutableDao extends BaseMapper<Pxstutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "zidingyiStuID", property = "zidingyiStuID"),
            @Result(column = "stuName", property = "stuName"),
            @Result(column = "parentTel", property = "parentTel"),
            @Result(column = "passwd", property = "passwd"),
            @Result(column = "activity", property = "activity"),
            @Result(column = "stuSex", property = "stuSex"),
            @Result(column = "stuTel", property = "stuTel"),
            @Result(column = "parentTelRelation", property = "parentTelRelation"),
            @Result(column = "buxiStateID", property = "buxiStateID"),
            @Result(column = "stuGradeID", property = "stuGradeID"),
            @Result(column = "stuXuexi", property = "stuXuexi"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "banzhurenTeacherID", property = "banzhurenTeacherID"),
            @Result(column = "dengjiTeacherID", property = "dengjiTeacherID"),
            @Result(column = "dengjiTime", property = "dengjiTime"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "unionid", property = "unionid"),
            @Result(column = "remainXuefei", property = "remainXuefei"),
            @Result(column = "stubirth", property = "stubirth"),
            @Result(column = "jifenNum", property = "jifenNum"),
            @Result(column = "fenpeiDate", property = "fenpeiDate"),
            @Result(column = "fenpeiStaffID", property = "fenpeiStaffID"),
            @Result(column = "IDImage", property = "IDImage"),
            @Result(column = "IDnumber", property = "IDnumber"),
            @Result(column = "roomid", property = "roomid"),
            @Result(column = "oldSchoolTeacher", property = "oldSchoolTeacher"),
            @Result(column = "oldSchool", property = "oldSchool"),
            @Result(column = "ruxuechengji", property = "ruxuechengji"),
            @Result(column = "laoshiyaoqiu", property = "laoshiyaoqiu"),
            @Result(column = "jijixing", property = "jijixing"),
            @Result(column = "xingge", property = "xingge"),
            @Result(column = "yxSubjects", property = "yxSubjects"),
            @Result(column = "lastHuifangTime", property = "lastHuifangTime"),
            @Result(column = "nextHuifangTime", property = "nextHuifangTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxstutable"
            + "</script>")
    List<Pxstutable> getAllList();

    @Select("<script>" + "SELECT * FROM pxstutable WHERE parentTel = #{parentTel}" + "</script>")
    Pxstutable GetstuInfoByParentTel(String parentTel);

    @Select("<script>" +
            "SELECT tm.id,tm.longtimestu from (\n" +
            "SELECT a.id,(SELECT Count(b.id) from pxkeshistutable b where a.id=b.stuID and b.haveclassDate &gt; (SELECT DATE_ADD(NOW(), INTERVAL - 3 MONTH))) longtimestu from pxstutable a" +
            " where a.qiyeID=#{qiyeID}  GROUP BY a.id \n" +
            ") tm\n" +
            "  where tm.longtimestu = 0"
            + "</script>")
    List<HashMap<String, String>> getlongtimenokeshiStu(Long qiyeID);


    @Select("<script>" +
            "select count(a.id) from pxstutable a left JOIN pxwxusertable b on a.parentTel=b.tel where a.qiyeID=1 and b.id is NULL " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY a.id"
            + "</script>")
    List<HashMap<String, String>> getwxNoUserList(@Param("ew") QueryWrapper queryWrapper);

}