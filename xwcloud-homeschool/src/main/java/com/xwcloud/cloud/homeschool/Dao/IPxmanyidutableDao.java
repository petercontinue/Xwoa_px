package com.xwcloud.cloud.homeschool.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.PxmanyidutableVo;
import com.xwcloud.cloud.model.entity.Pxmanyidutable;
import org.apache.ibatis.annotations.Param;
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
 * @since 2020-11-04
 */
public interface IPxmanyidutableDao extends BaseMapper<Pxmanyidutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "weixinmessageId", property = "weixinmessageId"),
            @Result(column = "stuId", property = "stuId"),
            @Result(column = "TeachingLevelOfTeachers", property = "TeachingLevelOfTeachers"),
            @Result(column = "TeachingEffectOfTeachers", property = "TeachingEffectOfTeachers"),
            @Result(column = "ServiceAttitude", property = "ServiceAttitude"),
            @Result(column = "SchoolManagementNorms", property = "SchoolManagementNorms"),
            @Result(column = "SchoolFacilities", property = "SchoolFacilities"),
            @Result(column = "pingjiaDate", property = "pingjiaDate"),
            @Result(column = "concent", property = "concent"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxmanyidutable"
            + "</script>")
    List<Pxmanyidutable> getAllList();

    @Select("<script>" +
            "SELECT manyidu.*,campus.campusName,stu.stuName,staffName as banzhurenName,stu.parentTel " +
            "FROM pxmanyidutable manyidu " +
            "LEFT JOIN pxstutable stu ON manyidu.stuId=stu.id " +
            "LEFT JOIN pxcampustable campus ON stu.campusID=campus.id " +
            "LEFT JOIN pxstafftable staff ON stu.banzhurenTeacherID=staff.id"+
            " WHERE campus.isOpen !=2"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxmanyidutableVo> getPage(Page page, @Param("ew") Wrapper wrapper);
}