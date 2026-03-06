package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.yaoyueVo;
import com.xwcloud.cloud.model.Vo.yaoyuedaofangVo;
import com.xwcloud.cloud.model.entity.Pxyxinvitationtable;
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
 * @since 2020-11-24
 */
@Repository
public interface IPxyxinvitationtableDao extends BaseMapper<Pxyxinvitationtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "invitationTime", property = "invitationTime"),
            @Result(column = "invitationZhuangtai", property = "invitationZhuangtai"),
            @Result(column = "addTeacher", property = "addTeacher"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "shuoMing", property = "shuoMing"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxinvitationtable"
            + "</script>")
    List<Pxyxinvitationtable> getAllList();


    /**
     * 通过学生ID查询邀约记录
     * @param stuID
     * @return
     */
    @Select("<script>" + "SELECT * FROM pxyxinvitationtable WHERE stuID=#{stuID}" + "</script>")
    List<Pxyxinvitationtable> GetInvitationListByStuID(long stuID);

    //查询学生对应的邀约记录
    @Select("<script>" +
            "SELECT a.id,c.stuName,c.stuSex,e.stuGradeName,d.campusName,a.invitationTime,a.invitationZhuangtai,a.shuoMing,f.daofangDatetime,a.stuID stuID \n" +
            "FROM pxyxinvitationtable AS a \n" +
            "LEFT JOIN pxstafftable AS b ON a.addTeacher = b.id\n" +
            "LEFT JOIN pxstutable AS c ON a.stuID = c.id\n" +
            "LEFT JOIN pxcampustable AS d ON c.campusID=d.id\n" +
            "LEFT JOIN pxstugradetable AS e ON c.stuGradeID = e.id\n" +
            "LEFT JOIN pxyxinvitedaofangtable f ON a.id = f.inviteID\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"
            + "</script>")
    Page<yaoyueVo> GetinvitationByStuIDPages(Page<yaoyueVo> page, @Param("ew") QueryWrapper<yaoyueVo> wrapper);

    /**
     * 分页查询到访信息
     */
    @Select("<script>" +
            "SELECT c.campusName,b.stuName,a.invitationTime,a.invitationZhuangtai,d.staffName,a.shuoMing,\n" +
            "(SELECT adddaofanftime FROM pxyxinvitedaofangtable WHERE inviteID =a.id) AS daofangDate,\n" +
            "(SELECT k.staffName FROM pxyxinvitedaofangtable AS t LEFT JOIN pxstafftable AS k ON t.adddanfangren = k.id WHERE inviteID =a.id) AS daofangStaffName \n" +
            "FROM pxyxinvitationtable AS a\n" +
            "LEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID = c.id\n" +
            "LEFT JOIN pxstafftable AS d ON a.addTeacher = d.id" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<yaoyuedaofangVo> GetyaoyueDaofangPages(Page page, @Param("ew") Wrapper wrapper);

    //1：已邀约 2：爽约  3已到访
    @Select("<script>" +
            "SELECT c.campusName,b.stuName,a.invitationTime,ifnull(d.staffName,'') staffName,ifnull(a.shuoMing,'') shuoMing," +
            "(case when a.invitationZhuangtai=1 then '已邀约' when a.invitationZhuangtai=2 then '爽约' when a.invitationZhuangtai=3 then '已到访' end) invitationZhuangtai," +
            "(SELECT adddaofanftime FROM pxyxinvitedaofangtable WHERE inviteID =a.id) AS daofangDate,\n" +
            "ifnull((SELECT k.staffName FROM pxyxinvitedaofangtable AS t LEFT JOIN pxstafftable AS k ON t.adddanfangren = k.id WHERE inviteID =a.id),'') daofangStaffName \n" +
            "FROM pxyxinvitationtable AS a\n" +
            "LEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID = c.id\n" +
            "LEFT JOIN pxstafftable AS d ON a.addTeacher = d.id" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<yaoyuedaofangVo> getyaoyuedaofangList(@Param("ew") Wrapper wrapper);
}