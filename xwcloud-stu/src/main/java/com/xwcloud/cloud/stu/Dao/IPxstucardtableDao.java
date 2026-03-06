package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstucardtable;
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
 * @since 2020-11-19
 */
@Repository
public interface IPxstucardtableDao extends BaseMapper<Pxstucardtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "cardNumber", property = "cardNumber"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstucardtable"
            + "</script>")
    List<Pxstucardtable> getAllList();

    //学员卡号去重
    @Select("<script>" + "select * from pxstucardtable where stuID &lt;&gt; #{stuID} and cardNumber=#{cardNumber} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxstucardtable> getCard(Long stuID, String cardNumber, Long qiyeID);

    @Select("<script>" +
            "SELECT * from  pxstucardtable " +
            "WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxstucardtable> getcfCard(@Param("ew") QueryWrapper queryWrapper);

    //按照学员ID获取学员卡记录
    @Select("<script>" + "select * from pxstucardtable where stuID =#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxstucardtable> addUpdateCard(Long stuID, Long qiyeID);

}