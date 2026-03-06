package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Birthdaydianzang;
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
 * @since 2021-05-18
 */
@Repository
public interface IBirthdaydianzangDao extends BaseMapper<Birthdaydianzang> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "dianzanUserID", property = "dianzanUserID"),
            @Result(column = "beidianzanUserID", property = "beidianzanUserID"),
            @Result(column = "dianzanDatetime", property = "dianzanDatetime"),
            @Result(column = "type", property = "type"),
    })
    @Select("<script>" +
            "SELECT * from  birthdaydianzang"
            + "</script>")
    List<Birthdaydianzang> getAllList();


}