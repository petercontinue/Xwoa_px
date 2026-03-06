package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.getclassVo;
import com.xwcloud.cloud.model.Vo.haveTimeTeaVo;
import com.xwcloud.cloud.model.entity.Pxstafftable;

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
 * @since 2020-12-11
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
    })
    @Select("<script>" +
            "SELECT * from  pxstafftable"
            + "</script>")
    List<Pxstafftable> getAllList();


    /**
     * 获取员工
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.staffName staffName,b.staffpostName staffpostName,c.id campusID,c.campusName campusName\n" +
            "from pxstafftable a \n" +
            "LEFT JOIN pxstaffposttable b on a.staffPostID=b.id \n" +
            "LEFT JOIN pxcampustable c on b.campusID =c.id\n" +
            "WHERE c.isOpen!=2 and a.staffState=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<haveTimeTeaVo> getstaff(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 按校区获取员工
     * @param campusID
     * @return
     */
    @Select("<script>" +
            "SELECT id id,staffName name from  pxstafftable where campusID=#{campusID} and qiyeID=#{qiyeID}"
            + "</script>")
    List<getclassVo>getstaffBycam(Long campusID, Long qiyeID);


    /**
     * 获取所有老师信息
     */
    @Select("<script>" +
            "SELECT id id,staffName name from  pxstafftable where staffState=1 and qiyeID=#{qiyeID}"
            + "</script>")
    List<getclassVo>getallstaff(Long qiyeID);
}