package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.entity.WhdChongzhiRecord;
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
public interface IWhdChongzhiRecordDao extends BaseMapper<WhdChongzhiRecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "wscUserID", property = "wscuserid"),
                @Result(column = "czHuodongID", property = "czhuodongid"),
                @Result(column = "chongzhiMoney", property = "chongzhimoney"),
                @Result(column = "songMoney", property = "songmoney"),
                @Result(column = "shideMoney", property = "shidemoney"),
                @Result(column = "chongzhiShuoming", property = "chongzhishuoming"),
                @Result(column = "chongzhiTime", property = "chongzhitime"),
                @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  whd_chongzhi_record"
            + "</script>")
    List<WhdChongzhiRecord> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.userName\n" +
            "FROM whd_chongzhi_record a\n" +
            "JOIN wsc_user b ON a.wscUserID=b.id\n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page getWhdChongzhiRecordPage(Page page, @Param("ew") QueryWrapper wrapper);

}