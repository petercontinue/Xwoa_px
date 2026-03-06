package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.classroomVo;
import com.xwcloud.cloud.model.entity.Pxclassroomtable;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
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
 * @since 2020-10-21
 */
@Repository
public interface IPxclassroomtableDao extends BaseMapper<Pxclassroomtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "classRoomName", property = "classRoomName"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "ischongtu", property = "ischongtu"),
            @Result(column = "recordInStaffID", property = "recordInStaffID"),
            @Result(column = "recordInTime", property = "recordInTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxclassroomtable"
            + "</script>")
    List<Pxclassroomtable> getAllList();

    /**
     * 分页查询教室信息
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.id, a.classRoomName,a.recordInTime,b.campusName,c.staffName,a.ischongtu FROM pxclassroomtable as a LEFT JOIN pxcampustable as b" +
            " on a.campusID=b.id LEFT JOIN pxstafftable as c on a.recordInStaffID=c.id" + " WHERE 1=1"+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<classroomVo> GetClassRoomPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT * FROM pxpaiketable WHERE classRoomID = #{classRoomID}"+"</script>")
    List<Pxpaiketable> GetpaikeByclassRoomid(String classRoomID);


    @Select("<script>"+"SELECT * FROM pxclassroomtable WHERE classRoomName = #{classRoomName} and qiyeID= #{qiyeID} and campusID= #{campusID}"+" </script>")
    List<Pxclassroomtable> GetClassRoomListByqiyeID_CampusID_classRoomName_Dao(Long qiyeID, Long campusID, String classRoomName);
}