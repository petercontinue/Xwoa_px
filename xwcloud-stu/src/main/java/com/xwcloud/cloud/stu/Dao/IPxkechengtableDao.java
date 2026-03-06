package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
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
public interface IPxkechengtableDao extends BaseMapper<Pxkechengtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "kechengName", property = "kechengName"),
            @Result(column = "subjectID", property = "subjectID"),
            @Result(column = "buxiStyleID", property = "buxiStyleID"),
            @Result(column = "is1v1KC", property = "is1v1KC"),
            @Result(column = "classTimeStyleID", property = "classTimeStyleID"),
            @Result(column = "kechengOriginalPrice", property = "kechengOriginalPrice"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "buyZonjia", property = "buyZonjia"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "ZSid", property = "ZSid"),
            @Result(column = "jifeiStyleID", property = "jifeiStyleID"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "bgColor", property = "bgColor"),
            @Result(column = "perdaysqj", property = "perdaysqj"),
            @Result(column = "perkeshiqj", property = "perkeshiqj"),
            @Result(column = "qingjiaTimes", property = "qingjiaTimes"),
    })
    @Select("<script>" +
            "SELECT * from  pxkechengtable"
            + "</script>")
    List<Pxkechengtable> getAllList();

    @Select("<script>" +
            "SELECT * from  Pxkechengtable where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxkechengtable> selectkc(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "select * from pxkechengtable where qiyeID=#{qiyeID}  limit 1"
            + "</script>")
    Pxkechengtable getBysubject(Long qiyeID);

    @Select("<script>" +
            "SELECT id id, kechengName name from  pxkechengtable where subjectID=#{subjectID} and qiyeID=#{qiyeID}"
            + "</script>")
    List<listVo> getKcBySubject(Long subjectID, Long qiyeID);

    //按照证书ID获取
    @Select("<script>" + "select * from pxkechengtable where ZSid=#{ZSid} and qiyeID=#{qiyeID}" + "</script>")
    Page<Pxkechengtable> getZSxq(Long ZSid, Long qiyeID,Page page);
}