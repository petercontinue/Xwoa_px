package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.WhdChoujiangJiangpinVo;
import com.xwcloud.cloud.model.Vo.allgailvVo;
import com.xwcloud.cloud.model.entity.WhdChoujiangJiangping;
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
public interface IWhdChoujiangJiangpingDao extends BaseMapper<WhdChoujiangJiangping> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "jiangpingLevel", property = "jiangpinglevel"),
                @Result(column = "jiangpingName", property = "jiangpingname"),
                @Result(column = "jiangpingImg", property = "jiangpingimg"),
                @Result(column = "choujiangHuodongID", property = "choujianghuodongid"),
                @Result(column = "zhongjiangGailv", property = "zhongjianggailv"),
                @Result(column = "jiangpingTotalNum", property = "jiangpingtotalnum"),
                @Result(column = "type", property = "type"),
                @Result(column = "qiyeID", property = "qiyeid"),
                @Result(column = "addUser", property = "adduser"),
                @Result(column = "addTime", property = "addtime"),
    })
    @Select("<script>" +
            "SELECT * from  whd_choujiang_jiangping"
            + "</script>")
    List<WhdChoujiangJiangping> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.choujiangHuodongName\n" +
            "FROM whd_choujiang_jiangping a\n" +
            "JOIN whd_choujiang_huodong b ON a.choujiangHuodongID=b.id\n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<WhdChoujiangJiangpinVo> getWhdChoujiangJiangpinPage(Page page, @Param("ew") QueryWrapper wrapper);

    @Select("<script>" +
            "SELECT SUM(zhongjiangGailv) alllv from whd_choujiang_jiangping where choujiangHuodongID=#{huodongID} and qiyeID=#{qiyeID}"
            + "</script>")
    allgailvVo getallgailv(Long huodongID,Long qiyeID);


}