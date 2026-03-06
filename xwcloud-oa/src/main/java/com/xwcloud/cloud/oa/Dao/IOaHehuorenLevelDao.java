package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.OA.OaHehuorenLevel;
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
 * @since 2021-07-02
 */
@Repository
public interface IOaHehuorenLevelDao extends BaseMapper<OaHehuorenLevel> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "hehuoLevel", property = "hehuoLevel"),
                @Result(column = "fangyongbili", property = "fangyongbili"),
                @Result(column = "shuoming", property = "shuoming"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  oa_hehuoren_level"
            + "</script>")
    List<OaHehuorenLevel> getAllList();
}