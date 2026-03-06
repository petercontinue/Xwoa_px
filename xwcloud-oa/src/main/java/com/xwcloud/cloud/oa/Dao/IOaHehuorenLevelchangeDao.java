package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.OA.OaHehuorenLevelchange;
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
public interface IOaHehuorenLevelchangeDao extends BaseMapper<OaHehuorenLevelchange> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "oldLevelID", property = "oldLevelID"),
                @Result(column = "newLevelID", property = "newLevelID"),
                @Result(column = "levelChangeReason", property = "levelChangeReason"),
                @Result(column = "changeTime", property = "changeTime"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  oa_hehuoren_levelchange"
            + "</script>")
    List<OaHehuorenLevelchange> getAllList();
}