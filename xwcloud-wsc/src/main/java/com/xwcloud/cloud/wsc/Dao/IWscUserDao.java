package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.fensiguanzhuVO;
import com.xwcloud.cloud.model.Vo.fenxiaoinfoVO;
import com.xwcloud.cloud.model.Vo.yongjinVO;
import com.xwcloud.cloud.model.entity.WscUser;
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
public interface IWscUserDao extends BaseMapper<WscUser> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "unionid", property = "unionid"),
            @Result(column = "headImage", property = "headimage"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "diqu", property = "diqu"),
            @Result(column = "addr", property = "addr"),
            @Result(column = "userType", property = "usertype"),
            @Result(column = "addTime", property = "addtime"),
            @Result(column = "isdongjie", property = "isdongjie"),
            @Result(column = "isKcUser", property = "iskcuser"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "userName", property = "username"),
            @Result(column = "userTel", property = "usertel"),
            @Result(column = "fid", property = "fid"),
            @Result(column = "gfid", property = "gfid"),
            @Result(column = "scRemainMoney", property = "scremainmoney"),
            @Result(column = "scJifen", property = "scjifen"),
            @Result(column = "scRemainyongjin", property = "scremainyongjin"),
            @Result(column = "scWeijieYongjin", property = "scweijieyongjin"),
            @Result(column = "scYijieYongjin", property = "scyijieyongjin"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_user"
            + "</script>")
    List<WscUser> getAllList();

    /**
     * 分页插叙微商城用户信息
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.*,(CASE a.fid\n" +
            "            WHEN NULL THEN '无'\n" +
            "            ELSE (SELECT nickName FROM wsc_user WHERE id = a.fid) END ) AS fidName ,\n" +
            "(CASE a.gfid\n" +
            "            WHEN NULL THEN '无'\n" +
            "            ELSE (SELECT nickName FROM wsc_user WHERE id = a.gfid) END ) AS gfidName ,\n" +
            "\t\t\t\t\t\t(CASE a.isKcUser\n" +
            "\tWHEN a.isKcUser='0' THEN '-'\n" +
            "\tELSE(SELECT stuName FROM pxstutable WHERE id = a.isKcUser)END) AS stuName\n" +
            "FROM wsc_user AS a" + "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<HashMap<String, Object>> GetWscUserPages(Page page, @Param("ew") QueryWrapper wrapper);


    /**
     * 查询当前登录用户的分销信息
     *
     * @param userID
     * @return
     */
    @Select("<script>" + "SELECT\n" +
            "\tu.id,\n" +
            "\tu.nickName,\n" +
            "\tu.headImage,\n" +
            "\tu.scRemainyongjin,\n" +
            "\tu.scWeijieYongjin,\n" +
            "\tu.scYijieYongjin,\n" +
            "\tu.scRemainMoney,\n" +
            "\t(SELECT COUNT(*) FROM wsc_user AS d WHERE d.fid=u.id OR d.gfid=u.id)AS alltuanduiCount,\n" +
            "\t(SELECT COUNT(*) FROM wsc_user AS a WHERE a.fid=u.id) AS zhishuCount,\n" +
            "\t(SELECT COUNT(*) FROM wsc_user AS b WHERE b.gfid = u.id) AS feizhishuCount\n" +
            "FROM\n" +
            "\twsc_user AS u \n" +
            "WHERE\n" +
            "\tu.id = #{userID} LIMIT 1 " + "</script>")
    fenxiaoinfoVO getloginuserFenxiaoInfo(long userID);

    /**
     * 分页查询直属推荐用户信息
     *
     * @param page
     * @param wscuserID
     * @return
     */
    @Select("<script>" + "\tSELECT a.*,a.addTime as guanzhuDatetine from  wsc_user AS a WHERE a.fid = #{wscuserID}" + "</script>")
    Page<fensiguanzhuVO> GetzhishuUserPages(Page page, long wscuserID);

    /**
     * 分页查询非直属成员信息
     *
     * @param page
     * @param wscuserID
     * @return
     */
    @Select("<script>" + "select b.*,b.addTime as guanzhuDatetine from wsc_user AS b WHERE b.gfid = #{wscuserID}" + "</script>")
    Page<fensiguanzhuVO> GetjianjietuijianPages(Page page, long wscuserID);

    /**
     * 分页查询已完成的佣金信息
     *
     * @param page
     * @param wscuserID
     * @return
     */
    @Select("<script>" + "select \ta.fidMoney ,o.payDateTime,u.nickName,o.payMoney FROM wsc_yongjin AS a LEFT JOIN wsc_order AS o ON a.orderNumber = o.orderNumber LEFT JOIN wsc_user AS u ON o.orderUserID = u.id\n" +
            "WHERE a.fid=#{wscuserID} OR a.topfid=#{wscuserID}" + "</script>")
    Page<yongjinVO> GetyiwanchengYongjinInfo(Page page, long wscuserID);

    /**
     * 分页查询未完成的分润信息
     *
     * @param page
     * @param wscuserID
     * @return
     */
    @Select("" + "SELECT * FROM wsc_order AS o LEFT JOIN wsc_user AS u ON o.orderUserID = u.id" +
            " WHERE (u.fid = #{wscuserID}OR u.gfid = #{wscuserID} )AND o.orderState in(2,3)\t" + "")
    Page<yongjinVO> GetWeiwanchengYongjinInfoPages(Page page, long wscuserID);

}