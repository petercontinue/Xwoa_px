package com.xwcloud.cloud.oauth.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstafftable;
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
 * @since 2021-05-11
 */
@Repository
public interface IPxstafftableDao extends BaseMapper<Pxstafftable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffName", property = "staffName"),
            @Result(column = "staffTel", property = "staffTel"),
            @Result(column = "password", property = "password"),
            @Result(column = "staffSex", property = "staffSex"),
            @Result(column = "staffBirthday", property = "staffBirthday"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "staffPostID", property = "staffPostID"),
            @Result(column = "staffState", property = "staffState"),
            @Result(column = "photo", property = "photo"),
            @Result(column = "QQ", property = "qq"),
            @Result(column = "email", property = "email"),
            @Result(column = "wx", property = "wx"),
            @Result(column = "douyin", property = "douyin"),
            @Result(column = "joinTime", property = "joinTime"),
            @Result(column = "shuoMing", property = "shuoMing"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "unionid", property = "unionid"),
            @Result(column = "phoneMac", property = "phoneMac"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "showInApp", property = "showInApp"),
    })
    @Select("<script>" +
            "SELECT * from  pxstafftable"
            + "</script>")
    List<Pxstafftable> getAllList();

    @Select("<script>" +
            "select a.staffName,a.QQ,a.email,a.wx,a.photo,a.staffTel,a.staffSex,a.joinTime,a.shuoMing,b.staffpostName,c.khShowJigouName,d.campusName \n" +
            "from pxstafftable a\n" +
            "LEFT JOIN pxstaffposttable b on a.staffPostID=b.id\n" +
            "LEFT JOIN oa_kehu c on a.qiyeID=c.id\n" +
            "LEFT JOIN pxcampustable d on a.campusID=d.id" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<HashMap<String, Object>> GetStaffInfo(@Param("ew") QueryWrapper wrapper);

    @Select("<script>" +
            "SELECT\n" +
            "\ta.id,\n" +
            "\ta.nickName,\n" +
            "\ta.sex,\n" +
            "\ta.headImage,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\t( CASE WHEN c.id IS NOT NULL THEN c.id ELSE '' END ) \n" +
            "\tFROM\n" +
            "\t\tpxstafftable c \n" +
            "\tWHERE\n" +
            "\t\ta.phoneNumber = c.staffTel and c.qiyeID=a.qiyeID \n" +
            "\t) staffID,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\t( CASE WHEN COUNT( d.id ) IS NOT NULL THEN COUNT( d.id ) ELSE 0 END ) \n" +
            "\tFROM\n" +
            "\t\twsc_dongtaiinfo d\n" +
            "\t\tJOIN dongtai_dianzang e ON d.id = e.dongtaiID \n" +
            "\tWHERE\n" +
            "\t\td.wscuserID = a.id  and d.isShow=0\n" +
            "\t) dznum, \n" +
            "\t(SELECT ( CASE WHEN COUNT(k.id) IS NOT NULL THEN COUNT(k.id) ELSE 0 END ) from wsc_userguanzhu k where k.beiguanzhuUserID=a.id) fsnum "+
            "FROM\n" +
            "\twsc_user a " +
            "where 1=1\n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<HashMap<String, Object>> GetgerenStaffInfo(@Param("ew") QueryWrapper wrapper);

    @Select("<script>" +
            "SELECT\n" +
            "\tb.id,\n" +
            "\tb.nickName,\n" +
            "\tb.sex,\n" +
            "\tb.userType,\n" +
            "\tb.headImage,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\t( CASE WHEN d.id IS NOT NULL THEN d.id ELSE '' END ) \n" +
            "\tFROM\n" +
            "\t\tpxstafftable d \n" +
            "\tWHERE\n" +
            "\t\tb.phoneNumber = d.staffTel and d.qiyeID=a.qiyeID \n" +
            "\t) staffID,\n" +
            "\tsum((\n" +
            "\t\tSELECT\n" +
            "\t\t\t( CASE WHEN COUNT( c.id ) IS NOT NULL THEN COUNT( c.id ) ELSE 0 END ) \n" +
            "\t\tFROM\n" +
            "\t\t\tdongtai_dianzang c \n" +
            "\t\tWHERE\n" +
            "\t\t\tc.dongtaiID = a.id \n" +
            "\t\t)) dznum, \n" +
            "\t(SELECT ( CASE WHEN COUNT(k.id) IS NOT NULL THEN COUNT(k.id) ELSE 0 END ) from wsc_userguanzhu k where k.beiguanzhuUserID=b.id) fsnum "+
            "FROM\n" +
            "\twsc_dongtaiinfo a\n" +
            "\tLEFT JOIN wsc_user b ON a.wscuserID = b.id \n" +
            "WHERE\n" +
            "\ta.isShow = 0 \n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +

            "</script>")
    List<HashMap<String, Object>> GetgerenStaffInfotoIndexpage(@Param("ew") QueryWrapper wrapper);

    @Select("<script>" +
            "SELECT b.id,b.nickName,b.sex,b.userType, a.yueduTimes,b.headImage, a.Addtime,a.iszhiding,a.dongtaiTitle,a.dongtaiContent,a.id AS dongtaiID,  \n" +
            "(SELECT ( CASE WHEN COUNT(c.id) IS NOT NULL THEN COUNT(c.id) ELSE 0 END ) from dongtai_dianzang c where c.dongtaiID=a.id) dznum,\n" +
            "(SELECT ( CASE WHEN COUNT(c.id) IS NOT NULL THEN COUNT(c.id) ELSE 0 END ) from dongtai_pinglun c where c.dongtaiID=a.id) plnum\n" +
            "from wsc_dongtaiinfo a\n" +
            "LEFT JOIN wsc_user b on a.wscuserID=b.id\n" +
            "where a.isShow=0 \n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
//            "GROUP BY\n" +
//            "\ta.wscuserID "+
            "</script>")
    List<HashMap<String, Object>> GetgerenDongtaiInfo(@Param("ew") QueryWrapper wrapper);


    @Select("<script>"+
            "SELECT *\n" +
            "from pxstafftable a \n" +
            "LEFT JOIN wsc_user b on a.staffTel=b.phoneNumber and  a.qiyeID=b.qiyeID\n" +
            "join pxcampustable c on a.campusID=c.id\n" +
            "JOIN pxstaffposttable d on a.staffPostID=d.id "+
            " where c.isOpen !=2 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            +"</script>")
    List<HashMap<String,Object>> getappteaInfo(@Param("ew") QueryWrapper wrapper);

}