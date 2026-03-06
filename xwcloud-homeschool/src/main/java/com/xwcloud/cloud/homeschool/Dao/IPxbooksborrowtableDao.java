package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.PxbooksborrowtableVo;
import com.xwcloud.cloud.model.entity.Pxbooksborrowtable;
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
public interface IPxbooksborrowtableDao extends BaseMapper<Pxbooksborrowtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "booksID", property = "booksID"),
            @Result(column = "people", property = "people"),
            @Result(column = "role", property = "role"),
            @Result(column = "borrownum", property = "borrownum"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "dostaffID", property = "dostaffID"),
            @Result(column = "doDate", property = "doDate"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbooksborrowtable"
            + "</script>")
    List<Pxbooksborrowtable> getAllList();

    @Select("<script>" +
            "SELECT borrow.*,books.booksName as bookName, campus.campusName,staff.staffName as dostaffName, " +
            "(CASE borrow.role " +
            " WHEN '教师' THEN (SELECT staffName FROM pxstafftable staff WHERE staff.id = borrow.people )" +
            " WHEN '学生' THEN (SELECT stuName FROM pxstutable stu WHERE stu.id = borrow.people) " +
            " ELSE (SELECT staffName FROM pxstafftable staff WHERE staff.id = borrow.people) END " +
            ") as peopleName,DATEDIFF(borrow.endDate,borrow.doDate) AS surplusDays, " +
            "(select count(*) from pxbooksreturntable where booksID=borrow.id) as returnNum " +
            "FROM pxbooksborrowtable borrow " +
            "LEFT JOIN pxbookstable books ON books.id=borrow.booksID  " +
            "LEFT JOIN pxcampustable campus ON books.campusID = campus.id " +
            "LEFT JOIN pxstafftable staff ON borrow.dostaffID = staff.id  "+
            " WHERE campus.isOpen !=2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxbooksborrowtableVo> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT borrow.*,books.booksName AS bookName, campus.campusName,staff.staffName as dostaffName, " +
            "(CASE borrow.role " +
            " WHEN '教师' THEN (SELECT staffName FROM pxstafftable staff WHERE staff.id = borrow.people )" +
            " WHEN '学生' THEN (SELECT stuName FROM pxstutable stu WHERE stu.id = borrow.people) " +
            " ELSE (SELECT staffName FROM pxstafftable staff WHERE staff.id = borrow.people) END " +
            ") as peopleName,DATEDIFF(borrow.endDate,borrow.doDate) AS surplusDays, " +
            "(select count(*) from pxbooksreturntable where booksID=borrow.id) as returnNum " +
            "FROM pxbooksborrowtable borrow " +
            "LEFT JOIN pxbookstable books ON books.id=borrow.booksID  " +
            "LEFT JOIN pxcampustable campus ON books.campusID = campus.id " +
            "LEFT JOIN pxstafftable staff ON borrow.dostaffID = staff.id  "+
            " WHERE campus.isOpen !=2 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxbooksborrowtableVo> getJoinList(@Param("ew") Wrapper wrapper);
}