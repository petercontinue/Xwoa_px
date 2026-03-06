package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkeshizengsongtable;

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
 * @since 2020-11-23
 */
@Repository
public interface IPxkeshizengsongtableDao extends BaseMapper<Pxkeshizengsongtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengPrice", property = "kechengPrice"),
            @Result(column = "keshiShu", property = "keshiShu"),
            @Result(column = "caozuoStaffId", property = "caozuoStaffId"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "songYangyin", property = "songYangyin"),
            @Result(column = "JifeiStyle", property = "JifeiStyle"),
            @Result(column = "qiandanInfoID", property = "qiandanInfoID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshizengsongtable"
            + "</script>")
    List<Pxkeshizengsongtable> getAllList();

    //获取学员的赠送课时
    @Select("<script>" + "SELECT * from  pxkeshizengsongtable where stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxkeshizengsongtable> getksZs(Long stuID, Long qiyeID);
}