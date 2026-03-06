package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.OA.OaSmsBuyrecords;
import com.xwcloud.cloud.model.OA.Vo.SmsBuyrecordsVo;
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
 * @since 2021-07-03
 */
@Repository
public interface IOaSmsBuyrecordsDao extends BaseMapper<OaSmsBuyrecords> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "buySum", property = "buySum"),
            @Result(column = "price", property = "price"),
            @Result(column = "sumMoney", property = "sumMoney"),
            @Result(column = "buyTime", property = "buyTime"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addUser", property = "addUser"),
    })
    @Select("<script>" +
            "SELECT * from  oa_sms_buyrecords"
            + "</script>")
    List<OaSmsBuyrecords> getAllList();

    @Results(id = "smsBuyrecordsInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "buySum", column = "buySum"),
            @Result(property = "price", column = "price"),
            @Result(property = "sumMoney", column = "sumMoney"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "shuoming", column = "shuoming"),
            @Result(property = "buyTime", column = "buyTime"),
            @Result(property = "addTime", column = "addTime")
    })
    @Select("<script>" + "SELECT \n" +
            "buyrecords.id,\n" +
            "buyrecords.buySum,\n" +
            "buyrecords.price,\n" +
            "buyrecords.sumMoney,\n" +
            "staff.staffName,\n" +
            "buyrecords.buyTime,\n" +
            "buyrecords.addTime,\n" +
            "buyrecords.shuoming,\n" +
            "kehu.kehucompanyname\n" +
            "from oa_sms_buyrecords buyrecords\n" +
            "LEFT JOIN oa_kehu kehu on buyrecords.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff staff on buyrecords.addUser=staff.id" + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<SmsBuyrecordsVo> getAllSmsBuyrecordsInfo(Page<SmsBuyrecordsVo> page, @Param("ew") Wrapper wrapper);
}