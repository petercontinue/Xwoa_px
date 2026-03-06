package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.haveTimeCrVO;
import com.xwcloud.cloud.model.entity.Pxclassroomtable;
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
 * @since 2020-12-24
 */
@Repository
public interface IPxclassroomtableDao extends BaseMapper<Pxclassroomtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "classRoomName", property = "classroomname"),
            @Result(column = "campusID", property = "campusid"),
            @Result(column = "recordInStaffID", property = "recordinstaffid"),
            @Result(column = "recordInTime", property = "recordintime"),
            @Result(column = "qiyeID", property = "qiyeid"),
            @Result(column = "ischongtu", property = "ischongtu"),
    })
    @Select("<script>" +
            "SELECT * from  pxclassroomtable"
            + "</script>")
    List<Pxclassroomtable> getAllList();

    /**
     * 获取空闲教室
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT  a.campusID campusID,b.campusName campusName,a.id classroomID,a.classRoomName classRoomName\n" +
            "from pxclassroomtable a\n" +
            "LEFT JOIN pxcampustable b on a.campusID=b.id\n" +
            "WHERE b.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<haveTimeCrVO> gethavetimeclassRoomList(Page page, @Param("ew") QueryWrapper queryWrapper);
}