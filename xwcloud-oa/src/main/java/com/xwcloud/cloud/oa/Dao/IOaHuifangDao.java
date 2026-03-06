package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.OA.OaHuifang;
import com.xwcloud.cloud.model.OA.Vo.HuifangVo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
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
 * @since 2021-07-03
 */
@Repository
public interface IOaHuifangDao extends BaseMapper<OaHuifang> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "huifangContent", property = "huifangContent"),
            @Result(column = "huifangDatetime", property = "huifangDatetime"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "shuoming", property = "shuoming"),
    })
    @Select("<script>" +
            "SELECT * from  oa_huifang"
            + "</script>")
    List<OaHuifang> getAllList();

    @Results(id = "huifangInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "huifangContent", column = "huifangContent"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "shuoming", column = "shuoming"),
            @Result(property = "huifangDatetime", column = "huifangDatetime"),
            @Result(property = "addTime", column = "addTime")
    })
    @Select("SELECT huifang.id,\n" +
            "huifang.huifangContent,\n" +
            "huifang.shuoming,\n" +
            "huifang.huifangDatetime,\n" +
            "staff.staffName,\n" +
            "kehu.kehucompanyname,\n" +
            "huifang.addTime\n" +
            "\n" +
            "from oa_huifang huifang\n" +
            "LEFT JOIN oa_staff staff on huifang.addStaffID=staff.id\n" +
            "LEFT JOIN oa_kehu kehu on huifang.qiyeID=kehu.id")
    IPage<HuifangVo> getAllHuifangInfo(Page<HuifangVo> page);


    @ResultMap("huifangInfo")
    @Select("SELECT huifang.id,\n" +
            "huifang.huifangContent,\n" +
            "huifang.shuoming,\n" +
            "huifang.huifangDatetime,\n" +
            "staff.staffName,\n" +
            "kehu.kehucompanyname,\n" +
            "huifang.addTime\n" +
            "\n" +
            "from oa_huifang huifang\n" +
            "LEFT JOIN oa_staff staff on huifang.addStaffID=staff.id\n" +
            "LEFT JOIN oa_kehu kehu on huifang.qiyeID=kehu.id\n" +
            "where huifang.id=#{id}")
    //根据id获取一条回访记录
    HuifangVo getOneHuifangById(Long id);
}