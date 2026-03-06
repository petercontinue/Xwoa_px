package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.WscDongtaiinfo;
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
 * @since 2021-08-06
 */
@Repository
public interface IWscDongtaiinfoDao extends BaseMapper<WscDongtaiinfo> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "wscuserID", property = "wscuserID"),
            @Result(column = "dongtaiTitle", property = "dongtaiTitle"),
            @Result(column = "dongtaiContent", property = "dongtaiContent"),
            @Result(column = "Addtime", property = "addtime"),
            @Result(column = "iszhiding", property = "iszhiding"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "yueduTimes", property = "yueduTimes"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_dongtaiinfo"
            + "</script>")
    List<WscDongtaiinfo> getAllList();

    /**
     * 分页查询动态信息
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT dongtai.*, u.nickName,u.headImage,\n" +
            "(SELECT COUNT(*) FROM dongtai_dianzang AS d WHERE d.dongtaiID=dongtai.id) AS dianzanCount,\n" +
            "(SELECT COUNT(*) FROM dongtai_pinglun AS f WHERE f.dongtaiID=dongtai.id) AS pinglunCount\n" +
            "FROM wsc_dongtaiinfo AS dongtai LEFT JOIN wsc_user AS u ON dongtai.wscUserID = u.id WHERE dongtai.isShow = 0" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getAllWscUserDongtaiPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT * FROM dongtai_dianzang AS a  JOIN wsc_user AS b ON a.dianzanUserID = b.id"+ "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String,String>> GetPagesDianzanInfo(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT * FROM dongtai_pinglun AS a JOIN wsc_user AS b ON a.pluserID = b.id "+"<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String,String>> GetPagesPinglunInfos(Page page,@Param("ew")Wrapper wrapper);

}