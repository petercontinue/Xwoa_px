package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.dongtaiVO;
import com.xwcloud.cloud.model.Vo.dongtaiinfoVO;
import com.xwcloud.cloud.model.entity.WscDongtaiinfo;
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
 * @since 2021-05-18
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
            @Result(column = "dianzangTimes", property = "dianzangTimes"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_dongtaiinfo"
            + "</script>")
    List<WscDongtaiinfo> getAllList();

    /**
     * 查询动态信息(首页动态信息)
     *
     * @return
     */
        @Select("<script>" + "SELECT dongtai.id, u.nickName,u.headImage,dongtai.dongtaiContent,dongtai.Addtime,dongtai.iszhiding,dongtai.yueduTimes,u.id as stuID, \n" +
            "   (SELECT COUNT(*) FROM dongtai_dianzang AS d WHERE d.dongtaiID=dongtai.id) AS dianzanCount,\n" +
            "            (SELECT COUNT(*) FROM dongtai_pinglun AS f WHERE f.dongtaiID=dongtai.id) AS pinglunCount, \n" +
            "            (SELECT COUNT(*) FROM wsc_userguanzhu AS g WHERE g.beiguanzhuUserID = u.id AND g.wscuserID = #{wscuserID}) AS guanzhu\n" +
            "FROM wsc_dongtaiinfo AS dongtai LEFT JOIN wsc_user AS u ON dongtai.wscUserID = u.id WHERE dongtai.isShow = 0" +
            " ORDER BY dongtai.Addtime DESC" + "</script>")
    List<dongtaiVO> GetIndexDongtaifenxiang(long wscuserID);

    /**
     * 查询动态详情信息
     *
     * @param dongtaiID
     * @param wscuserID
     * @return
     */
    @Select("<script>" + "SELECT a.id,a.dongtaiTitle,a.dongtaiContent,a.addtime,u.nickName,u.headImage,a.wscuserID,a.yueduTimes,\n" +
            "(SELECT COUNT(*) FROM dongtai_dianzang AS d WHERE d.dongtaiID=a.id) AS dianzanCount,\n" +
            "(SELECT COUNT(*) FROM dongtai_pinglun AS f WHERE f.dongtaiID=a.id) AS pinglunCount, \n" +
            "(SELECT COUNT(*) FROM wsc_userguanzhu AS g WHERE g.beiguanzhuUserID = u.id AND g.wscuserID = #{wscuserID}) AS guanzhu\n" +
            "FROM wsc_dongtaiinfo AS a LEFT JOIN wsc_user AS u ON a.wscuserID = u.id WHERE a.id = #{dongtaiID}" + "</script>")
    List<dongtaiinfoVO> GetDongtaiInfo(@Param("dongtaiID")long dongtaiID, @Param("wscuserID") long wscuserID);

    /**
     * 查询当前登录用户发布的动态信息
     *
     * @param page
     * @param wscuserID
     * @return
     */
    @Select("<script>" + "SELECT dongtai.id, u.nickName,u.headImage,dongtai.dongtaiContent,dongtai.Addtime,dongtai.iszhiding,dongtai.yueduTimes, \n" +
            "              (SELECT COUNT(*) FROM dongtai_dianzang AS d WHERE d.dongtaiID=dongtai.id) AS dianzanCount,\n" +
            "                        (SELECT COUNT(*) FROM dongtai_pinglun AS f WHERE f.dongtaiID=dongtai.id) AS pinglunCount\n" +
            "            FROM wsc_dongtaiinfo AS dongtai LEFT JOIN wsc_user AS u ON dongtai.wscUserID = u.id WHERE u.id = #{wscuserID}" + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "</script>")
    Page<dongtaiVO> GetMyDongtaiInfo(Page page, long wscuserID,@Param("ew") Wrapper wrapper);
}