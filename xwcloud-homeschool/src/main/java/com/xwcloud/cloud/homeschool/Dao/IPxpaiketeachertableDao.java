package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxpaiketeachertable;
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
public interface IPxpaiketeachertableDao extends BaseMapper<Pxpaiketeachertable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "paikeID", property = "paikeid"),
            @Result(column = "teacherID", property = "teacherid"),
            @Result(column = "qiyeID", property = "qiyeid"),
})
@Select("<script>" +
        "SELECT * from  pxpaiketeachertable"
        + "</script>")
List<Pxpaiketeachertable> getAllList();
}