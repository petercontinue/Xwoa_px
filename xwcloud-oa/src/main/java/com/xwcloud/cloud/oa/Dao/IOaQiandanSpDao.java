package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.OA.OaQiandanSp;
import com.xwcloud.cloud.model.OA.Vo.QiandanSpVo;
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
 * @since 2021-07-21
 */
@Repository
public interface IOaQiandanSpDao extends BaseMapper<OaQiandanSp> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "area", property = "area"),
            @Result(column = "areaname", property = "areaname"),
            @Result(column = "productID", property = "productID"),
            @Result(column = "buyhardwarelists", property = "buyhardwarelists"),
            @Result(column = "hardwareFahuoState", property = "hardwareFahuoState"),
            @Result(column = "hehuorenID", property = "hehuorenID"),
            @Result(column = "fanyong", property = "fanyong"),
            @Result(column = "fanyongState", property = "fanyongState"),
            @Result(column = "fanyongOptStaffID", property = "fanyongOptStaffID"),
            @Result(column = "fanyongTime", property = "fanyongTime"),
            @Result(column = "fanyongLiushuiID", property = "fanyongLiushuiID"),
            @Result(column = "fanyongBeizhu", property = "fanyongBeizhu"),
            @Result(column = "hetong", property = "hetong"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "orderid", property = "orderid"),
            @Result(column = "qiandanDatetime", property = "qiandanDatetime"),
            @Result(column = "qiandanstate", property = "qiandanstate"),
            @Result(column = "qiandanbeizhu", property = "qiandanbeizhu"),
            @Result(column = "ruanjianjine", property = "ruanjianjine"),
            @Result(column = "salestaffID", property = "salestaffID"),
            @Result(column = "shishoumoney", property = "shishoumoney"),
            @Result(column = "taocanTypeID", property = "taocanTypeID"),
            @Result(column = "xinqianorxufei", property = "xinqianorxufei"),
            @Result(property = "xufeiType", column = "xufeiType"),
            @Result(property = "campusNum", column = "campusNum"),
            @Result(property = "taocanID", column = "taocanID"),
            @Result(column = "yingjianjine", property = "yingjianjine"),
            @Result(column = "zengsong", property = "zengsong"),
            @Result(column = "shengpiState", property = "shengpiState"),
            @Result(column = "shenpiNopassReason", property = "shenpiNopassReason"),
            @Result(column = "shengpiDate", property = "shengpiDate"),
            @Result(column = "shengpiStaff", property = "shengpiStaff"),
    })
    @Select("<script>" +
            "SELECT * from  oa_qiandan_sp"
            + "</script>")
    List<OaQiandanSp> getAllList();


    @Results(id = "QiandanSpInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "area", column = "area"),
            @Result(property = "areaname", column = "areaname"),
            @Result(property = "productID", column = "productID"),
            @Result(property = "buyhardwarelists", column = "buyhardwarelists"),
            @Result(property = "hardwareFahuoState", column = "hardwareFahuoState"),
            @Result(property = "hehuorenID", column = "hehuorenID"),
            @Result(property = "fanyong", column = "fanyong"),
            @Result(property = "fanyongState", column = "fanyongState"),
            @Result(property = "fanyongOptStaffID", column = "fanyongOptStaffID"),
            @Result(property = "fanyongTime", column = "fanyongTime"),
            @Result(property = "fanyongLiushuiID", column = "fanyongLiushuiID"),
            @Result(property = "fanyongBeizhu", column = "fanyongBeizhu"),
            @Result(property = "hetong", column = "hetong"),
            @Result(property = "qiyeID", column = "qiyeID"),
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "qiandanDatetime", column = "qiandanDatetime"),
            @Result(property = "qiandanstate", column = "qiandanstate"),
            @Result(property = "qiandanbeizhu", column = "qiandanbeizhu"),
            @Result(property = "ruanjianjine", column = "ruanjianjine"),
            @Result(property = "salestaffID", column = "salestaffID"),
            @Result(property = "shishoumoney", column = "shishoumoney"),
            @Result(property = "taocanTypeID", column = "taocanTypeID"),
            @Result(property = "xinqianorxufei", column = "xinqianorxufei"),
            @Result(property = "xufeiType", column = "xufeiType"),
            @Result(property = "campusNum", column = "campusNum"),
            @Result(property = "taocanID", column = "taocanID"),
            @Result(property = "yingjianjine", column = "yingjianjine"),
            @Result(property = "zengsong", column = "zengsong"),
            @Result(property = "shengpiState", column = "shengpiState"),
            @Result(property = "shenpiNopassReason", column = "shenpiNopassReason"),
            @Result(property = "shengpiDate", column = "shengpiDate"),
            @Result(property = "shengpiStaff", column = "shengpiStaff"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "taocanName", column = "taocanName"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "productName", column = "productName"),

    })
    @Select("<script>" +
            "SELECT qiandansp.*,\n" +
            "kehu.kehucompanyname,\n" +
            "staff.staffName,\n" +
            "(select staffName from pxstafftable where id =qiandansp.shengpiStaff) spstaffName, "+
            "taocan.taocanName\n" +
            "FROM oa_qiandan_sp qiandansp\n" +
            "LEFT JOIN oa_kehu kehu on qiandansp.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff staff on qiandansp.salestaffID=staff.id\n" +
            "LEFT JOIN oa_taocantype taocan on qiandansp.taocanTypeID=taocan.id\n" +
            " where 1=1 " +
            "<if test='ew.sqlSegment!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
        //获取所有的签单审批信息
    Page<QiandanSpVo> getAllQiandanSpInfo(Page<QiandanSpVo> page, @Param("ew") Wrapper wrapper);
}