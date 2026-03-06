package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.OA.OaLiushui;
import com.xwcloud.cloud.model.OA.Vo.LiushuiInfoVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Repository
public interface IOaLiushuiDao extends BaseMapper<OaLiushui> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "jinbanrenStaffID", property = "jinbanrenStaffID"),
            @Result(column = "liushuiDatetime", property = "liushuiDatetime"),
            @Result(column = "liushuishuoming", property = "liushuishuoming"),
            @Result(column = "lurutime", property = "lurutime"),
            @Result(column = "paymoneystyleID", property = "paymoneystyleID"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "shourumoney", property = "shourumoney"),
            @Result(column = "zhichumoney", property = "zhichumoney"),
    })
    @Select("<script>" +
            "SELECT * from  oa_liushui"
            + "</script>")
    List<OaLiushui> getAllList();

    @Results(id = "LiushuiInfo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffName", property = "staffName"),
            @Result(column = "liushuiDatetime", property = "liushuiDatetime"),
            @Result(column = "jinbanrenStaffID", property = "jinbanrenStaffID"),
            @Result(column = "liushuishuoming", property = "liushuishuoming"),
            @Result(column = "lurutime", property = "lurutime"),
            @Result(column = "paymoneyStyleName", property = "paymoneyStyleName"),
            @Result(column = "paymoneystyleID", property = "paymoneystyleID"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "shourumoney", property = "shourumoney"),
            @Result(column = "zhichumoney", property = "zhichumoney"),
            @Result(column = "liushuiStyle", property = "liushuiStyle"),
            @Result(column = "liushuiStyleID", property = "liushuiStyleID"),
            @Result(column = "isShouruOrZhichu", property = "isShouruOrZhichu"),
    })
    @Select("<script>" +
            "SELECT liushui.id, \n" +
            "staff.staffName, \n" +
            "liushui.liushuiDatetime, \n" +
            "liushui.liushuishuoming, \n" +
            "liushui.lurutime, \n" +
            "paymoneystyle.paymoneyStyleName,\n" +
            "liushui.paymoneystyleID, \n" +
            "liushui.qiandanID, \n" +
            "liushui.shourumoney, \n" +
            "liushui.zhichumoney, \n" +
            "liushuistyle.liushuiStyle, \n" +
            "liushui.liushuiStyleID,\n" +
            "liushui.isShouruOrZhichu\n" +
            "\n" +
            "FROM oa_liushui liushui\n" +
            "LEFT JOIN oa_staff staff on liushui.jinbanrenStaffID=staff.id\n" +
            "LEFT JOIN oa_paymoneystyle paymoneystyle on liushui.paymoneystyleID=paymoneystyle.id\n" +
            "LEFT JOIN oa_liushui_style liushuistyle on liushui.liushuiStyleID=liushuistyle.id " + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<LiushuiInfoVo> getAllLiushuiInfo(Page<LiushuiInfoVo> page, @Param("ew") Wrapper wrapper);


    @ResultMap(value = "LiushuiInfo")
    @Select("<script>" +
            "SELECT liushui.id, \n" +
            "staff.staffName, \n" +
            "liushui.liushuiDatetime, \n" +
            "liushui.jinbanrenStaffID, \n" +
            "liushui.liushuishuoming, \n" +
            "liushui.lurutime, \n" +
            "paymoneystyle.paymoneyStyleName,\n" +
            "liushui.paymoneystyleID, \n" +
            "liushui.qiandanID, \n" +
            "liushui.shourumoney, \n" +
            "liushui.zhichumoney, \n" +
            "liushuistyle.liushuiStyle, \n" +
            "liushui.liushuiStyleID,\n" +
            "liushui.isShouruOrZhichu\n" +
            "\n" +
            "FROM oa_liushui liushui\n" +
            "LEFT JOIN oa_staff staff on liushui.jinbanrenStaffID=staff.id\n" +
            "LEFT JOIN oa_paymoneystyle paymoneystyle on liushui.paymoneystyleID=paymoneystyle.id\n" +
            "LEFT JOIN oa_liushui_style liushuistyle on liushui.liushuiStyleID=liushuistyle.id where liushui.id=#{id}"
            + "</script>")
    LiushuiInfoVo getOneLiushuiInfoById(Long id);
}