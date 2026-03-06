package com.xwcloud.cloud.homeschool.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.PxstuFeedbackVo;
import com.xwcloud.cloud.model.entity.Pxtousutable;
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
public interface IPxtousutableDao extends BaseMapper<Pxtousutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "tousuContent", property = "tousuContent"),
            @Result(column = "tousuDate", property = "tousuDate"),
            @Result(column = "chayueState", property = "chayueState"),
            @Result(column = "chayueDate", property = "chayueDate"),
            @Result(column = "chayueSatff", property = "chayueSatff"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtousutable"
            + "</script>")
    List<Pxtousutable> getAllList();

    @Select("<script>" +
            "SELECT tousu.*,campus.campusName,stu.stuName,staff.staffName as banzhurenName,stugrade.stuGradeName," +
            "stu.parentTel,chayueren.staffName as chayueSatffName  " +
            "from pxtousutable tousu " +
            "LEFT JOIN pxstutable stu ON tousu.stuID=stu.id " +
            "LEFT JOIN pxstugradetable stugrade ON stu.stuGradeID=stugrade.id " +
            "LEFT JOIN pxcampustable campus ON stu.campusID=campus.id " +
            "LEFT JOIN pxstafftable staff ON stu.banzhurenTeacherID= staff.id " +
            "LEFT JOIN pxstafftable chayueren ON tousu.chayueSatff=chayueren.id "+
            " WHERE campus.isOpen !=2"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxstuFeedbackVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT tousu.*,campus.campusName,stu.stuName,staff.staffName as banzhurenName,stugrade.stuGradeName," +
            "stu.parentTel,chayueren.staffName as chayueSatffName  " +
            "from  pxtousutable tousu " +
            "LEFT JOIN pxstutable stu ON tousu.stuID=stu.id " +
            "LEFT JOIN pxstugradetable stugrade ON stu.stuGradeID=stugrade.id " +
            "LEFT JOIN pxcampustable campus ON stu.campusID=campus.id " +
            "LEFT JOIN pxstafftable staff ON stu.banzhurenTeacherID= staff.id " +
            "LEFT JOIN pxstafftable chayueren ON tousu.chayueSatff=chayueren.id "+
            " WHERE campus.isOpen !=2"+
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxstuFeedbackVo> getJoinList(@Param("ew") Wrapper wrapper);
}