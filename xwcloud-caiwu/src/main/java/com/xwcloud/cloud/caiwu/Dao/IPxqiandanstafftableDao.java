package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxqiandanstafftable;
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
 * @since 2020-11-26
 */
public interface IPxqiandanstafftableDao extends BaseMapper<Pxqiandanstafftable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "yejiMoney", property = "yejiMoney"),
            @Result(column = "yejidateTime", property = "yejidateTime"),
            @Result(column = "isWeikuan", property = "isWeikuan"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandanstafftable"
            + "</script>")
    List<Pxqiandanstafftable> getAllList();

    @Select("<script>" +
            "SELECT  staff.staffName,post.staffpostName,campus.campusName,DATE_FORMAT(liushui.liushuiDateTime,'%Y') as liushuidate," +
            "(SUM(liushui.shouruMoney)-SUM(liushui.zhichuMoney)) as yeji,liushui.liushuiDateTime,liushui.shouzhiStyleID " +
            "FROM pxliushuizhangtable liushui \n" +
            "JOIN pxqiandaninfotable qdinfo ON qdinfo.id= liushui.qiandanID\n" +
            "JOIN pxqiandanstafftable qdstaff ON qdstaff.qiandanID = qdinfo.id\n" +
            "JOIN pxstafftable staff ON qdstaff.staffID = staff.id\n" +
            "LEFT JOIN pxcampustable campus ON campus.id = staff.campusID\n" +
            "LEFT JOIN pxstaffposttable post ON post.id = staff.staffPostID\n" +
            "WHERE 1=1 " +
            "<if test=\"qiyeID != null and  qiyeID != '' \">"+
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>"+
            "<if test=\"yearstr != null and  yearstr != '' \">"+
            " AND YEAR(liushui.liushuiDateTime) = #{yearstr} " +
            "</if>"+
            "<if test=\"campusID != null and  campusID != '' \">"+
            " AND staff.campusID = #{campusID} " +
            "</if>"+
            "AND liushui.shouzhiStyleID IN('1','2','3','7') " +
            "GROUP BY qdstaff.staffID ORDER BY yeji DESC "
            + "</script>")
    Page<HashMap<String,String>> getGerenYearPage(Page page, @Param("yearstr") String yearstr, @Param("campusID") String campusID, @Param("qiyeID") long qiyeID);

    @Select("<script>" +
            "SELECT  campus.id as campusID,staff.id as staffID,staff.staffName,post.staffpostName,campus.campusName,DATE_FORMAT(liushui.liushuiDateTime,'%Y-%m') as liushuidate," +
            "(SUM(liushui.shouruMoney)-SUM(liushui.zhichuMoney)) as yeji,liushui.liushuiDateTime,liushui.shouzhiStyleID " +
            "FROM pxliushuizhangtable liushui \n" +
            "JOIN pxqiandaninfotable qdinfo ON qdinfo.id= liushui.qiandanID\n" +
            "JOIN pxqiandanstafftable qdstaff ON qdstaff.qiandanID = qdinfo.id\n" +
            "JOIN pxstafftable staff ON qdstaff.staffID = staff.id\n" +
            "JOIN pxcampustable campus ON campus.id = staff.campusID\n" +
            "JOIN pxstaffposttable post ON post.id = staff.staffPostID\n" +
            "WHERE 1=1" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">"+
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>"+
            "<if test=\"yearstr != null and  yearstr != '' \">"+
            " AND YEAR(liushui.liushuiDateTime) = #{yearstr} " +
            "</if>"+
            "<if test=\"monthstr != null and  monthstr != '' \">"+
            " AND MONTH(liushui.liushuiDateTime) = #{monthstr}" +
            "</if>"+
            "<if test=\"campusID != null and  campusID != '' \">"+
            " AND staff.campusID = #{campusID} " +
            "</if>"+
            " AND liushui.shouzhiStyleID IN('1','2','3','7') " +
            "GROUP BY qdstaff.staffID ORDER BY yeji DESC "
            + "</script>")
    Page<HashMap<String,String>> getGerenMonthPage(Page page,@Param("yearstr") String yearstr,@Param("monthstr") String monthstr,
                                                   @Param("campusID") String campusID,@Param("qiyeID") long qiyeID

    );
}