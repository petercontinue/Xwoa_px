package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxshouzhistyletable;
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
 * @since 2021-08-16
 */
@Repository
public interface IPxshouzhistyletableDao extends BaseMapper<Pxshouzhistyletable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "shouzhiStyle", property = "shouzhiStyle"),
                @Result(column = "isshouOrzhichu", property = "isshouOrzhichu"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "staffID", property = "staffID"),
                @Result(column = "lurudate", property = "lurudate"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxshouzhistyletable"
            + "</script>")
    List<Pxshouzhistyletable> getAllList();
}