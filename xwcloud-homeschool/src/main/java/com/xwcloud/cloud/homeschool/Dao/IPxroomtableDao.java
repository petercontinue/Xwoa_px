package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.ExportStuRoomVo;
import com.xwcloud.cloud.model.Vo.RoomManaVo;
import com.xwcloud.cloud.model.entity.Pxroomtable;
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
 * @since 2020-11-04
 */
@Repository
public interface IPxroomtableDao extends BaseMapper<Pxroomtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "number", property = "number"),
            @Result(column = "renshu", property = "renshu"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxroomtable"
            + "</script>")
    List<Pxroomtable> getAllList();

    @Select("<script>" +
            "select pxcampustable.campusName as campusName,stu.id as stuID,stu.stuName as stuName,pxstugradetable.stuGradeName as stuGradeName,pxroomtable.number as RoomNum,pxstafftable.staffName as banzhuren " +
            "from pxstutable as stu " +
            "LEFT JOIN pxcampustable on stu.campusID=pxcampustable.id " +
            "LEFT JOIN pxstugradetable on stu.stuGradeID=pxstugradetable.id " +
            "LEFT JOIN pxroomtable on stu.roomid=pxroomtable.id " +
            "LEFT JOIN pxstafftable on stu.banzhurenTeacherID=pxstafftable.id " +
            " WHERE pxcampustable.isOpen=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<ExportStuRoomVo> exportStuRoom(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>"+"select * from pxroomtable where campusID=#{campusID} and number=#{number}"+"</script>")
    List<Pxroomtable> getAdd(String campusID ,String number);

    @Select("<script>"+"select * from pxroomtable where campusID=#{campusID} and number=#{number} and id!=#{id}"+"</script>")
    List<Pxroomtable> getRoomUp(String campusID ,String number,String id);

    @Select("<script>"+
            "select pxcampustable.campusName as campusName,rm.renshu as Num,rm.number as RoomName " +
            "from pxroomtable as rm left join pxcampustable on rm.campusID=pxcampustable.id " +
            " WHERE pxcampustable.isOpen=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<RoomManaVo> getRmManagePage(Page page,@Param("ew") QueryWrapper queryWrapper);
}