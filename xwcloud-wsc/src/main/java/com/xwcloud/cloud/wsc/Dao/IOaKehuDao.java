package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.OA.OaKehu;
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
 * @since 2021-05-06
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
            @Result(column = "jifeiType", property = "jifeiType"),
            @Result(column = "kehutelphone", property = "kehutelphone"),
            @Result(column = "manyiduID", property = "manyiduID"),
            @Result(column = "nextpaydatetime", property = "nextpaydatetime"),
            @Result(column = "pagediscription", property = "pagediscription"),
            @Result(column = "pagekewords", property = "pagekewords"),
            @Result(column = "pagetitle", property = "pagetitle"),
            @Result(column = "peixuntypeID", property = "peixuntypeID"),
            @Result(column = "qq", property = "qq"),
            @Result(column = "salestaffID", property = "salestaffID"),
            @Result(column = "zhuanjieshaoID", property = "zhuanjieshaoID"),
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
    List<OaKehu> getAllList();

    @Select("<script>" + "SELECT distinct  * FROM oa_kehu WHERE  id in(SELECT qiyeID FROM pxstutable WHERE parentTel = #{parentTel})" + "</script>")
    List<OaKehu> GetPhoneIncloudJigouStu(String parentTel);

    @Select("<script>" + "SELECT DISTINCT * FROM oa_kehu WHERE id in(SELECT qiyeID FROM pxstafftable WHERE staffTel = #{staffTel})" + "</script>")
    List<OaKehu> GetPhoneIncloudjigouStaff(String staffTel);


}