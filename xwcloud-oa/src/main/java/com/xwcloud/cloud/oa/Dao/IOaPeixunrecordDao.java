package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.OA.OaPeixunrecord;
import com.xwcloud.cloud.model.OA.Vo.PeixunrecordVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-02
 */
@Repository
public interface IOaPeixunrecordDao extends BaseMapper<OaPeixunrecord> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addstaffID", property = "addstaffID"),
            @Result(column = "pxcontent", property = "pxcontent"),
            @Result(column = "pxDate", property = "pxDate"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  oa_peixunrecord"
            + "</script>")
    List<OaPeixunrecord> getAllList();

    @Results(id = "PeixunrecordInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "addstaffID", column = "addstaffID"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "pxcontent", column = "pxcontent"),
            @Result(property = "addTime", column = "addTime"),
            @Result(property = "pxDate", column = "pxDate"),
            @Result(property = "qiyeID", column = "qiyeID"),
            @Result(property = "kehucompanyname", column = "kehucompanyname")
    })
    @Select("<script>" + "SELECT " +
            "peixunrecord.id,\n" +
            "peixunrecord.pxcontent,\n" +
            "peixunrecord.addTime,\n" +
            "peixunrecord.pxDate,\n" +
            "peixunrecord.addstaffID,\n" +
            "peixunrecord.qiyeID,\n" +
            "kehu.kehucompanyname,\n" +
            "staff.staffName \n" +
            "from oa_peixunrecord peixunrecord\n" +
            "LEFT JOIN oa_staff staff on peixunrecord.addstaffID=staff.id\n" +
            "LEFT JOIN oa_kehu kehu on peixunrecord.qiyeID=kehu.id" + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
        //分页获取所有的培训记录信息
    IPage<PeixunrecordVo> getAllPeixunrecordInfo(Page<PeixunrecordVo> page, @Param("ew") Wrapper wrapper);

    @ResultMap("PeixunrecordInfo")
    @Select("SELECT " +
            "peixunrecord.id,\n" +
            "peixunrecord.pxcontent,\n" +
            "peixunrecord.addTime,\n" +
            "peixunrecord.pxDate,\n" +
            "peixunrecord.addstaffID,\n" +
            "peixunrecord.qiyeID,\n" +
            "kehu.kehucompanyname,\n" +
            "staff.staffName \n" +
            "from oa_peixunrecord peixunrecord\n" +
            "LEFT JOIN oa_staff staff on peixunrecord.addstaffID=staff.id\n" +
            "LEFT JOIN oa_kehu kehu on peixunrecord.qiyeID=kehu.id\n" +
            "where peixunrecord.id=#{id}")
        //根据id获取一条培训记录信息
    PeixunrecordVo getOnePeixunrecordById(Long id);
}