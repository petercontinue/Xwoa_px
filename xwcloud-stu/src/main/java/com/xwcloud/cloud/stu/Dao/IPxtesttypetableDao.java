package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxtesttypetable;

import com.xwcloud.cloud.model.Vo.listVo;
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
 * @since 2021-02-22
 */
@Repository
public interface IPxtesttypetableDao extends BaseMapper<Pxtesttypetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "testType", property = "testType"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtesttypetable"
            + "</script>")
    List<Pxtesttypetable> getAllList();

    @Select("<script>" +
            "SELECT * from  Pxtesttypetable where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxtesttypetable> selecttesttype(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "select id,testType name  from pxtesttypetable where qiyeID=#{qiyeID} "
            + "</script>")
    List<listVo> getTesttype(Long qiyeID);

    @Select("<script>" +
            "SELECT * from pxtesttypetable where qiyeID=#{qiyeID} limit 1"
            + "</script>")
    Pxtesttypetable getOnetest(Long qiyeID);

}