package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.WscYongjin;
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
 * @since 2021-02-22
 */
@Repository
public interface IWscYongjinDao extends BaseMapper<WscYongjin> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "orderNumber", property = "orderNumber"),
            @Result(column = "money", property = "money"),
            @Result(column = "fid", property = "fid"),
            @Result(column = "fidMoney", property = "fidMoney"),
            @Result(column = "fidJiesuanDateTime", property = "fidJiesuanDateTime"),
            @Result(column = "topfid", property = "topfid"),
            @Result(column = "topfidMoney", property = "topfidMoney"),
            @Result(column = "topfidJiesuanDateTime", property = "topfidJiesuanDateTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wscyongjin"
            + "</script>")
    List<WscYongjin> getAllList();

    /**
     * 分页查询三级返佣信息
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
//            "SELECT * FROM (SELECT\n" +
//            "\ta.id,\n" +
//            "\ta.orderNumber,\n" +
//            "\ta.money,\n" +
//            "\ta.topfid,\n" +
//            "\ta.fidMoney,\n"+
//            "\ta.topfidMoney,\n" +
//            "\ta.topfidJiesuanDateTime,\n" +
//            "\tb.nickName,\n" +
//            "\t2 AS type,\n" +
//            "\ta.qiyeID\n" +
//            "FROM\n" +
//            "\twsc_yongjin AS a\n" +
//            "\tLEFT JOIN wsc_user AS b ON a.fid = b.id\n" +
//            "union all \n" +
//            "SELECT \t\n" +
//            "\tc.id,\n" +
//            "\tc.orderNumber,\n" +
//            "\tc.money,\n" +
//            "\tc.topfid,\n" +
//            "\tc.fidMoney,\n"+
//            "\tc.topfidMoney,\n" +
//            "\tc.topfidJiesuanDateTime,\n" +
//            "\td.nickName,\n" +
//            "\t1 AS type,c.qiyeID  FROM wsc_yongjin AS c " +
//            "LEFT JOIN wsc_user AS d ON c.topfid = d.id) AS list " +
            "SELECT a.*,\n" +
            "(select id from pxstafftable where staffTel=a.phoneNumber and qiyeID=a.qiyeID) staffID,"+
            "(SELECT nickName from wsc_user b where b.id=a.fid and b.qiyeID=a.qiyeID ) fnickName,\n" +
            "(SELECT nickName from wsc_user c where c.id=a.gfid and c.qiyeID=a.qiyeID ) znickName,\n" +
            "(SELECT d.buyTime from  wsc_tuike_buy d where d.wsc_user_id=a.id and d.qiyeID=a.qiyeID  ORDER BY d.buyTime DESC LIMIT 1 ) buyTime,\n" +
            "(SELECT f.tuikeLevelName from  wsc_tuike_buy e JOIN wsc_tuikeLevel f on e.buyTuikeLevelID=f.id where e.wsc_user_id=a.id and e.qiyeID=a.qiyeID   ORDER BY e.buyTime DESC LIMIT 1 )  tuikeLevelName," +
            "(SELECT COUNT(id) from wsc_user where fid=a.id or gfid=a.id) teamnum\n" +
            "from wsc_user a \n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<HashMap<String, Object>> getfangyongShowPages(Page page, @Param("ew") QueryWrapper wrapper);


    @Select("<script>"+
            "SELECT a.*,b.nickName,b.scRemainMoney,b.scWeijieYongjin,b.scYijieYongjin,b.headImage,\n" +
            "(SELECT tuikeLevelName from wsc_tuikeLevel  where id=a.oldTuikeLevelID  and qiyeID=a.qiyeID) oldtuikelvName,\n" +
            "(SELECT tuikeLevelName from wsc_tuikeLevel  where id=a.buyTuikeLevelID  and qiyeID=a.qiyeID) tuikelvName\n" +
            "from wsc_tuike_buy a \n" +
            "join wsc_user b on a.wsc_user_id =b.id"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<HashMap<String,Object>> getbuytuikePages(Page page ,@Param("ew") QueryWrapper queryWrapper);


    @Select("<script>"+
            "select a.id,b.nickName,a.realName, a.buyTime,b.phoneNumber,\n" +
            "(SELECT COUNT(id) from wsc_user where qiyeID=a.qiyeID and fid=a.wsc_user_id) fnum,\n" +
            "(SELECT COUNT(id) from wsc_user where qiyeID=a.qiyeID and gfid=a.wsc_user_id) gjnum\n" +
            "from wsc_tuike_buy   a\n" +
            "join wsc_user b on a.wsc_user_id=b.id\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            " GROUP BY a.wsc_user_id ORDER BY a.buyTime DESC "+
            "</script>")
    Page<HashMap<String,Object>> gettkteamPage(Page page ,@Param("ew") QueryWrapper queryWrapper);



    @Select("<script>"+
            "select a.id,a.realName,b.nickName,b.addTime, b.scYijieYongjin,\n" +
            "(SELECT COUNT(id) from wsc_user where fid=a.wsc_user_id or gfid=a.wsc_user_id) teamnum,\n" +
            "(@rank:=@rank+1) 'rank' \n" +
            "FROM wsc_tuike_buy a \n" +
            "LEFT JOIN wsc_user b on a.wsc_user_id=b.id\n" +
            ",(SELECT @rank:=0) q\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            " GROUP BY a.wsc_user_id\n" +
            " ORDER BY b.scYijieYongjin DESC, a.buyTime DESC " +
            "</script>")
    Page<HashMap<String,Object>> getfanyongpaiming(Page page ,@Param("ew") QueryWrapper queryWrapper);
}