package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.collectVo;
import com.xwcloud.cloud.model.entity.WscCollect;
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
 * @since 2021-05-26
 */
@Repository
public interface IWscCollectDao extends BaseMapper<WscCollect> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "goodsID", property = "goodsID"),
            @Result(column = "wscUserID", property = "wscUserID"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_collect"
            + "</script>")
    List<WscCollect> getAllList();

    /**
     * 获取用户收藏
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select a.id collectid,b.* \n" +
            "from wsc_collect a \n" +
            "LEFT JOIN wsc_goods b on a.goodsID=b.id\n" +
            "LEFT JOIN wsc_user c on a.wscUserID =c.id "+
            "WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<collectVo> GetwscUserCollect(Page page, @Param("ew") QueryWrapper wrapper);
}