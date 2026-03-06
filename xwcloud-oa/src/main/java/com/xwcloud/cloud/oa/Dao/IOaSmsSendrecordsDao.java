package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.OA.OaSmsSendrecords;
import com.xwcloud.cloud.model.OA.Vo.SmsSendrecordsVo;
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
public interface IOaSmsSendrecordsDao extends BaseMapper<OaSmsSendrecords> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "smscontent", property = "smscontent"),
            @Result(column = "sendTime", property = "sendTime"),
            @Result(column = "smsPhone", property = "smsPhone"),
            @Result(column = "shuoming", property = "shuoming"),
    })
    @Select("<script>" +
            "SELECT * from  oa_sms_sendrecords"
            + "</script>")
    List<OaSmsSendrecords> getAllList();

    @Results(id = "SmsBuyrecordsInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "smscontent", column = "smscontent"),
            @Result(property = "smsPhone", column = "smsPhone"),
            @Result(property = "shuoming", column = "shuoming"),
            @Result(property = "sendTime", column = "sendTime")
    })
    @Select("<script>" +
            "SELECT sendrecords.id,\n" +
            "sendrecords.smscontent,\n" +
            "sendrecords.smsPhone,\n" +
            "sendrecords.shuoming,\n" +
            "sendrecords.sendTime,\n" +
            "kehu.kehucompanyname\n" +
            "\n" +
            "from oa_sms_sendrecords sendrecords\n" +
            "LEFT JOIN oa_kehu kehu on sendrecords.qiyeID=kehu.id" + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<SmsSendrecordsVo> getAllSmsSendecordsVoInfo(Page<SmsSendrecordsVo> page, @Param("ew") Wrapper wrapper);

}