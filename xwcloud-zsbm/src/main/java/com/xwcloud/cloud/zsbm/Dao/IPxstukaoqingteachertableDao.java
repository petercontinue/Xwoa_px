package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstukaoqingteachertable;
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
 * @since 2020-11-18
 */
@Repository
public interface IPxstukaoqingteachertableDao extends BaseMapper<Pxstukaoqingteachertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stukaoqingTabID", property = "stukaoqingTabID"),
            @Result(column = "teacherID", property = "teacherID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstukaoqingteachertable"
            + "</script>")
    List<Pxstukaoqingteachertable> getAllList();

    @Select("<script>"+"DELETE FROM pxstukaoqingteachertable where stukaoqingTabID=#{stukaoqingTabID}"+"</script>")
    int removeBystukaoqingTabID(Long stukaoqingTabID);
}