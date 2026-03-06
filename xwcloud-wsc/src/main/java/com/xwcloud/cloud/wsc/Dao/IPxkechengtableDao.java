package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
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
 * @since 2021-05-31
 */
@Repository
public interface IPxkechengtableDao extends BaseMapper<Pxkechengtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "kechengName", property = "kechengName"),
            @Result(column = "subjectID", property = "subjectID"),
            @Result(column = "buxiStyleID", property = "buxiStyleID"),
            @Result(column = "is1v1KC", property = "is1v1KC"),
            @Result(column = "is1v1Tongke", property = "is1v1Tongke"),
            @Result(column = "classTimeStyleID", property = "classTimeStyleID"),
            @Result(column = "kechengOriginalPrice", property = "kechengOriginalPrice"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "buyZonjia", property = "buyZonjia"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "ZSid", property = "zSid"),
            @Result(column = "jifeiStyleID", property = "jifeiStyleID"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "bgColor", property = "bgColor"),
            @Result(column = "perdaysqj", property = "perdaysqj"),
            @Result(column = "perkeshiqj", property = "perkeshiqj"),
            @Result(column = "qingjiaTimes", property = "qingjiaTimes"),
            @Result(column = "iskoukeshi", property = "iskoukeshi"),
            @Result(column = "kechengImg", property = "kechengImg"),
            @Result(column = "kechengbeizhu", property = "kechengbeizhu"),
            @Result(column = "kechengcontent", property = "kechengcontent"),
            @Result(column = "showInApp", property = "showInApp"),
            @Result(column = "shuakaTime", property = "shuakaTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxkechengtable"
            + "</script>")
    List<Pxkechengtable> getAllList();
}