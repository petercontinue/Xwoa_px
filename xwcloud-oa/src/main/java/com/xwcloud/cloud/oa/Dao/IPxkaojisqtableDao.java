package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkaojisqtable;
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
 * @since 2021-08-25
 */
@Repository
public interface IPxkaojisqtableDao extends BaseMapper<Pxkaojisqtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuid", property = "stuid"),
                @Result(column = "kemuid", property = "kemuid"),
                @Result(column = "sqjibie", property = "sqjibie"),
                @Result(column = "shjibie", property = "shjibie"),
                @Result(column = "lururen", property = "lururen"),
                @Result(column = "addDate", property = "addDate"),
                @Result(column = "shenheren", property = "shenheren"),
                @Result(column = "shenheDate", property = "shenheDate"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkaojisqtable"
            + "</script>")
    List<Pxkaojisqtable> getAllList();
}