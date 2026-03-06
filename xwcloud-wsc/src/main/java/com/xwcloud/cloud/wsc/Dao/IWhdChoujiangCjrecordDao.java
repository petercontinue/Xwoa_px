package com.xwcloud.cloud.wsc.Dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.entity.WhdChoujiangCjrecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Repository
public interface IWhdChoujiangCjrecordDao extends BaseMapper<WhdChoujiangCjrecord> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "choujiangHuodongID", property = "choujianghuodongid"),
            @Result(column = "choujiangWxUserID", property = "choujiangwxuserid"),
            @Result(column = "jiangpingID", property = "jiangpingid"),
            @Result(column = "jiangpingFafangState", property = "jiangpingfafangstate"),
            @Result(column = "jiangpingFFTime", property = "jiangpingFFTime"),
            @Result(column = "FFstaffID", property = "FFstaffID"),
            @Result(column = "choujiangTime", property = "choujiangtime"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  whd_choujiang_cjrecord"
            + "</script>")
    List<WhdChoujiangCjrecord> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.choujiangHuodongName, c.nickName AS userName, d.jiangpingName,d.jiangpingLevel,d.type as jptype," +
            "(select staffName from pxstafftable where id=a.FFstaffID) ffStaffName \n" +
            "FROM whd_choujiang_cjrecord a\n" +
            "JOIN whd_choujiang_huodong b ON a.choujiangHuodongID=b.id\n" +
            "JOIN wsc_user c ON a.choujiangWxUserID=c.id\n" +
            "JOIN whd_choujiang_jiangping d ON a.jiangpingID=d.id\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<HashMap<String, Object>> getWhdChoujiangRecordPage(Page page, @Param("ew") QueryWrapper wrapper);

}