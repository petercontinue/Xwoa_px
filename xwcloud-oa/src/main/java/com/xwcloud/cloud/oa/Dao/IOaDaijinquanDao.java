package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.OA.OaDaijinquan;
import com.xwcloud.cloud.model.OA.Vo.DaijinquanVo;
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
public interface IOaDaijinquanDao extends BaseMapper<OaDaijinquan> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "getDjq", property = "getDjq"),
            @Result(column = "useDjq", property = "useDjq"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "addUser", property = "addUser"),
            @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  oa_daijinquan"
            + "</script>")
    List<OaDaijinquan> getAllList();


    @Results(id = "DaijinquanInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "djqRemain", column = "djqRemain"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "qiyeID", column = "qiyeID"),
            @Result(property = "getDjq", column = "getDjq"),
            @Result(property = "useDjq", column = "useDjq"),
            @Result(property = "shuoming", column = "shuoming"),
            @Result(property = "addUser", column = "addUser"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "addTime", column = "addTime")
    })
    @Select("<script>" +
            "SELECT daijinquan.id,\n" +
            "daijinquan.qiyeID,\n" +
            "daijinquan.getDjq,\n" +
            "daijinquan.useDjq,\n" +
            "daijinquan.shuoming,\n" +
            "daijinquan.addTime,\n" +
            "daijinquan.addUser,\n" +
            "staff.staffName,\n" +
            "kehu.djqRemain,\n" +
            "kehu.kehucompanyname\n" +
            "\n" +
            "from oa_daijinquan daijinquan\n" +
            "LEFT JOIN oa_kehu kehu on daijinquan.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff staff on daijinquan.addUser=staff.id " + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
        //获取所有的代金券信息
    IPage<DaijinquanVo> getAllDaijinquanInfo(Page<DaijinquanVo> page, @Param("ew") Wrapper wrapper);

    @ResultMap("DaijinquanInfo")
    @Select("SELECT daijinquan.id,\n" +
            "daijinquan.qiyeID,\n" +
            "daijinquan.getDjq,\n" +
            "daijinquan.useDjq,\n" +
            "daijinquan.shuoming,\n" +
            "daijinquan.addTime,\n" +
            "daijinquan.addUser,\n" +
            "staff.staffName,\n" +
            "kehu.djqRemain,\n" +
            "kehu.kehucompanyname\n" +
            "\n" +
            "from oa_daijinquan daijinquan\n" +
            "LEFT JOIN oa_kehu kehu on daijinquan.qiyeID=kehu.id \n" +
            "LEFT JOIN oa_staff staff on daijinquan.addUser=staff.id \n" +
            "where daijinquan.id=#{id}")
        //根据id获取一个代金券信息
    DaijinquanVo getOneDaijinquanById(Long id);
}