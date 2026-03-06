package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.xflvVo;
import com.xwcloud.cloud.model.entity.Pxkeshistuteachertable;
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
 * @since 2020-12-02
 */
public interface IPxkeshistuteachertableDao extends BaseMapper<Pxkeshistuteachertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "keshiStuTableID", property = "keshiStuTableID"),
            @Result(column = "teacherID", property = "teacherID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshistuteachertable"
            + "</script>")
    List<Pxkeshistuteachertable> getAllList();

    @Select("<script>" +
            "SELECT  campus.id AS campusID ,campus.campusName,staff.id as teacherID,staff.staffName,stuqdT.subjectName,\n" +
            "            stuqdT.subID,COUNT(*) AS stuNum,stuqdT.xfnum \n" +
            "            FROM pxstafftable staff \n" +
            "            LEFT JOIN (SELECT DISTINCT keshiteacher.teacherID as staffID,keshi.stuID as NstuID,sub.subjectName,sub.id as subID \n" +
            "            FROM pxkeshistuteachertable keshiteacher \n" +
            "           LEFT JOIN pxkeshistutable keshi ON keshi.id = keshiteacher.keshiStuTableID\n" +
            "            LEFT JOIN pxkechengtable kecheng ON keshi.kechengID = kecheng.id\n" +
            "            LEFT JOIN pxsubjecttable sub ON kecheng.subjectID = sub.id) stuNumT ON stuNumT.staffID = staff.id \n" +
            "            LEFT JOIN (SELECT stuNumT.*,COUNT(*) AS xfnum FROM (\n" +
            "\t\t\t\t\t\tSELECT DISTINCT keshiteacher.teacherID as staffID,keshi.stuID as NstuID,sub.subjectName,sub.id as subID,keshi.haveClassDate \n" +
            "            FROM pxkeshistuteachertable keshiteacher \n" +
            "           LEFT JOIN pxkeshistutable keshi ON keshi.id = keshiteacher.keshiStuTableID\n" +
            "            LEFT JOIN pxkechengtable kecheng ON keshi.kechengID = kecheng.id\n" +
            "            LEFT JOIN pxsubjecttable sub ON kecheng.subjectID = sub.id) stuNumT\n" +
            "            LEFT JOIN pxqiandaninfotable qdinfo ON stuNumT.NstuID = qdinfo.stuID\n" +
            "            WHERE 1=1 AND qdinfo.moneyStyle = '2'\n" +
            "            GROUP BY stuNumT.staffID) stuqdT ON stuNumT.staffID = stuqdT.staffID\n" +
            "            LEFT JOIN pxcampustable campus ON campus.id=staff.campusID\n" +
            "            WHERE 1=1 \n" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "            GROUP BY staff.id"
            + "</script>")
    Page<HashMap<String, String>> getRenewPage(Page page, @Param("ew") Wrapper wrapper);


    @Select("<script>" +
            "SELECT \n" +
            "a.id,a.staffName,e.id subjectID,e.subjectName,c.kechengID, c.stuID,\n" +
            "f.id campusID,f.campusName,COUNT(DISTINCT stuID) stunum,\n" +
            "(SELECT COUNT(*) from pxqiandansubjecttable o \n" +
            "JOIN pxqiandaninfotable q on o.qianDanInfoID=q.id\n" +
            "where o.stuID=c.stuID and o.kechengID=c.kechengID and q.moneyStyle=2 and o.kechengStyle=1" +

            "<if test='ew2 != null '>" +
            " AND ${ew2.SqlSegment}" +
            "</if>" +

            ") xfnum\n" +
            "from pxstafftable a \n" +
            "JOIN pxkeshistuteachertable b on a.id =b.teacherID\n" +
            "JOIN pxkeshistutable c on b.keshiStuTableID=c.id\n" +
            "JOIN pxkechengtable d on c.kechengID =d.id\n" +
            "JOIN pxsubjecttable e on d.subjectID=e.id\n" +
            "JOIN pxcampustable f on a.campusID=f.id\n" +
            "where f.isOpen!=2 \n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY a.id,e.id" +
            "</script>")
    Page<xflvVo> getallshuju(Page page, @Param("ew") QueryWrapper queryWrapper, @Param("ew2") QueryWrapper queryWrapper1);
}