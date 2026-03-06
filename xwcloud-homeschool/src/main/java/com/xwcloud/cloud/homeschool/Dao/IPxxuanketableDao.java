package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxxuanketable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-28
 */
@Repository
public interface IPxxuanketableDao extends BaseMapper<Pxxuanketable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "paikeID", property = "paikeid"),
            @Result(column = "recordDate", property = "recorddate"),
            @Result(column = "stuID", property = "stuid"),
            @Result(column = "type", property = "type"),
            @Result(column = "buxiID", property = "buxiid"),
            @Result(column = "qiyeID", property = "qiyeid"),
})
@Select("<script>" +
        "SELECT * from  pxxuanketable"
        + "</script>")
List<Pxxuanketable> getAllList();
}