package com.xwcloud.cloud.wsc.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Minganwords;
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
 * @since 2021-08-06
 */
@Repository
public interface IMinganwordsDao extends BaseMapper<Minganwords> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "word", property = "word"),
                @Result(column = "isYouxiao", property = "isYouxiao"),
    })
    @Select("<script>" +
            "SELECT * from  minganwords"
            + "</script>")
    List<Minganwords> getAllList();
}