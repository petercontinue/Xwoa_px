package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkaojitable;

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
public interface IPxkaojitableDao extends BaseMapper<Pxkaojitable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuid", property = "stuid"),
            @Result(column = "kemuid", property = "kemuid"),
            @Result(column = "jibie", property = "jibie"),
            @Result(column = "time", property = "time"),
            @Result(column = "addsatff", property = "addsatff"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkaojitable"
            + "</script>")
    List<Pxkaojitable> getAllList();

    //按照学员ID、科目ID获取
    @Select("<script>" +
            "SELECT * from  pxkaojitable where stuid =#{stuid} and kemuid=#{kemuid} and qiyeID=#{qiyeID}"
            + "</script>")
    List<Pxkaojitable> getStuASub(Long stuid, Long kemuid, Long qiyeID);
}