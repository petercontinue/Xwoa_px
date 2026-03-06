package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabustutable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxyueketeacherfabustutableDao extends BaseMapper<Pxyueketeacherfabustutable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "wxUserID", property = "wxUserID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "stuName", property = "stuName"),
            @Result(column = "telphone", property = "telphone"),
            @Result(column = "buxiID", property = "buxiID"),
            @Result(column = "yuekeTeachFabuID", property = "yuekeTeachFabuID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  pxyueketeacherfabustutable"
        + "</script>")
List<Pxyueketeacherfabustutable> getAllList();
}