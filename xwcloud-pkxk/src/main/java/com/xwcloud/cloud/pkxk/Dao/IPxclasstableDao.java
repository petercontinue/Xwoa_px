package com.xwcloud.cloud.pkxk.Dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.getclassVo;
import com.xwcloud.cloud.model.entity.Pxclasstable;

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
 * @since 2020-12-01
 */
@Repository
public interface IPxclasstableDao extends BaseMapper<Pxclasstable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "zidingyiClassID", property = "zidingyiClassID"),
            @Result(column = "className", property = "className"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "maxStuNum", property = "maxStuNum"),
            @Result(column = "is1v1Class", property = "is1v1Class"),
            @Result(column = "isdelete", property = "isdelete"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxclasstable"
            + "</script>")
    List<Pxclasstable> getAllList();

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxclasstable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxclasstable> selectclass(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 按校区获取班级
     *
     * @param campusID
     * @return
     */
    @Select("<script>" + "SELECT id id,className name from  pxclasstable where campusID=#{campusID} and qiyeID=#{qiyeID}" + "</script>")
    List<getclassVo> getclassbycam(Long campusID, Long qiyeID);


    @Select("<script>" +
            "SELECT a.id id,concat(concat(b.campusName,'',':'),' ', a.className) name,a.is1v1Class is1v1Class from  pxclasstable a \n" +
            "left join pxcampustable b on a.campusID=b.id " +
            "where a.isShow=1 and a.isdelete!=true "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<getclassVo> NOpaikegetclass(@Param("ew") QueryWrapper queryWrapper);
}