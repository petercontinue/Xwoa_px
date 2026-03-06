package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxyxtelfromtable;
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
public interface IPxyxtelfromtableDao extends BaseMapper<Pxyxtelfromtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "telFromName", property = "telFromName"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxtelfromtable"
            + "</script>")
    List<Pxyxtelfromtable> getAllList();


    @Select("<script>" +
            "SELECT campus.campusName, " +
            "stu.zidingyiStuID," +
            "stu.stuName," +
            "grade.stuGradeName," +
            "yxly.telFromName," +
            "qdinfo.HetongMoney," +
            "qdinfo.shishouTotalMoney," +
            "\t(SELECT GROUP_CONCAT(staff.staffName) \n" +
            "            FROM pxqiandanstafftable AS qds \n" +
            "            LEFT JOIN pxstafftable AS staff ON qds.staffID=staff.id WHERE qiandanID =qdinfo.id ) AS yejistaffName,"+
            "staff.staffName," +
            "qdinfo.qiandandate " +
            "FROM pxstutable stu " +
            "LEFT JOIN pxyxtelfromtable yxly ON stu.yxFromID = yxly.id " +
            "LEFT JOIN pxcampustable campus ON stu.campusID = campus.id " +
            "LEFT JOIN pxstugradetable grade ON stu.stuGradeID=grade.id " +
            "JOIN pxqiandaninfotable qdinfo ON qdinfo.stuID = stu.id " +
            "LEFT JOIN pxstafftable staff ON qdinfo.qianDanStaffID=staff.id " +
            "where 1=1"+
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY stu.id"
            + "</script>")
    Page<HashMap<String,String>> getZhaoshengList(Page page , @Param("ew")Wrapper wrapper);

    @Select("<script>" +
            "SELECT \n" +
            "            aaa.yxlyID,\n" +
            "            aaa.telFromName,\n" +
            "            ROUND((aaa.fznum/num)*100,2) AS bili\n" +
            "            FROM \n" +
            "             (SELECT yxlyID,telFromName,count(*) as fznum,qiandandate FROM (SELECT yxly.id as yxlyID,yxly.telFromName,qdinfo.qiandandate FROM pxstutable stu  \n" +
            "                        LEFT JOIN pxyxtelfromtable yxly ON stu.yxFromID = yxly.id \n" +
            "                        LEFT JOIN pxcampustable campus ON stu.campusID = campus.id \n" +
            "                        LEFT JOIN pxstugradetable grade ON stu.stuGradeID=grade.id \n" +
            "                        LEFT JOIN pxqiandaninfotable qdinfo ON qdinfo.stuID = stu.id \n" +
            "                        LEFT JOIN pxstafftable staff ON qdinfo.qianDanStaffID=staff.id\n" +
            "             where 1=1 \n" +
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "            ) tmp GROUP BY yxlyID) as aaa\n" +
            "             LEFT JOIN (select count(*) as num FROM (SELECT yxly.id as yxlyID,yxly.telFromName,qdinfo.qiandandate FROM pxstutable stu  \n" +
            "                        LEFT JOIN pxyxtelfromtable yxly ON stu.yxFromID = yxly.id \n" +
            "                        LEFT JOIN pxcampustable campus ON stu.campusID = campus.id \n" +
            "                        LEFT JOIN pxstugradetable grade ON stu.stuGradeID=grade.id \n" +
            "                        LEFT JOIN pxqiandaninfotable qdinfo ON qdinfo.stuID = stu.id \n" +
            "                        LEFT JOIN pxstafftable staff ON qdinfo.qianDanStaffID=staff.id\n" +
            "             where 1=1 \n" +
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "            ) tmp ) as bbb ON 1=1"
            + "</script>")
    List<HashMap<String,String>> getZhaoshengBili(@Param("ew")Wrapper wrapper);

    @Select("<script>" +
            "SELECT * from  pxyxtelfromtable"+
            " where 1=1 "+
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String,String>> getLaiyuantujingList(@Param("ew")Wrapper wrapper);
}