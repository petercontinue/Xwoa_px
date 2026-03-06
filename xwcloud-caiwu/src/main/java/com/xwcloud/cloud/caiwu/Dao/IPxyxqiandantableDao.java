package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxyxqiandantable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-01
 */
public interface IPxyxqiandantableDao extends BaseMapper<Pxyxqiandantable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qianDanMoney", property = "qianDanMoney"),
            @Result(column = "isBaomingOrChongzhi", property = "isBaomingOrChongzhi"),
            @Result(column = "yxShichangRenID", property = "yxShichangRenID"),
            @Result(column = "yxDengjiRenID", property = "yxDengjiRenID"),
            @Result(column = "yxFuzheRenID", property = "yxFuzheRenID"),
            @Result(column = "yxQiandanRenID", property = "yxQiandanRenID"),
            @Result(column = "yxDengjiDateTime", property = "yxDengjiDateTime"),
            @Result(column = "yxFenpeiDateTime", property = "yxFenpeiDateTime"),
            @Result(column = "yxQiandanDateTime", property = "yxQiandanDateTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxqiandantable"
            + "</script>")
    List<Pxyxqiandantable> getAllList();

    @Select("<script>" +
            "SELECT stu.stuName,yxqd.* FROM pxyxqiandantable yxqd \n" +
            "LEFT JOIN pxstafftable staff ON yxqd.yxQiandanRenID = staff.id\n" +
            "LEFT JOIN pxcampustable campus ON staff.campusID = campus.id\n" +
            "LEFT JOIN pxstutable stu ON yxqd.stuID = stu.id "+
            "WHERE 1=1 "+
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String,String>> getYxtongjiDetail(Page page,@Param("ew")Wrapper wrapper);

    @Select("<script>" +
            "SELECT yxqd.yxQiandanRenID,campus.id as campusID,campus.campusName,staff.staffName,count(*) AS stuNum,SUM(yxqd.qianDanMoney) AS qianDanMoney " +
            "FROM pxyxqiandantable yxqd \n" +
            "LEFT JOIN pxstafftable staff ON yxqd.yxQiandanRenID = staff.id\n" +
            "LEFT JOIN pxcampustable campus ON staff.campusID = campus.id\n" +
            "LEFT JOIN pxstutable stu ON yxqd.stuID = stu.id\n" +
            "WHERE 1=1 "+
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "GROUP BY yxqd.yxQiandanRenID"
            + "</script>")
    Page<HashMap<String,String>> getYixiangPage(Page page,@Param("ew")Wrapper wrapper);

    @Select("<script>" +
            "SELECT yxqd.yxQiandanRenID,campus.id as campusID,campus.campusName,staff.staffName,count(*) AS stuNum,SUM(yxqd.qianDanMoney) AS qianDanMoney " +
            "FROM pxyxqiandantable yxqd \n" +
            "LEFT JOIN pxstafftable staff ON yxqd.yxQiandanRenID = staff.id\n" +
            "LEFT JOIN pxcampustable campus ON staff.campusID = campus.id\n" +
            "LEFT JOIN pxstutable stu ON yxqd.stuID = stu.id\n" +
            "WHERE 1=1 "+
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "GROUP BY yxqd.yxQiandanRenID"
            + "</script>")
    List<HashMap<String,Object>> getYixiangList(@Param("ew")Wrapper wrapper);
}