package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.OA.OaLiushuiYewu;
import com.xwcloud.cloud.model.OA.Vo.YewuLiushuiVo;
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
 * @since 2021-07-13
 */
@Repository
public interface IOaLiushuiYewuDao extends BaseMapper<OaLiushuiYewu> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "liushuiType", property = "liushuiType"),
            @Result(column = "liushuishuoming", property = "liushuishuoming"),
            @Result(column = "addUser", property = "addUser"),
            @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  oa_liushui_yewu"
            + "</script>")
    List<OaLiushuiYewu> getAllList();


    @Results(id = "YewuLiushuiInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "liushuiType", column = "liushuiType"),
            @Result(property = "liushuishuoming", column = "liushuishuoming"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "addTime", column = "addTime")
    })
    @Select("<script>" +
            "SELECT yewuliushui.id,\n" +
            "yewuliushui.liushuiType,\n" +
            "yewuliushui.liushuishuoming,\n" +
            "yewuliushui.addTime,\n" +
            "kehu.kehucompanyname,\n" +
            "staff.staffName\n" +
            "FROM oa_liushui_yewu yewuliushui\n" +
            "LEFT JOIN oa_kehu kehu on yewuliushui.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff staff on yewuliushui.addUser=staff.id " + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
        //获取所有的业务流水信息
    IPage<YewuLiushuiVo> getAllYewuLiushuiInfo(Page<YewuLiushuiVo> page, @Param("ew") Wrapper wrapper);
}