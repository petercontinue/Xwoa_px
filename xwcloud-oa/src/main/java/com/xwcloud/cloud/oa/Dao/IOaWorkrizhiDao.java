package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.OA.OaWorkrizhi;
import com.xwcloud.cloud.model.OA.Vo.WorkrizhiVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
@Repository
public interface IOaWorkrizhiDao extends BaseMapper<OaWorkrizhi> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "workHuibaoShuoming", property = "workHuibaoShuoming"),
            @Result(column = "workJihua", property = "workJihua"),
            @Result(column = "readState", property = "readState"),
            @Result(column = "type", property = "type"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  oa_workrizhi"
            + "</script>")
    List<OaWorkrizhi> getAllList();

    @Results(id = "WorkrizhiInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "workHuibaoShuoming", column = "workHuibaoShuoming"),
            @Result(property = "workJihua", column = "workJihua"),
            @Result(property = "readState", column = "readState"),
            @Result(property = "type", column = "type"),
            @Result(property = "huibaoToStaffID", column = "huibaoToStaffID"),
            @Result(property = "huibaoTOStaffName", column = "huibaoTOStaffName"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "addTime", column = "addTime")
    })
    @Select("<script>" + "SELECT workrizhi.id,\n" +
            "workrizhi.workHuibaoShuoming,\n" +
            "workrizhi.workJihua,\n" +
            "workrizhi.readState,\n" +
            "workrizhi.type,\n" +
            "workrizhi.addStaffID,\n" +
            "staff2.staffName as huibaoTOStaffName,\n" +
            "workrizhi.addTime,\n" +
            "staff.staffName\n" +
            "from oa_workrizhi workrizhi\n" +
            "LEFT JOIN oa_staff staff on workrizhi.addStaffID=staff.id \n" +
            "LEFT JOIN oa_staff staff2 on workrizhi.addStaffID=staff2.id " + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<WorkrizhiVo> getAllWorkrizhiInfo(Page<WorkrizhiVo> page, @Param("ew") Wrapper wrapper);

    @ResultMap(value = "WorkrizhiInfo")
    @Select("<script>" +
            "SELECT workrizhi.id,\n" +
            "workrizhi.workHuibaoShuoming,\n" +
            "workrizhi.workJihua,\n" +
            "workrizhi.readState,\n" +
            "workrizhi.type,\n" +
            "workrizhi.huibaoToStaffID,\n" +
            "staff2.staffName as huibaoTOStaffName,\n" +
            "workrizhi.addTime,\n" +
            "staff.staffName\n" +
            "from oa_workrizhi workrizhi\n" +
            "LEFT JOIN oa_staff staff on workrizhi.addStaffID=staff.id \n" +
            "LEFT JOIN oa_staff staff2 on workrizhi.huibaoToStaffID=staff2.id \n" +
            " where workrizhi.id=#{id}"
            + "</script>")
    WorkrizhiVo getOneWorkrizhiById(Long id);


}