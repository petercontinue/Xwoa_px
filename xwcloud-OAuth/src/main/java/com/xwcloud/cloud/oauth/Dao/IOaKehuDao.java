package com.xwcloud.cloud.oauth.Dao;

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


}