package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.PxsalarytableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystyletable;
import com.xwcloud.cloud.model.entity.Pxsalarytable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-17
 */
@Repository
public interface IPxsalarytableDao extends BaseMapper<Pxsalarytable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "salaryMoney", property = "salaryMoney"),
            @Result(column = "shenheState", property = "shenheState"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "shenheNopassReason", property = "shenheNopassReason"),
            @Result(column = "shengHeTime", property = "shengHeTime"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "salaryDate", property = "salaryDate"),
            @Result(column = "lururen", property = "lururen"),
            @Result(column = "lurudatetime", property = "lurudatetime"),
            @Result(column = "shengheren", property = "shengheren"),
            @Result(column = "fafangzhuangtai", property = "fafangzhuangtai"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxsalarytable"
            + "</script>")
    List<Pxsalarytable> getAllList();

    @Select("<script>" +
            "SELECT salary.*,campus.campusName,staffpost.staffpostName as postName,staff.staffName,lururenid.staffName as lururenName, " +
            "shengherenid.staffName as shengherenName " +
            "from  pxsalarytable salary \n" +
            "LEFT JOIN pxstafftable staff ON salary.staffID = staff.id\n" +
            "LEFT JOIN pxcampustable campus ON staff.campusID = campus.id\n" +
            "LEFT JOIN pxstaffposttable staffpost ON staffpost.id=staff.staffPostID "+
            "LEFT JOIN pxstafftable lururenid ON salary.lururen = lururenid.id\n" +
            "LEFT JOIN pxstafftable shengherenid ON salary.shengheren = shengherenid.id\n" +
            " where campus.isOpen !=2  "+
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxsalarytableVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT salary.*,campus.campusName,staffpost.staffpostName as postName,staff.staffName from  pxsalarytable salary \n" +
            "LEFT JOIN pxstafftable staff ON salary.staffID = staff.id\n" +
            "LEFT JOIN pxcampustable campus ON staff.campusID = campus.id\n" +
            "LEFT JOIN pxstaffposttable staffpost ON staffpost.id=staff.staffPostID "+
            "<if test='ew != null'>"+
            " where campus.isOpen !=2  AND "+
            " ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxsalarytableVo> getJoinList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT salarystyle.* FROM pxstafftable staff \n" +
            "LEFT JOIN pxstaffposttable post ON staff.staffPostID=post.id\n" +
            "LEFT JOIN pxsalarystaffposttable salarypost ON salarypost.staffPostID=post.id\n" +
            "LEFT JOIN pxsalarystyletable salarystyle ON salarypost.salaryStyleID=salarystyle.id "+
            " where 1=1 "+
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxsalarystyletable> getGongziPro(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT salary.*,staff.staffName FROM pxsalarytable salary \n" +
            "LEFT JOIN pxstafftable staff ON staff.id = salary.staffID"+
            " where 1=1 "+
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    PxsalarytableVo getGongzi(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT id,campusName as '校区',staffName as '员工名称',staffpostName as '岗位',DATE_FORMAT(salaryDate,'%Y-%m-%d %H:%i:%s') as '工资时间',\n" +
            "<foreach item=\"item\" collection=\"stylelist\" separator=\",\" open=\"\" close=\"\" index=\"\">\n" +
            "       SUM(${item}) as #{item} " +
            "</foreach>"+
            "FROM (\n" +
            "SELECT id,campusName,staffName,staffpostName,salaryDate, \n" +
            "<foreach item=\"item\" collection=\"stylelist\" separator=\",\" open=\"\" close=\"\" index=\"\">\n" +
            "case salaryStyle when #{item} then tmp.salarymoney else 0 end as #{item}" +
            "</foreach>"+
            "FROM(\n" +
            "SELECT salary.id,campus.campusName,staff.staffName,staffpost.staffpostName,salary.salaryDate,salarstyle.id as styleID,salarstyle.salaryStyle,xiangxi.salarymoney \n" +
            "FROM pxsalarytable salary \n" +
            "LEFT JOIN pxstafftable staff ON salary.staffID = staff.id\n" +
            "LEFT JOIN pxcampustable campus ON campus.id= staff.campusID\n" +
            "LEFT JOIN pxstaffposttable staffpost ON staff.staffPostID = staffpost.id\n" +
            "LEFT JOIN pxsalarystaffposttable salarypost ON salarypost.staffPostID = staffpost.id\n" +
            "LEFT JOIN pxsalarystyletable salarstyle ON salarypost.salaryStyleID= salarstyle.id\n" +
            "LEFT JOIN pxsalaryxiangxitable xiangxi ON xiangxi.salaryID=salary.id AND xiangxi.salarystyleID = salarstyle.id" +
            " where 1=1 "+
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            ") as tmp) tmp2\n" +
            "GROUP BY campusName,staffName,staffpostName,salaryDate,id"
            + "</script>")
    List<HashMap<String,String>> getGongzitiao(@Param("ew") Wrapper wrapper, @Param("stylelist") List<String> stylelist);


    @Select("<script>" +
            "SELECT salary.*,campus.campusName,staffpost.staffpostName as postName,staff.staffName,lururenid.staffName as lururenName, " +
            "shengherenid.staffName as shengherenName " +
            "from  pxsalarytable salary \n" +
            "LEFT JOIN pxstafftable staff ON salary.staffID = staff.id\n" +
            "LEFT JOIN pxcampustable campus ON staff.campusID = campus.id\n" +
            "LEFT JOIN pxstaffposttable staffpost ON staffpost.id=staff.staffPostID "+
            "LEFT JOIN pxstafftable lururenid ON salary.lururen = lururenid.id\n" +
            "LEFT JOIN pxstafftable shengherenid ON salary.shengheren = shengherenid.id\n" +
            " where campus.isOpen !=2  "+
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxsalarytableVo> GetSalaryList(@Param("ew") Wrapper wrapper);
}