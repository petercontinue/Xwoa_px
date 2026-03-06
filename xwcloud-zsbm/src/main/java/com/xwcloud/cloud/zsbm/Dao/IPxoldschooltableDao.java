package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxoldschooltable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxoldschooltableDao extends BaseMapper<Pxoldschooltable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "oldSchoolID", property = "oldSchoolID"),
            @Result(column = "oldSchoolName", property = "oldSchoolName"),
    })
    @Select("<script>" +
            "SELECT * from  pxoldschooltable"
            + "</script>")
    List<Pxoldschooltable> getAllList();

    @Select("<script>"+"SELECT * FROM pxoldschooltable WHERE oldSchoolName=#{oldSchoolName}"+"</script>")
    Pxoldschooltable GetOldSchoolByName(String oldSchoolName);
}