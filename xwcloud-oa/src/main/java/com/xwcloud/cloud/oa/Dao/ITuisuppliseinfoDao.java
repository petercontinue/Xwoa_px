package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Tuisuppliseinfo;
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
public interface ITuisuppliseinfoDao extends BaseMapper<Tuisuppliseinfo> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "qiadansuppliesTabID", property = "qiadansuppliesTabID"),
                @Result(column = "tuisuppliseID", property = "tuisuppliseID"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "tuiguige", property = "tuiguige"),
                @Result(column = "adduser", property = "adduser"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "tuifeispID", property = "tuifeispID"),
                @Result(column = "tuimoney", property = "tuimoney"),
                @Result(column = "tuinum", property = "tuinum"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  tuisuppliseinfo"
            + "</script>")
    List<Tuisuppliseinfo> getAllList();
}