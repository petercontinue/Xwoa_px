package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.classInfoListVO;
import com.xwcloud.cloud.model.Vo.exporttuisongVO;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;

import com.xwcloud.cloud.model.Vo.wchatmessageVO;
import com.xwcloud.cloud.model.Vo.searchVO;
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
 * @since 2020-11-06
 */
@Repository
public interface IPxtuisongtableDao extends BaseMapper<Pxtuisongtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "note", property = "note"),
            @Result(column = "tuisongTypeName", property = "tuisongTypeName"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "role", property = "role"),
            @Result(column = "wxstate", property = "wxstate"),
            @Result(column = "appread", property = "appread"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtuisongtable"
            + "</script>")
    List<Pxtuisongtable> getAllList();

    @Select("<script>" +
            "SELECT * from  pxstutable " +
            "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxstutable> getStuList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT stu.* FROM pxstuclasstable as stuclass " +
            "LEFT JOIN pxbuxikechengtable as buxikecheng ON stuclass.buxiID= buxikecheng.id " +
            "LEFT JOIN pxstutable as stu ON buxikecheng.stuID=stu.id" +
            " WHERE 1=1" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxstutable> getStuByClassList(@Param("ew") Wrapper wrapper);


    @Select("<script>" + "SELECT\n" +
            "\ta.*,d.tuisongType,b.id AS stuID,b.zidingyiStuID,b.stuName,c.campusName,b.campusID,banzhuren.staffName AS banzhurenName\n" +
            "FROM\n" +
            "\tpxtuisongtable AS a\n" +
            "\tLEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "\tLEFT JOIN pxcampustable AS c ON b.campusID = c.id\n" +
            "\tLEFT JOIN pxtuisongtypetable AS d ON a.tuisongTypeName = d.id \n" +
            "\tLEFT JOIN pxstafftable AS banzhuren ON b.banzhurenTeacherID = banzhuren.id\n" +
            "WHERE\n" +
            "\ta.role = 1 \n" +
            "\tAND c.isOpen !=2 " + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<wchatmessageVO> GetStuWechatMessagePages(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 查询所有的下拉选项
     *
     * @return
     */
    @Select("<script>" + "SELECT id AS id,tuisongType AS name FROM pxtuisongtypetable" + "</script>")
    List<searchVO> getTuisongTypeList();

    /**
     * 查询导出的推送信息
     *
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT\n" +
            "\td.campusName,b.zidingyiStuID,b.stuName,e.staffName,c.tuisongType,a.note,a.addTime\n" +
            "FROM\n" +
            "\tpxtuisongtable AS a\n" +
            "\tLEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "\tLEFT JOIN pxtuisongtypetable AS c ON a.tuisongTypeName = c.id \n" +
            "\tLEFT JOIN pxcampustable AS d ON b.campusID = d.id\n" +
            "\tLEFT JOIN pxstafftable AS e ON b.banzhurenTeacherID = e.id\n" +
            "WHERE\n" +
            "\ta.role = 1 \n" +
            "\tAND d.isOpen!=2" + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<exporttuisongVO> SearchTuisongMessageList(@Param("ew") Wrapper wrapper);

    /**
     * 加载所有班级（按班级推送消息选择下拉）
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.id,a.className,b.id AS campusID,b.campusName FROM pxclasstable AS a " +
            "LEFT JOIN pxcampustable AS b ON a.campusID = b.id WHERE a.isShow =TRUE AND a.isdelete = FALSE AND b.isOpen!=2" + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<classInfoListVO> GetAllClassListInfo(@Param("ew") Wrapper wrapper);
}