package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.daofangVo;
import com.xwcloud.cloud.model.entity.Pxyxinvitedaofangtable;
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
public interface IPxyxinvitedaofangtableDao extends BaseMapper<Pxyxinvitedaofangtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "inviteID", property = "inviteID"),
            @Result(column = "daofangDatetime", property = "daofangDatetime"),
            @Result(column = "adddaofanftime", property = "adddaofanftime"),
            @Result(column = "adddanfangren", property = "adddanfangren"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxinvitedaofangtable"
            + "</script>")
    List<Pxyxinvitedaofangtable> getAllList();


    @Select("<script>" +
            "SELECT a.id,c.stuName,c.stuSex,e.stuGradeName,d.campusName,a.daofangDatetime,a.daofangtext,t.invitationTime,t.invitationZhuangtai,t.id invitationID,\n" +
            "c.stuGradeID,c.campusID campusID " +
            "FROM pxyxinvitationtable AS t\n" +
            "LEFT JOIN pxyxinvitedaofangtable AS a ON a.inviteID = t.id\n" +
//            "LEFT JOIN pxstafftable AS b ON a.adddanfangren = b.id\n" +
            "LEFT JOIN pxstutable AS c ON t.stuID = c.id\n" +
            "LEFT JOIN pxcampustable AS d ON c.campusID=d.id\n" +
            "LEFT JOIN pxstugradetable AS e ON c.stuGradeID = e.id " +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<daofangVo> GetinvitationDaofangByStuIDPages(Page<daofangVo> page, @Param("ew") QueryWrapper<daofangVo> wrapper);
}