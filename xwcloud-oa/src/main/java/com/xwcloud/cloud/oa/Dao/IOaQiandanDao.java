package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.OA.OaQiandan;
import com.xwcloud.cloud.model.OA.Vo.QiandanInfoVo;
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
public interface IOaQiandanDao extends BaseMapper<OaQiandan> {

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
            @Result(column = "yingjianjine", property = "yingjianjine"),
            @Result(column = "zengsong", property = "zengsong"),
    })
    @Select("<script>" +
            "SELECT * from  oa_qiandan"
            + "</script>")
    List<OaQiandan> getAllList();


    @Results(id = "QiandanInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "area", column = "area"),
            @Result(property = "areaname", column = "areaname"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "buyhardwarelists", column = "buyhardwarelists"),
            @Result(property = "hardwareFahuoState", column = "hardwareFahuoState"),
            @Result(property = "realName", column = "realName"),
            @Result(property = "fanyong", column = "fanyong"),
            @Result(property = "fanyongState", column = "fanyongState"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "fanyongTime", column = "fanyongTime"),
            @Result(property = "fanyongLiushuiID", column = "fanyongLiushuiID"),
            @Result(property = "fanyongBeizhu", column = "fanyongBeizhu"),
            @Result(property = "hetong", column = "hetong"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "khShowJigouName", column = "khShowJigouName"),
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "kehuType", column = "kehuType"),
            @Result(property = "qiandanDatetime", column = "qiandanDatetime"),
            @Result(property = "qiandanstate", column = "qiandanstate"),
            @Result(property = "qiandanbeizhu", column = "qiandanbeizhu"),
            @Result(property = "ruanjianjine", column = "ruanjianjine"),
            @Result(property = "shishoumoney", column = "shishoumoney"),
            @Result(property = "taocanName", column = "taocanName"),
            @Result(property = "xinqianorxufei", column = "xinqianorxufei"),
            @Result(property = "yingjianjine", column = "yingjianjine"),
            @Result(property = "zengsong", column = "zengsong")
    })
    @Select("<script>" + "SELECT qiandan.id,\n" +
            "qiandan.area,\n" +
            "qiandan.areaname,\n" +
            "qiandan.buyhardwarelists,\n" +
            "qiandan.hardwareFahuoState,\n" +
            "hehuoren.realName,\n" +
            "qiandan.fanyong,\n" +
            "qiandan.fanyongState,\n" +
            "oa_staff.staffName,\n" +
            "qiandan.fanyongTime,\n" +
            "qiandan.fanyongLiushuiID,\n" +
            "qiandan.fanyongBeizhu,\n" +
            "qiandan.hetong,\n" +
            "kehu.kehucompanyname,\n" +
            "kehu.khShowJigouName,\n" +
            "kehu.kehuType,\n" +
            "qiandan.orderid,\n" +
            "qiandan.qiandanDatetime,\n" +
            "qiandan.qiandanstate,\n" +
            "qiandan.qiandanbeizhu,\n" +
            "qiandan.ruanjianjine,\n" +
            "qiandan.shishoumoney,\n" +
            "areas1.areaname as province,\n" +
            "areas2.areaname as city,\n" +
            "taocan.taocanName,\n" +
            "qiandan.xinqianorxufei,\n" +
            "qiandan.yingjianjine,\n" +
            "qiandan.zengsong\n" +
            "\n" +
            "from oa_qiandan qiandan\n" +
            "LEFT JOIN oa_hehuoren hehuoren on qiandan.hehuorenID=hehuoren.id\n" +
//            "LEFT JOIN oa_staff on qiandan.fanyongOptStaffID=oa_staff.id\n" +
            "LEFT JOIN oa_staff on qiandan.salestaffID=oa_staff.id\n" +
            "LEFT JOIN oa_kehu kehu on qiandan.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_taocantype taocan on qiandan.taocanTypeID=taocan.id " +
            "LEFT JOIN areas as areas1 on kehu.provinceid=areas1.id\n" +
            "LEFT JOIN areas as areas2 on kehu.cityid=areas2.id\n" +
            " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" + "</script>")
    IPage<QiandanInfoVo> getAllQiandanInfo(Page<QiandanInfoVo> page, @Param("ew") Wrapper wrapper);


    @ResultMap(value = "QiandanInfo")
    @Select("<script>" + "SELECT qiandan.id,\n" +
            "qiandan.area,\n" +
            "qiandan.areaname,\n" +
            "product.productName,\n" +
            "qiandan.buyhardwarelists,\n" +
            "qiandan.hardwareFahuoState,\n" +
            "hehuoren.realName,\n" +
            "qiandan.fanyong,\n" +
            "qiandan.fanyongState,\n" +
            "oa_staff.staffName,\n" +
            "qiandan.fanyongTime,\n" +
            "qiandan.fanyongLiushuiID,\n" +
            "qiandan.fanyongBeizhu,\n" +
            "qiandan.hetong,\n" +
            "kehu.kehucompanyname,\n" +
            "kehu.khShowJigouName,\n" +
            "kehu.kehuType,\n" +
            "kehu.addStaffID,\n" +
            "qiandan.orderid,\n" +
            "qiandan.qiandanDatetime,\n" +
            "qiandan.qiandanstate,\n" +
            "qiandan.qiandanbeizhu,\n" +
            "qiandan.ruanjianjine,\n" +
            "qiandan.shishoumoney,\n" +
            "taocan.taocanName,\n" +
            "qiandan.xinqianorxufei,\n" +
            "qiandan.yingjianjine,\n" +
            "qiandan.zengsong,\n" +
            "qiandan.taocanID,\n" +
            "qiandan.productID,\n" +
            "qiandan.salestaffID\n" +
            "\n" +
            "from oa_qiandan qiandan\n" +
            "LEFT JOIN oa_product product on qiandan.productID=product.id\n" +
            "LEFT JOIN oa_hehuoren hehuoren on qiandan.hehuorenID=hehuoren.id\n" +
//            "LEFT JOIN oa_staff on qiandan.fanyongOptStaffID=oa_staff.id\n" +
            "LEFT JOIN oa_staff on qiandan.salestaffID=oa_staff.id\n" +
            "LEFT JOIN oa_kehu kehu on qiandan.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_taocantype taocan on qiandan.taocanTypeID=taocan.id " +

            "where qiandan.id=#{id}"
            + "</script>")
    QiandanInfoVo getOneQiandanInfo(Long id);

}