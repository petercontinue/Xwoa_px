package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.PxstuHuifang;
import com.xwcloud.cloud.model.Vo.oldstuhuifangVO;
import com.xwcloud.cloud.model.entity.Pxstuhuifangtable;
import com.xwcloud.cloud.model.Vo.PxstuhuifangVo;
import com.xwcloud.cloud.model.Vo.PxstutablekechengVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
public interface IPxstuhuifangtableDao extends BaseMapper<Pxstuhuifangtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "hfType", property = "hfType"),
            @Result(column = "text", property = "text"),
            @Result(column = "hfstaffID", property = "hfstaffID"),
            @Result(column = "huifangTime", property = "huifangTime"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addstaffID", property = "addstaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstuhuifangtable"
            + "</script>")
    List<Pxstuhuifangtable> getAllList();

    @Select("<script>" +
           "SELECT stu.*,campus.campusName,staff.staffName as banzhurenName, stu.id AS stuID," +
            "(select count(*) from pxstuhuifangtable where stuID=stu.id and hfType=2) as totalDetails " +
            "FROM pxstutable stu " +
            "LEFT JOIN pxstafftable AS staff ON stu.banzhurenTeacherID = staff.id " +
            "LEFT JOIN pxcampustable as campus ON stu.campusID = campus.id " +
            "WHERE 1=1 and stu.buxiStateID != 1 and campus.isOpen !=2"+
            "<if test='ew!=null'>" +
            "and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxstuhuifangVo> getStuPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT stu.*,campus.campusName,staff.staffName as banzhurenName,stu.id stuID, " +
            "concat((select count(*) from pxstuhuifangtable where stuID=stu.id and hfType=2),'次') as totalDetails " +
            "FROM pxstutable stu " +
            "LEFT JOIN pxstafftable AS staff ON stu.banzhurenTeacherID = staff.id " +
            "LEFT JOIN pxcampustable as campus ON stu.campusID = campus.id " +
            "WHERE 1=1 and stu.buxiStateID != 1 and campus.isOpen !=2"+
            "<if test='ew!=null'>" +
            "and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxstuhuifangVo> getStuList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT stu.*,campus.campusName,staff.staffName as banzhurenName, " +
            "concat((select count(*) from pxstuhuifangtable where stuID=stu.id and hfType=2),'次') as totalDetails " +
            "FROM pxstuclasstable as stuclass " +
            "LEFT JOIN pxbuxikechengtable as buxikecheng ON stuclass.buxiID = buxikecheng.id " +
            "LEFT JOIN pxstutable as stu ON buxikecheng.stuID = stu.id " +
            "LEFT JOIN pxstafftable AS staff ON stu.banzhurenTeacherID = staff.id " +
            "LEFT JOIN pxcampustable as campus ON stu.campusID = campus.id " +
            "WHERE 1=1 and stu.buxiStateID != 1 and campus.isOpen !=2"+
            "<if test='ew!=null'>" +
            "and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxstuhuifangVo> getStuPageByClass(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT stu.*,campus.campusName,staff.staffName as banzhurenName " +
            "FROM pxstutable stu " +
            "LEFT JOIN  pxstafftable AS staff ON stu.banzhurenTeacherID = staff.id " +
            "LEFT JOIN pxcampustable as campus ON stu.campusID = campus.id " +
            "WHERE 1=1 and stu.buxiStateID != 1 and campus.isOpen !=2"+
            "<if test='ew!=null'>" +
            "and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    PxstuHuifang getStu(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT stu.*,kecheng.kechengName,kecheng.isShow,class.className,staff.staffName as banzhurenName," +
            "(" +
            "CASE " +
            " WHEN kecheng.isShow = 1 THEN " +
            " '启用' " +
            " ELSE " +
            " '未启用' " +
            " END " +
            " ) AS isShow " +
            "FROM pxbuxikechengtable buxikecheng " +
            "LEFT JOIN pxstutable stu ON stu.id=buxikecheng.stuID " +
            "LEFT JOIN pxkechengtable kecheng ON buxikecheng.kechengID= kecheng.id " +
            "LEFT JOIN pxstuclasstable stuclass ON stuclass.buxiID=buxikecheng.id " +
            "LEFT JOIN pxclasstable class ON class.id=stuclass.classID " +
            "LEFT JOIN pxstafftable staff ON staff.id=stu.banzhurenTeacherID " +
            "WHERE 1=1 and stu.buxiStateID != 1 "+
            "<if test='ew!=null'>" +
            "and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxstutablekechengVo> getStuKechengList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT huifang.*,staff.staffName \n" +
            "FROM pxstuhuifangtable huifang \n" +
            "LEFT JOIN pxstafftable staff ON huifang.hfstaffID= staff.id \n" +
            "LEFT JOIN pxstutable stu ON stu.id=huifang.stuID "+
            "WHERE 1=1 and stu.buxiStateID != 1 "+
            "<if test='ew!=null'>" +
            "and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxstuhuifangVo> getStuhuifangList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT huifang.*,staff.staffName AS huifangTeacherName FROM pxstuhuifangtable AS huifang LEFT JOIN pxstafftable AS staff ON huifang.hfstaffID = staff.id "+
            "WHERE 1=1 "+
            "<if test='ew!=null'>" +
            "and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<oldstuhuifangVO> getPage(Page page, @Param("ew") Wrapper wrapper);
}