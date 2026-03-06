package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.OA.OaQiandanXufeiSp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-25
 */
@Repository
public interface IOaQiandanXufeiSpDao extends BaseMapper<OaQiandanXufeiSp> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "qiyeID", property = "qiyeid"),
                @Result(column = "oldCampusNum", property = "oldcampusnum"),
                @Result(column = "addCampusNum", property = "addcampusnum"),
                @Result(column = "addCampusNextpayDateTime", property = "addcampusnextpaydatetime"),
                @Result(column = "nextpayDateTime", property = "nextpaydatetime"),
                @Result(column = "oldTaocanTypeID", property = "oldtaocantypeid"),
                @Result(column = "newTaocanTypeID", property = "newtaocantypeid"),
                @Result(column = "hardwareFahuoState", property = "hardwarefahuostate"),
                @Result(column = "hehuorenID", property = "hehuorenid"),
                @Result(column = "orderid", property = "orderid"),
                @Result(column = "ruanjianjine", property = "ruanjianjine"),
                @Result(column = "yingjianjine", property = "yingjianjine"),
                @Result(column = "daijinquan", property = "daijinquan"),
                @Result(column = "shishoumoney", property = "shishoumoney"),
                @Result(column = "salestaffID", property = "salestaffid"),
                @Result(column = "qiandanDatetime", property = "qiandandatetime"),
                @Result(column = "xufeiType", property = "xufeitype"),
                @Result(column = "maxStuNum", property = "maxstunum"),
                @Result(column = "zengsong", property = "zengsong"),
                @Result(column = "buyhardwarelists", property = "buyhardwarelists"),
                @Result(column = "qiandanbeizhu", property = "qiandanbeizhu"),
                @Result(column = "addUser", property = "adduser"),
                @Result(column = "addTime", property = "addtime"),
                @Result(column = "shengpiState", property = "shengpistate"),
                @Result(column = "shenpiNopassReason", property = "shenpinopassreason"),
                @Result(column = "shengpiDate", property = "shengpidate"),
                @Result(column = "shengpiStaff", property = "shengpistaff"),
    })
    @Select("<script>" +
            "SELECT * from  oa_qiandan_xufei_sp"
            + "</script>")
    List<OaQiandanXufeiSp> getAllList();

    @Select("<script>" +
            "SELECT\n" +
            "\tqiandansp.*,\n" +
            "(select staffName from pxstafftable where id =qiandansp.shengpiStaff) spstaffName, "+
            "\tkehu.kehucompanyname,\n" +
            "\tstaff.staffName,\n" +
            "\ttaocan.taocanName \n" +
            "FROM\n" +
            "\toa_qiandan_xufei_sp qiandansp\n" +
            "\tLEFT JOIN oa_kehu kehu ON qiandansp.qiyeID = kehu.id\n" +
            "\tLEFT JOIN oa_staff staff ON qiandansp.salestaffID = staff.id\n" +
            "\tLEFT JOIN oa_taocantype taocan ON qiandansp.oldTaocanTypeID = taocan.id \n" +
            "WHERE\n" +
            "\t1 = 1 " +
            "<if test='ew.sqlSegment!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
        //获取所有的签单审批信息
    Page<HashMap<String,Object>> getxfQiandanSpInfo(Page page, @Param("ew") Wrapper wrapper);

}