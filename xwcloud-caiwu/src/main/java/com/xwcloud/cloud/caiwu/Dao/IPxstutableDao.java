package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxstutable;
import org.apache.ibatis.annotations.Param;
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
 * @since 2021-01-17
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
            @Result(column = "openid", property = "openid"),
            @Result(column = "unionid", property = "unionid"),
            @Result(column = "remainXuefei", property = "remainXuefei"),
            @Result(column = "remainChongzhi", property = "remainChongzhi"),
            @Result(column = "stubirth", property = "stubirth"),
            @Result(column = "jifenNum", property = "jifenNum"),
            @Result(column = "IDImage", property = "iDImage"),
            @Result(column = "IDnumber", property = "iDnumber"),
            @Result(column = "roomid", property = "roomid"),
            @Result(column = "oldSchoolTeacher", property = "oldSchoolTeacher"),
            @Result(column = "oldSchool", property = "oldSchool"),
            @Result(column = "ruxuechengji", property = "ruxuechengji"),
            @Result(column = "laoshiyaoqiu", property = "laoshiyaoqiu"),
            @Result(column = "jijixing", property = "jijixing"),
            @Result(column = "xingge", property = "xingge"),
            @Result(column = "lastHuifangTime", property = "lastHuifangTime"),
            @Result(column = "nextHuifangTime", property = "nextHuifangTime"),
            @Result(column = "stuPhoto", property = "stuPhoto"),
            @Result(column = "yxFromID", property = "yxFromID"),
            @Result(column = "yxLevelID", property = "yxLevelID"),
            @Result(column = "yixiangkemu", property = "yixiangkemu"),
            @Result(column = "yxshichangTeacherID", property = "yxshichangTeacherID"),
            @Result(column = "yxfenpeistaffID", property = "yxfenpeistaffID"),
            @Result(column = "yxfenpeiDate", property = "yxfenpeiDate"),
            @Result(column = "daoruDate", property = "daoruDate"),
            @Result(column = "lastFollowDate", property = "lastFollowDate"),
            @Result(column = "luruType", property = "luruType"),
            @Result(column = "dengjiTeacherID", property = "dengjiTeacherID"),
            @Result(column = "dengjiTime", property = "dengjiTime"),
            @Result(column = "nextGenjinTime", property = "nextGenjinTime"),
            @Result(column = "tingkeTime", property = "tingkeTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxstutable"
            + "</script>")
    List<Pxstutable> getAllList();


    @Select("<script>" +
            "SELECT a.id id ,a.stuName name " +
            "from pxstutable a \n" +
            "LEFT JOIN pxstugradetable b on a.stuGradeID=b.id\n" +
            "LEFT JOIN pxcampustable c on a.campusID=c.id\n" +
            "WHERE c.isOpen !=2 and (a.buxiStateID=1 or a.buxiStateID=2 or a.buxiStateID=3 or a.buxiStateID=6)" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<listVo> getallstu(@Param("ew") QueryWrapper queryWrapper);
}