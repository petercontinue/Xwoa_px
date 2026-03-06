package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxqiandaninfo2table;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxqiandaninfo2tableDao extends BaseMapper<Pxqiandaninfo2table> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qianDanOtherMoneyID", property = "qianDanOtherMoneyID"),
            @Result(column = "jiaoxueYonpingID", property = "jiaoxueYonpingID"),
            @Result(column = "onePrice", property = "onePrice"),
            @Result(column = "nums", property = "nums"),
            @Result(column = "zongMoney", property = "zongMoney"),
            @Result(column = "qianInfoTabID", property = "qianInfoTabID"),
            @Result(column = "type", property = "type"),
            @Result(column = "tuiMoney", property = "tuiMoney"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandaninfo2table "
            + "</script>")
    List<Pxqiandaninfo2table> getAllList();

    //开始退费
    @Select("<script>" +
            "SELECT a.* from pxqiandaninfo2table a \n" +
            "LEFT JOIN pxqiandaninfotable b on a.qianInfoTabID=b.id " +
            "where 1=1  " +
            "<if test='ew != null'>" +
            " AND  ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxqiandaninfo2table> tuiallzf(@Param("ew") Wrapper wrapper);
}