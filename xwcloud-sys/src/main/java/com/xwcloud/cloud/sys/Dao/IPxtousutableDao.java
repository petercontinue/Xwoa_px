package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxtousutable;
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
 * @since 2021-07-29
 */
@Repository
public interface IPxtousutableDao extends BaseMapper<Pxtousutable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "openid", property = "openid"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "tousuContent", property = "tousuContent"),
                @Result(column = "tousuDate", property = "tousuDate"),
                @Result(column = "chayueState", property = "chayueState"),
                @Result(column = "chayueDate", property = "chayueDate"),
                @Result(column = "chayueSatff", property = "chayueSatff"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtousutable"
            + "</script>")
    List<Pxtousutable> getAllList();
}