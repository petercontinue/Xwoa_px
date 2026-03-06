package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.PxtuifeitableVo;
import com.xwcloud.cloud.model.Vo.alltuizfVo;
import com.xwcloud.cloud.model.entity.Pxtuifeitable;
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
 * @since 2020-11-16
 */
@Repository
public interface IPxtuifeitableDao extends BaseMapper<Pxtuifeitable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "tuifeispID", property = "tuifeispID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "tuifeiType", property = "tuifeiType"),
            @Result(column = "beforeTuifeiRemainXuefei", property = "beforeTuifeiRemainXuefei"),
            @Result(column = "shoudTuifeiTotalMoney", property = "shoudTuifeiTotalMoney"),
            @Result(column = "shijiTuifeiTotalMoney", property = "shijiTuifeiTotalMoney"),
            @Result(column = "afterTuifeiRemainXuefei", property = "afterTuifeiRemainXuefei"),
            @Result(column = "beforeTuifeiJifen", property = "beforeTuifeiJifen"),
            @Result(column = "tuijifen", property = "tuijifen"),
            @Result(column = "afterTuifeiJifen", property = "afterTuifeiJifen"),
            @Result(column = "beforeTFchongzhiRemainMoney", property = "beforeTFchongzhiRemainMoney"),
            @Result(column = "tuiChongzhiMoney", property = "tuiChongzhiMoney"),
            @Result(column = "afterTFchongzhiRemainMoney", property = "afterTFchongzhiRemainMoney"),
            @Result(column = "tuifeiPayStyleID", property = "tuifeiPayStyleID"),
            @Result(column = "liushuiID", property = "liushuiID"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtuifeitable"
            + "</script>")
    List<Pxtuifeitable> getAllList();

    @Select("<script>" +
            "select a.id liushuiID,a.liushuiDateTime liushuiDateTime ,a.stuID stuID ,b.zidingyiStuID zidingyiStuID,b.stuName stuName,c.campusName campusName,\n" +
            "f.moneystyleName,a.payMoneyStyle,a.zhichuMoney zhichuMoney,e.staffName jinbanren ,a.jinbanRen jinbanRenID,a.campusID campusID ,\n" +
            "(SELECT staffName from pxstafftable where id = b.banzhurenTeacherID) banzhuren  \n" +
            "from pxliushuizhangtable a \n" +
            "left JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable c on a.campusID=c.id\n" +
            "LEFT JOIN pxshouzhistyletable d on a.shouzhiStyleID=d.id\n" +
            "LEFT JOIN pxstafftable e on a.jinbanRen=e.id\n" +
            "LEFT JOIN pxpaymoneystyletable f on a.payMoneyStyle=f.id\n" +
            "where a.shouzhiStyleID=6 and c.isOpen !=2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxtuifeitableVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "select a.id liushuiID,a.liushuiDateTime liushuiDateTime ," +
            "( CASE WHEN b.zidingyiStuID is null then a.stuID ELSE b.zidingyiStuID END ) stuID ," +
            "b.stuName stuName,c.campusName campusName,\n" +
            "f.moneystyleName,a.payMoneyStyle,a.zhichuMoney zhichuMoney,e.staffName jinbanren ,a.jinbanRen jinbanRenID,a.campusID campusID ,\n" +
            "(SELECT staffName from pxstafftable where id = b.banzhurenTeacherID) banzhuren  \n" +
            "from pxliushuizhangtable a \n" +
            "left JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable c on a.campusID=c.id\n" +
            "LEFT JOIN pxshouzhistyletable d on a.shouzhiStyleID=d.id\n" +
            "LEFT JOIN pxstafftable e on a.jinbanRen=e.id\n" +
            "LEFT JOIN pxpaymoneystyletable f on a.payMoneyStyle=f.id\n" +
            "where a.shouzhiStyleID=6 and c.isOpen !=2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxtuifeitableVo> getJoinList(@Param("ew") Wrapper wrapper);

    // 课程缴纳费用
    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(qdsp.zongjia) is not null THEN SUM(qdsp.zongjia) ELSE 0 END )  " +
            "from  pxqiandansubjecttable qdsp " +
            "LEFT JOIN pxqiandaninfotable qdinfo ON qdsp.qianDanInfoID=qdinfo.id " +
            "LEFT JOIN pxqiandanstafftable staff ON staff.qiandanID = qdinfo.id " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    String getSumKechengFee(@Param("ew") Wrapper wrapper);

    // 课程消耗费用
    @Select("<script>" +
//            "SELECT stu.zidingyiStuID as xuehao,stu.stuName as stuName,grade.stuGradeName as gradeName," +
//            " buxifs.buxiStyleName as buxi,keshi.*,campus.campusName as campusName FROM pxkeshistutable keshi " +
//            " LEFT JOIN pxstutable stu ON keshi.stuID = stu.id " +
//            " LEFT JOIN pxstugradetable grade ON stu.stuGradeID = grade.id " +
//            " LEFT JOIN pxkechengtable kecheng ON kecheng.id = keshi.kechengID " +
//            " LEFT JOIN pxbuxistyletable buxifs ON kecheng.buxiStyleID = buxifs.id " +
//            " LEFT JOIN pxcampustable campus ON stu.campusID = campus.id " +
//            " LEFT JOIN pxqiandansubjecttable qdkc ON qdkc.kechengID = kecheng.id " +
            "SELECT\n" +
            " ( CASE WHEN sum(keshi.keshiNum*keshi.kechengPrice) is not null THEN sum(keshi.keshiNum*keshi.kechengPrice) ELSE 0 END ) \n" +
            "FROM\n" +
            "\tpxkeshistutable keshi\n" +
            "\tLEFT JOIN pxstutable stu ON keshi.stuID = stu.id\n" +
            "\tLEFT JOIN pxstugradetable grade ON stu.stuGradeID = grade.id\n" +
            "\tLEFT JOIN pxkechengtable kecheng ON kecheng.id = keshi.kechengID\n" +
            "\tLEFT JOIN pxbuxistyletable buxifs ON kecheng.buxiStyleID = buxifs.id\n" +
            "\tLEFT JOIN pxcampustable campus ON stu.campusID = campus.id\n" +
            "\tLEFT JOIN pxqiandansubjecttable qdkc ON qdkc.kechengID = kecheng.id" +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    String getKeshiXiaohao(@Param("ew") Wrapper wrapper);

    // 接受他人赠送课时
    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(qdsp.shouXueFei) is not null THEN SUM(qdsp.shouXueFei) ELSE 0 END ) " +
            "from  pxkeshizhuansongtable qdsp " +
            "LEFT JOIN pxqiandaninfotable qdinfo ON qdsp.id=qdinfo.zhuansongID " +
            "LEFT JOIN pxqiandanstafftable staff ON staff.qiandanID = qdinfo.id " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    String getJieshouKeshiSum(@Param("ew") Wrapper wrapper);

    // 转送给他人的课时
    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(qdsp.songXueFei) is not null THEN SUM(qdsp.songXueFei) ELSE 0 END ) " +
            "from  pxkeshizhuansongtable qdsp " +
            "LEFT JOIN pxqiandaninfotable qdinfo ON qdsp.id=qdinfo.zhuansongID " +
            "LEFT JOIN pxqiandanstafftable staff ON staff.qiandanID = qdinfo.id " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    String getZhuansongKeshiSum(@Param("ew") Wrapper wrapper);

    // 充值缴费
    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(qdsp.shijiChongzhiMoney) is not null THEN SUM(qdsp.shijiChongzhiMoney) ELSE 0 END ) " +
            "from  pxchongzhitable qdsp" +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    String getJiaofei(@Param("ew") Wrapper wrapper);

    // 充值赠送
    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(qdsp.songMoney) is not null THEN SUM(qdsp.songMoney) ELSE 0 END )" +
            " from  pxchongzhitable qdsp " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    String getZengsong(@Param("ew") Wrapper wrapper);

    // 充值金额消耗
    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(qdsp.chongzhiPayMoney) is not null THEN SUM(qdsp.chongzhiPayMoney) ELSE 0 END ) " +
            "from  pxchongzhipaytable qdsp " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    String getXiaohaoFee(@Param("ew") Wrapper wrapper);

    // 充值余额
    @Select("<script>" +
            "SELECT remainChongzhi from  pxstutable " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    String getYuFee(@Param("ew") Wrapper wrapper);

    // 商品缴费
    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(qdsp.SumMoney) is not null THEN SUM(qdsp.SumMoney) ELSE 0 END ) " +
            " FROM pxqiandansupplies qdsp " +
            "LEFT JOIN pxqiandaninfotable qdinfo ON qdsp.QiandaninfoID=qdinfo.id " +
            "LEFT JOIN pxqiandanstafftable staff ON staff.qiandanID = qdinfo.id " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    String getCommodityFee(@Param("ew") Wrapper wrapper);

    // 杂费缴费
    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(qdsp.zongMoney) is not null THEN SUM(qdsp.zongMoney) ELSE 0 END )  FROM pxqiandaninfo2table qdsp " +
            "LEFT JOIN pxqiandaninfotable qdinfo ON qdsp.qianInfoTabID=qdinfo.id " +
            "LEFT JOIN pxqiandanstafftable staff ON staff.qiandanID = qdinfo.id " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    String getOtherFee(@Param("ew") Wrapper wrapper);

    // 代金券
    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(qdsp.money) is not null THEN SUM(qdsp.money) ELSE 0 END )  FROM pxdaijinquantable qdsp " +
            "LEFT JOIN pxqiandaninfotable qdinfo ON qdsp.qiandanID=qdinfo.id " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    String getDaijinquan(@Param("ew") Wrapper wrapper);

    // 优惠金额
    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(b.youhui) is not null THEN SUM(b.youhui) ELSE 0 END) \n" +
            "from pxqiandaninfotable qdinfo  \n" +
            "LEFT JOIN pxyouhuizhengcetable b on qdinfo.youhuiID=b.id \n" +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    String getYouhuiFee(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * from pxbuxikechengtable a \n" +
            "LEFT JOIN pxkechengtable b on a.kechengID=b.id\n" +
            "left join pxqiandaninfotable c on a.qianDanInfoID=c.id "+
            "where 1=1 " +
            "<if test='ew != null'>" +
            " and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getKechengfeiDetail(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM " +
            "(SELECT chongzhi.shijiChongzhiMoney as money ,chongzhi.shideTotalMoney as zongMoney, chongzhi.stuID," +
            "songMoney,chongzhi.shuoming as beizhu, chongzhi.chongzhiDatetime, '1' as chongxiao, " +
            "staff.staffName as yejiStaffName FROM pxchongzhitable chongzhi " +
            "LEFT JOIN pxstafftable staff ON staff.id = chongzhi.yejiStaffID " +
            "WHERE chongzhi.qiyeID = #{qiyeID}" +
            "UNION ALL " +
            "SELECT chongzhiPayMoney as money, chongzhiPayMoney as zongMoney, stuID, '0' as songMoney,beizhu," +
            " addTime as chongzhiDatetime,'1' as chongxiao,'' AS yejiStaffName FROM pxchongzhipaytable" +
            "WHERE qiyeID = #{qiyeID}" +
            ") as tmp" +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getChongzhiDetail(@Param("ew") Wrapper wrapper, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT a.* from pxqiandansupplies a \n" +
            "LEFT JOIN pxteachsubjecttable b on a.TeachingSuppliesID =b.id\n" +
            "LEFT JOIN pxqiandaninfotable c on a.QiandaninfoID =c.id\n" +
            "where (a.TuiMoney is not null and a.TuiMoney &lt; a.SumMoney or a.TuiMoney is null ) " +
            "<if test='ew != null'>" +
            "AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getShangpinDetail(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT zf.*,qdinfo.qiandandate,c.otherMoneyName  FROM pxqiandaninfo2table zf \n" +
            "LEFT JOIN pxqiandaninfotable qdinfo ON zf.qianInfoTabID = qdinfo.id \n" +
            "LEFT JOIN pxqiandanothermoneytable c on zf.qianDanOtherMoneyID=c.id " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            "AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getZafeiDetail(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxkeshistutable keshi LEFT JOIN pxstutable stu ON keshi.stuID = stu.id " +
            " LEFT JOIN pxstugradetable grade ON stu.stuGradeID = grade.id " +
            " LEFT JOIN pxkechengtable kecheng ON kecheng.id = keshi.kechengID  " +
            " LEFT JOIN pxbuxistyletable buxifs ON kecheng.buxiStyleID = buxifs.id " +
            " LEFT JOIN pxcampustable campus ON stu.campusID = campus.id" +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getKeshiDetail(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT  " +
            " campus.campusName," + //  -- 赠送人校区名称
            " stu.zidingyiStuID,  " + //-- 赠送人学号
            " stu.stuName,  " + //-- 赠送人名称
            " kecheng.kechengName,  " + // -- 赠送课程
            " zhuansong.songkechengPrice,  " +// -- 送出课程单价
            " zhuansong.songKeshiNum,  " +// -- 送出课时
            " zhuansong.songXueFei, " +//  -- 送出的学费
            " SUM(buxikecheng.remainkeshi) as remainkeshi," + //-- 剩余课时
            " SUM(buxikecheng.remainkeshi)*buxikecheng.kechengprice," +// -- 剩余学费
            " jsstu.stuName as jsstuName," +// -- 接收人
            " jsstu.zidingyiStuID AS jszidingyiStuID," +// -- 接收人学号
            " jscampus.campusName AS jscampusName," +// -- 接收人校区
            " jskecheng.kechengName AS jskechengName," +// -- 接收课程
            " zhuansong.shouKeshi," +// -- 接收课时
            " zhuansong.shouXueFei," +// -- 接收学费
            " SUM(jsbuxikecheng.remainkeshi) as jsremainkeshi," +// -- 剩余课时
            " SUM(jsbuxikecheng.remainkeshi)*jsbuxikecheng.kechengprice AS jsremainXuefei," +//  -- 剩余学费
            " zhuansong.zhuansongDate," +// -- 转送时间
            " staff.staffName " +// -- 操作人
            "FROM pxkeshizhuansongtable zhuansong \n" +
            "LEFT JOIN pxstutable stu ON zhuansong.songstuID = stu.id  \n" +
            "LEFT JOIN pxcampustable campus ON stu.campusID = campus.id  \n" +
            "LEFT JOIN pxkechengtable kecheng ON zhuansong.songkechengID = kecheng.id \n" +
            "LEFT JOIN pxbuxikechengtable buxikecheng ON buxikecheng.kechengID = kecheng.id AND buxikecheng.stuID = stu.id\n" +
            "LEFT JOIN pxstutable jsstu ON jsstu.id = zhuansong.shoustuID\n" +
            "LEFT JOIN pxcampustable jscampus ON jscampus.id=jsstu.campusID\n" +
            "LEFT JOIN pxkechengtable jskecheng ON zhuansong.shoukechengID = jskecheng.id\n" +
            "LEFT JOIN pxbuxikechengtable jsbuxikecheng ON jsbuxikecheng.kechengID = jskecheng.id AND jsbuxikecheng.stuID = jsstu.id\n" +
            "LEFT JOIN pxstafftable staff ON zhuansong.adduser=staff.id " +
            "LEFT JOIN pxqiandaninfotable qdinfo ON qdinfo.zhuansongID = zhuansong.id " +
            "<if test='ew != null'>" +
            " where 1=1 AND " +
            " ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getTransferDetail(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT \n" +
            "\tbuxikecheng.id as buxiID,\n" +
            "\tbuxikecheng.kechengID as kechengID,\n" +
            "\tbuxikecheng.kechengprice,\n" +
            "\tSUM(buxikecheng.remainkeshi) AS remainkeshi,\n" +
            "\tSUM(buxikecheng.remainkeshi)*buxikecheng.kechengprice AS syxuefei,\n" +
            "\tkecheng.kechengName\n" +
            "FROM pxbuxikechengtable buxikecheng \n" +
            "LEFT JOIN pxkechengtable kecheng ON buxikecheng.kechengID=kecheng.id\n" +
            "GROUP BY buxikecheng.kechengID" +
            "<if test='ew != null'>" +
            " where 1=1 AND " +
            " ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getKechengList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM pxqiandansupplies " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getCommodityList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT zafei.* FROM pxqiandaninfo2table zafei " +
            "LEFT JOIN pxqiandaninfotable qdinfo ON zafei.qianInfoTabID = qdinfo.id " +
            "where 1=1" +
            "<if test='ew != null'>" +
            " AND  ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getZafeiList(@Param("ew") Wrapper wrapper);

    //检测是否有可退杂费项
    @Select("<script>" +
            "SELECT a.id id ,c.otherMoneyName,a.zongMoney zongMoney,a.tuiMoney tuiMoney,a.qianInfoTabID qianInfoTabID from pxqiandaninfo2table a \n" +
            "LEFT JOIN pxqiandaninfotable b on a.qianInfoTabID=b.id\n" +
            "LEFT JOIN pxqiandanothermoneytable c on a.qianDanOtherMoneyID=c.id\n" +
            "where a.zongMoney !=0  " +
            "<if test='ew != null'>" +
            " AND  ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<alltuizfVo> getalltuizf(@Param("ew") Wrapper wrapper);


}