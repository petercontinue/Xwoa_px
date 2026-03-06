package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxjieke;
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
public interface IPxjiekeDao extends BaseMapper<Pxjieke> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "jkaddUser", property = "jkaddUser"),
                @Result(column = "jiekeTime", property = "jiekeTime"),
                @Result(column = "jkshuoming", property = "jkshuoming"),
                @Result(column = "jiekeyue", property = "jiekeyue"),
                @Result(column = "jiekeremainxf", property = "jiekeremainxf"),
                @Result(column = "fkaddUser", property = "fkaddUser"),
                @Result(column = "fukeTime", property = "fukeTime"),
                @Result(column = "fkshuoming", property = "fkshuoming"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxjieke"
            + "</script>")
    List<Pxjieke> getAllList();
}