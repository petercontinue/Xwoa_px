package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.WhdChongzhiPayrecordVo;
import com.xwcloud.cloud.model.entity.WhdChongzhiPayrecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Repository
public interface IWhdChongzhiPayrecordDao extends BaseMapper<WhdChongzhiPayrecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "wxUserID", property = "wxuserid"),
                @Result(column = "payMoney", property = "paymoney"),
                @Result(column = "shuoming", property = "shuoming"),
                @Result(column = "payTime", property = "paytime"),
                @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  whd_chongzhi_payrecord"
            + "</script>")
    List<WhdChongzhiPayrecord> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.userName\n" +
            "FROM whd_chongzhi_payrecord a\n" +
            "JOIN wsc_user b ON a.wxUserID=b.id\n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<WhdChongzhiPayrecordVo> getWhdChongzhiPayRecordPage(Page Page, @Param("ew") QueryWrapper wrapper);

}