package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.chongzhiListVo;
import com.xwcloud.cloud.model.Vo.chongzhiliushuiVO;
import com.xwcloud.cloud.model.entity.Pxchongzhitable;

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
 * @since 2020-11-12
 */
@Repository
public interface IPxchongzhitableDao extends BaseMapper<Pxchongzhitable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "shijiChongzhiMoney", property = "shijiChongzhiMoney"),
            @Result(column = "songMoney", property = "songMoney"),
            @Result(column = "shideTotalMoney", property = "shideTotalMoney"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "yejiStaffID", property = "yejiStaffID"),
            @Result(column = "chongzhiDatetime", property = "chongzhiDatetime"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxchongzhitable"
            + "</script>")
    List<Pxchongzhitable> getAllList();

    /**
     * 分页查询充值信息
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>"+"SELECT a.id,a.stuID,a.shijiChongzhiMoney,a.songMoney,a.shideTotalMoney,a.shuoming,a.chongzhiDatetime,b.stuName,c.staffName FROM pxchongzhitable AS a LEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "LEFT JOIN pxstafftable as c ON a.yejiStaffID = c.id"+ " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<chongzhiListVo> GetUserChongzhiListPages(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 不分页查询充值信息
     * @param wrapper
     * @return
     */
    @Select("<script>"+"SELECT a.id,a.stuID,a.shijiChongzhiMoney,a.songMoney,a.shideTotalMoney,a.shuoming,a.chongzhiDatetime,b.stuName,c.staffName FROM pxchongzhitable AS a LEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "LEFT JOIN pxstafftable as c ON a.yejiStaffID = c.id"+ " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<chongzhiListVo> GetUserChongzhiListList(@Param("ew") Wrapper wrapper);

    /**
     * 分页查询充值流水
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>"+
            "SELECT\n" +
            "\tb.id AS id,\n" +
            "\tb.zidingyiStuID,\n" +
            "\td.campusName,\n" +
            "\tb.stuName,\n" +
            "\ta.shijiChongzhiMoney,\n" +
            "\ta.songMoney,\n" +
            "\ta.shideTotalMoney,\n" +
            "\tc.staffName,\n" +
            "\ta.addTime AS addTime,\n" +
            "\te.stuGradeName,\n" +
            "(select GROUP_CONCAT( moneystyleName )  from pxpaymoneystyletable where qiyeID=a.qiyeID and FIND_IN_SET( id, a.PayMoneyStyle ) ) paymoneystyle\n" +
            "FROM\n" +
            "\tpxchongzhitable AS a\n" +
            "\tJOIN pxstutable AS b ON a.stuID = b.id\n" +
            "\tJOIN pxstafftable AS c ON a.addStaffID = c.id\n" +
            "\tJOIN pxcampustable AS d ON b.campusID = d.id\n" +
            "\tJOIN pxstugradetable AS e ON b.stuGradeID = e.id " +
            " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<chongzhiliushuiVO> GetChongzhiliushuiPages(Page page, @Param("ew") Wrapper wrapper);

}