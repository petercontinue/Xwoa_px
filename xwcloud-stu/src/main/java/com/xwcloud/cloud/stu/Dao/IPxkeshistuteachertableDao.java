package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkeshistuteachertable;
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
public interface IPxkeshistuteachertableDao extends BaseMapper<Pxkeshistuteachertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "keshiStuTableID", property = "keshiStuTableID"),
            @Result(column = "teacherID", property = "teacherID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshistuteachertable"
            + "</script>")
    List<Pxkeshistuteachertable> getAllList();

    //按照学员课耗ID获取
    @Select("<script>" + "select * from pxkeshistuteachertable where keshiStuTableID=#{keshiStuTableID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxkeshistuteachertable> getksStuTeachs(Long keshiStuTableID, Long qiyeID);

}