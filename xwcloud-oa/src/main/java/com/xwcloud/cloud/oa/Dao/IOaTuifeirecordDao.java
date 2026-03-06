package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.OA.OaTuifeirecord;
import com.xwcloud.cloud.model.OA.Vo.TuifeirecordVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-01
 */
@Repository
public interface IOaTuifeirecordDao extends BaseMapper<OaTuifeirecord> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "operatetuifeiDatetime", property = "operatetuifeiDatetime"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "salestaffID", property = "salestaffID"),
            @Result(column = "tuifeiDate", property = "tuifeiDate"),
            @Result(column = "tuifeiMoney", property = "tuifeiMoney"),
            @Result(column = "tuifeiReason", property = "tuifeiReason"),
    })
    @Select("<script>" +
            "SELECT * from  oa_tuifeirecord"
            + "</script>")
    List<OaTuifeirecord> getAllList();

    @Results(id = "TuifeirecordInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "operatetuifeiDatetime", column = "operatetuifeiDatetime"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "qiandanID", column = "qiandanID"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "tuifeiDate", column = "tuifeiDate"),
            @Result(property = "tuifeiMoney", column = "tuifeiMoney"),
            @Result(property = "tuifeiReason", column = "tuifeiReason")
    })
    @Select("<script>" + "SELECT tuifeirecord.id,\n" +
            "tuifeirecord.operatetuifeiDatetime,\n" +
            "kehu.kehucompanyname,\n" +
            "tuifeirecord.qiandanID,\n" +
            "staff.staffName,\n" +
            "tuifeirecord.tuifeiDate,\n" +
            "tuifeirecord.tuifeiMoney,\n" +
            "tuifeirecord.tuifeiReason\n" +
            "from oa_tuifeirecord tuifeirecord\n" +
            "LEFT JOIN oa_kehu kehu on tuifeirecord.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff staff on tuifeirecord.salestaffID=staff.id where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<TuifeirecordVo> getAllTuifeirecordInfo(Page<TuifeirecordVo> page, @Param("ew") Wrapper wrapper);

    @ResultMap("TuifeirecordInfo")
    @Select("SELECT tuifeirecord.id,\n" +
            "tuifeirecord.operatetuifeiDatetime,\n" +
            "kehu.kehucompanyname,\n" +
            "tuifeirecord.qiandanID,\n" +
            "staff.staffName,\n" +
            "tuifeirecord.tuifeiDate,\n" +
            "tuifeirecord.tuifeiMoney,\n" +
            "tuifeirecord.tuifeiReason\n" +
            "from oa_tuifeirecord tuifeirecord\n" +
            "LEFT JOIN oa_kehu kehu on tuifeirecord.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff staff on tuifeirecord.salestaffID=staff.id\n" +
            "where tuifeirecord.id=#{id}")
        //根据id查询一条退费记录
    TuifeirecordVo getOneTuifeirecordById(Long id);
}