package com.xwcloud.cloud.wsc.Dao;


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
 * @since 2021-05-31
 */
@Repository
public interface IPxxuanketableDao extends BaseMapper<Pxxuanketable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "paikeID", property = "paikeID"),
            @Result(column = "recordDate", property = "recordDate"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "type", property = "type"),
            @Result(column = "buxiID", property = "buxiID"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  pxxuanketable"
        + "</script>")
List<Pxxuanketable> getAllList();
}