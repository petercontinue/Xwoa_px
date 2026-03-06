package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.OA.Vo.KehuSmsVo;
import com.xwcloud.cloud.model.OA.Vo.KehuVo;
import com.xwcloud.cloud.model.OA.Vo.YiqiandanKehuVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Repository
public interface IOaKehuDao extends BaseMapper<OaKehu> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "areaid", property = "areaid"),
            @Result(column = "areaname", property = "areaname"),
            @Result(column = "cityid", property = "cityid"),
            @Result(column = "provinceid", property = "provinceid"),
            @Result(column = "aftersalestaffID", property = "aftersalestaffID"),
            @Result(column = "campusnum", property = "campusnum"),
            @Result(column = "email", property = "email"),
            @Result(column = "firstqiandandatetime", property = "firstqiandandatetime"),
            @Result(column = "hetong", property = "hetong"),
            @Result(column = "djqRemain", property = "djqRemain"),
            @Result(column = "smsRemain", property = "smsRemain"),
            @Result(column = "kehucompanyname", property = "kehucompanyname"),
            @Result(column = "kehucontractname", property = "kehucontractname"),
            @Result(column = "maxStuNum", property = "maxStuNum"),
            @Result(column = "khShowJigouName", property = "khShowJigouName"),
            @Result(column = "kehuinfobeizhu", property = "kehuinfobeizhu"),
            @Result(column = "kehuothertel", property = "kehuothertel"),
            @Result(column = "kehuType", property = "kehuType"),
            @Result(column = "kehuUseState", property = "kehuUseState"),
            @Result(column = "taocanID", property = "taocanID"),
            @Result(column = "kehutelphone", property = "kehutelphone"),
            @Result(column = "manyiduID", property = "manyiduID"),
            @Result(column = "nextpaydatetime", property = "nextpaydatetime"),
            @Result(column = "pagediscription", property = "pagediscription"),
            @Result(column = "pagekewords", property = "pagekewords"),
            @Result(column = "pagetitle", property = "pagetitle"),
            @Result(column = "peixuntypeID", property = "peixuntypeID"),
            @Result(column = "qq", property = "qq"),
            @Result(column = "salestaffID", property = "salestaffID"),
            @Result(column = "hehuorenID", property = "hehuorenID"),
            @Result(column = "sitebanner", property = "sitebanner"),
            @Result(column = "sitebannerpc", property = "sitebannerpc"),
            @Result(column = "sitecontracttelinfo", property = "sitecontracttelinfo"),
            @Result(column = "sitejigouaddress", property = "sitejigouaddress"),
            @Result(column = "sitejigoudouyinhao", property = "sitejigoudouyinhao"),
            @Result(column = "sitejigouintroduce", property = "sitejigouintroduce"),
            @Result(column = "sitename", property = "sitename"),
            @Result(column = "siteweixingzh", property = "siteweixingzh"),
            @Result(column = "sitelogo", property = "sitelogo"),
            @Result(column = "verifystatus", property = "verifystatus"),
            @Result(column = "weitongguoshuoming", property = "weitongguoshuoming"),
            @Result(column = "weixin", property = "weixin"),
            @Result(column = "yixiangTypeID", property = "yixiangTypeID"),
            @Result(column = "yxfromID", property = "yxfromID"),
            @Result(column = "yxnextgenjindatetime", property = "yxnextgenjindatetime"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  oa_kehu"
            + "</script>")
    List<OaKehu> getAllList(QueryWrapper<OaKehu> queryWrapper);


    @Results(id = "KehuInfo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "kehucompanyname", property = "kehucompanyname"),
            @Result(column = "kehucontractname", property = "kehucontractname"),
            @Result(column = "maxStuNum", property = "maxStuNum"),
            @Result(column = "khShowJigouName", property = "khShowJigouName"),
            @Result(column = "kehuinfobeizhu", property = "kehuinfobeizhu"),
            @Result(column = "kehuothertel", property = "kehuothertel"),
            @Result(column = "kehutelphone", property = "kehutelphone"),
            @Result(column = "staffName", property = "staffName"),
            @Result(column = "taocanName", property = "taocanName"),
            @Result(column = "hangyetypename", property = "hangyetypename"),
            @Result(column = "realName", property = "realName"),
            @Result(column = "yxname", property = "yxname"),
            @Result(column = "yxFromName", property = "yxFromName"),
            @Result(column = "yxnextgenjindatetime", property = "yxnextgenjindatetime"),
            @Result(column = "weixin", property = "weixin"),
            @Result(column = "provinceid", property = "provinceid"),
            @Result(column = "cityid", property = "cityid"),
            @Result(column = "addTime", property = "addTime")
    })
    @Select("<script>" +
            "SELECT \n" +
            "kehu.id,\n" +
            "kehucompanyname,\n" +
            "kehu.kehucontractname,\n" +
            "kehu.maxStuNum,\n" +
            "kehu.khShowJigouName,\n" +
            "kehu.kehuinfobeizhu,\n" +
            "kehu.yxnextgenjindatetime,\n" +
            "kehu.kehuothertel,\n" +
            "kehu.weixin,\n" +
            "kehu.campusnum,\n" +
            "kehu.kehutelphone,\n" +
            "staff1.staffName,\n" +
            "peixuntype.hangyetypename,\n" +
            "hehuoren.realName,\n" +
            "yixiangtype.yxname,\n" +
            "yxfrom.yxFromName,\n" +
            "areas1.areaname as province,\n" +
            "areas2.areaname as city,\n" +
            "kehu.addTime\n" +
            "\n" +
            "from oa_kehu as kehu\n" +
            "LEFT JOIN oa_staff as staff1 on kehu.addStaffID=staff1.id\n" +
//            "LEFT JOIN oa_staff as staff2 on kehu.addStaffID=staff2.id\n" +
//            "LEFT JOIN oa_staff as staff3 on kehu.aftersalestaffID=staff3.id\n" +
            "LEFT JOIN oa_peixuntype as peixuntype on kehu.peixuntypeID=peixuntype.id\n" +
            "LEFT JOIN oa_hehuoren as hehuoren on kehu.hehuorenID=hehuoren.id\n" +
            "LEFT JOIN oa_yixiangtype as yixiangtype on kehu.yixiangTypeID=yixiangtype.id\n" +
            "LEFT JOIN oa_yxfrom as yxfrom on kehu.yxfromID=yxfrom.id\n" +
            "LEFT JOIN areas as areas1 on kehu.provinceid=areas1.id\n" +
            "LEFT JOIN areas as areas2 on kehu.cityid=areas2.id\n" +
            " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<KehuVo> getAllKehuInfo(Page<KehuVo> page, @Param("ew") Wrapper wrapper);


    @ResultMap("KehuInfo")
    @Select("<script>" +
            "SELECT \n" +
            "kehu.id,\n" +
            "kehucompanyname,\n" +
            "kehu.kehucontractname,\n" +
            "kehu.maxStuNum,\n" +
            "kehu.khShowJigouName,\n" +
            "kehu.kehuinfobeizhu,\n" +
            "kehu.yxnextgenjindatetime,\n" +
            "kehu.kehuothertel,\n" +
            "kehu.weixin,\n" +
            "kehu.kehutelphone,\n" +
            "kehu.provinceid,\n" +
            "kehu.cityid,\n" +
            "staff1.staffName,\n" +
            "taocan.taocanName,\n" +
            "peixuntype.hangyetypename,\n" +
            "hehuoren.realName,\n" +
            "yixiangtype.yxname,\n" +
            "yxfrom.yxFromName,\n" +
            "areas1.areaname as province,\n" +
            "areas2.areaname as city,\n" +
            "manyidu.manyiduName,\n" +
            "kehu.addTime\n" +
            "\n" +
            "from oa_kehu as kehu\n" +
            "LEFT JOIN oa_staff as staff1 on kehu.addStaffID=staff1.id\n" +
//            "LEFT JOIN oa_staff as staff2 on kehu.addStaffID=staff2.id\n" +
//            "LEFT JOIN oa_staff as staff3 on kehu.aftersalestaffID=staff3.id\n" +
            "LEFT JOIN oa_taocantype as taocan on kehu.taocanID=taocan.id\n" +
            "LEFT JOIN oa_peixuntype as peixuntype on kehu.peixuntypeID=peixuntype.id\n" +
            "LEFT JOIN oa_hehuoren as hehuoren on kehu.hehuorenID=hehuoren.id\n" +
            "LEFT JOIN oa_yixiangtype as yixiangtype on kehu.yixiangTypeID=yixiangtype.id\n" +
            "LEFT JOIN oa_yxfrom as yxfrom on kehu.yxfromID=yxfrom.id\n" +
            "LEFT JOIN oa_manyidu as manyidu on kehu.manyiduID=manyidu.id\n" +
            "LEFT JOIN areas as areas1 on kehu.provinceid=areas1.id\n" +
            "LEFT JOIN areas as areas2 on kehu.cityid=areas2.id\n" +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    KehuVo getOneKehuById(@Param("ew") Wrapper wrapper);

    @Results(id = "YiqiandanKehuInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "qiyeID", column = "qiyeID"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "qiandanstate", column = "qiandanstate"),
            @Result(property = "kehuType", column = "kehuType"),
            @Result(property = "kehuUseState", column = "kehuUseState"),
            @Result(property = "manyiduName", column = "manyiduName"),
            @Result(property = "kehucontractname", column = "kehucontractname"),
            @Result(property = "kehutelphone", column = "kehutelphone"),
            @Result(property = "hangyetypename", column = "hangyetypename"),
            @Result(property = "pxcontent", column = "pxcontent"),
            @Result(property = "firstqiandandatetime", column = "firstqiandandatetime"),
            @Result(property = "hetong", column = "hetong"),
            @Result(property = "nextpaydatetime", column = "nextpaydatetime"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "djqRemain", column = "djqRemain"),
            @Result(property = "areaname", column = "areaname"),
            @Result(property = "afterstaffName", column = "afterstaffName"),
            @Result(property = "color", column = "color"),
    })
    @Select("<script>" +
            "SELECT qiandan.id,\n" +
            "qiandan.qiyeID,\n" +
            "kehu.kehucompanyname,\n" +
            "qiandan.qiandanstate,\n" +
            "manyidu.manyiduName,\n" +
            "kehu.kehucontractname,\n" +
            "kehu.kehutelphone,\n" +
            "kehu.kehuType,\n" +
            "kehu.kehuUseState,\n" +
            "kehu.firstqiandandatetime,\n" +
            "kehu.nextpaydatetime,\n" +
            "peixuntype.hangyetypename,\n" +
            "staff.staffName,\n" +
            "staff2.staffName as afterstaffName,\n" +
            //"peixunrecord.pxcontent,\n" +
            "kehu.djqRemain,\n" +
            "manyidu.color,\n" +
            "areas1.areaname as province,\n" +
            "areas2.areaname as city,\n" +
            "qiandan.areaname\n" +
            "\n" +
            "from oa_qiandan qiandan \n" +
            "LEFT JOIN oa_kehu kehu on qiandan.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_manyidu manyidu on kehu.manyiduID=manyidu.id\n" +
            "LEFT JOIN oa_peixuntype peixuntype on kehu.peixuntypeID=peixuntype.id\n" +
            "LEFT JOIN oa_staff staff on qiandan.salestaffID=staff.id\n" +
            //"LEFT JOIN oa_peixunrecord peixunrecord on peixunrecord.addstaffID= staff.id\n" +
            "LEFT JOIN areas as areas1 on kehu.provinceid=areas1.id\n" +
            "LEFT JOIN areas as areas2 on kehu.cityid=areas2.id\n" +
            "LEFT JOIN oa_staff staff2 on kehu.aftersalestaffID=staff2.id " + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<YiqiandanKehuVo> getAllYiqiandanKehuInfo(Page<YiqiandanKehuVo> page, @Param("ew") Wrapper wrapper);

    @ResultMap(value = "YiqiandanKehuInfo")
    @Select("<script>" + "SELECT qiandan.id,\n" +
            "qiandan.qiyeID,\n" +
            "kehu.kehucompanyname,\n" +
            "qiandan.qiandanstate,\n" +
            "manyidu.manyiduName,\n" +
            "kehu.kehucontractname,\n" +
            "kehu.kehutelphone,\n" +
            "kehu.kehuType,\n" +
            "kehu.kehuUseState,\n" +
            "kehu.firstqiandandatetime,\n" +
            "kehu.nextpaydatetime,\n" +
            "peixuntype.hangyetypename,\n" +
            "staff.staffName,\n" +
            "staff2.staffName as afterstaffName,\n" +
            "peixunrecord.pxcontent,\n" +
            "kehu.djqRemain,\n" +
            "kehu.areaname\n" +
            "\n" +
            "from oa_qiandan qiandan \n" +
            "LEFT JOIN oa_kehu kehu on qiandan.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_manyidu manyidu on kehu.manyiduID=manyidu.id\n" +
            "LEFT JOIN oa_peixuntype peixuntype on kehu.peixuntypeID=peixuntype.id\n" +
            "LEFT JOIN oa_staff staff on kehu.salestaffID=staff.id\n" +
            "LEFT JOIN oa_peixunrecord peixunrecord on peixunrecord.addstaffID= staff.id\n" +
            "LEFT JOIN oa_staff staff2 on kehu.aftersalestaffID=staff2.id " +
            " where kehu.id=#{qiyeID}"
            + "</script>")
    YiqiandanKehuVo getOneYiqiandanKehuInfo(Long qiyeID);

    /**
     * 续费
     *
     * @param id
     * @param y
     */
    @Update("UPDATE oa_kehu SET nextpaydatetime = DATE_ADD(nextpaydatetime, INTERVAL #{y} YEAR) WHERE id =#{id}; ")
    void xufei(Long id, int y);


    @ResultMap(value = "BaseResultMap")
    @Select("select oa_kehu.id,oa_kehu.kehucompanyname from oa_qiandan " +
            "right join oa_kehu on oa_qiandan.qiyeID=oa_kehu.id where oa_qiandan.qiandanstate=1")
    List<OaKehu> getAllYQDKehukehucompanyname();

    @Select("SELECT count(qiyeID) from oa_peixunrecord where qiyeID=#{qiyeID}")
        //获取客户培训次数
    Integer getKehuPeixunCount(Long qiyeID);


    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "qiyeID", column = "qiyeID"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "kehucontractname", column = "kehucontractname"),
            @Result(property = "smsRemain", column = "smsRemain"),
            @Result(property = "kehutelphone", column = "kehutelphone"),
            @Result(property = "buySum", column = "buySum"),
            @Result(property = "price", column = "price"),
            @Result(property = "sumMoney", column = "sumMoney"),
            @Result(property = "buyTime", column = "buyTime"),
    })
    @Select("<script>" +
            "SELECT sms.id,\n" +
            "sms.qiyeID,\n" +
            "sms.buySum,\n" +
            "sms.price,\n" +
            "sms.sumMoney,\n" +
            "kehu.kehucompanyname,\n" +
            "kehu.smsRemain,\n" +
            "kehu.kehucontractname,\n" +
            "kehu.kehutelphone,\n" +
            "sms.buyTime\n" +
            "from oa_sms_buyrecords sms\n" +
            "LEFT JOIN oa_kehu kehu \n" +
            "on sms.qiyeID=kehu.id where 1=1 \n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<KehuSmsVo> getAllKehusms(Page<KehuSmsVo> page, @Param("ew") Wrapper wrapper);

}