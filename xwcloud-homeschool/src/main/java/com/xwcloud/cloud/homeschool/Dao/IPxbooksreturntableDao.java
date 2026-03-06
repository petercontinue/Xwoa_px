package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.PxbooksreturntableVo;
import com.xwcloud.cloud.model.entity.Pxbooksreturntable;
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
 * @since 2020-11-13
 */
public interface IPxbooksreturntableDao extends BaseMapper<Pxbooksreturntable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "booksID", property = "booksID"),
            @Result(column = "people", property = "people"),
            @Result(column = "role", property = "role"),
            @Result(column = "returnnum", property = "returnnum"),
            @Result(column = "returnDate", property = "returnDate"),
            @Result(column = "dostaffID", property = "dostaffID"),
            @Result(column = "doDate", property = "doDate"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbooksreturntable"
            + "</script>")
    List<Pxbooksreturntable> getAllList();

    @Select("<script>" +
            "SELECT returnbook.*,campus.campusName,books.booksName,staff.staffName as dostaffName, " +
            "(CASE returnbook.role  " +
            " WHEN '教师' THEN (SELECT staffName FROM pxstafftable staff WHERE staff.id = borrow.people ) " +
            " WHEN '学生' THEN (SELECT stuName FROM pxstutable stu WHERE stu.id = borrow.people)  " +
            " ELSE (SELECT staffName FROM pxstafftable staff WHERE staff.id = borrow.people) END  " +
            ") as peopleName " +
            "FROM pxbooksreturntable returnbook " +
            "LEFT JOIN pxbooksborrowtable borrow ON borrow.id= returnbook.booksID " +
            "LEFT JOIN pxbookstable book ON borrow.booksID=books.id " +
            "LEFT JOIN pxcampustable campus ON books.campusID = campus.id " +
            "LEFT JOIN pxstafftable staff ON returnbook.dostaffID = staff.id"+
            " WHERE campus.isOpen !=2" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxbooksreturntableVo> getJoinList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT\n" +
            "\treturnbook.*,\n" +
            "\tcampus.campusName,\n" +
            "\tbooks.booksName,\n" +
            "\tstaff.staffName AS dostaffName,\n" +
            "\t(\n" +
            "\tCASE\n" +
            "\t\t\treturnbook.role \n" +
            "\t\t\tWHEN 1 THEN\n" +
            "\t\t\t( SELECT staffName FROM pxstafftable staff WHERE staff.id = returnbook.people ) \n" +
            "\t\t\tWHEN 2 THEN\n" +
            "\t\t\t( SELECT stuName FROM pxstutable stu WHERE stu.id = returnbook.people ) ELSE ( SELECT staffName FROM pxstafftable staff WHERE staff.id = returnbook.people ) \n" +
            "\t\tEND \n" +
            "\t\t) AS peopleName \n" +
            "\tFROM\n" +
            "\t\tpxbooksreturntable returnbook\n" +
            "\t\tLEFT JOIN pxbookstable books ON returnbook.booksID = books.id\n" +
            "\t\tLEFT JOIN pxcampustable campus ON books.campusID = campus.id\n" +
            "\t\tLEFT JOIN pxstafftable staff ON returnbook.dostaffID = staff.id \n" +
            "\tWHERE\n" +
            "\t\tcampus.isOpen != 2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxbooksreturntableVo> getPage(Page page,@Param("ew") Wrapper wrapper);
}