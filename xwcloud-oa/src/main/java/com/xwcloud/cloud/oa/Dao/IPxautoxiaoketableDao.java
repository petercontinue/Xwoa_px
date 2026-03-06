package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxautoxiaoketable;
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
public interface IPxautoxiaoketableDao extends BaseMapper<Pxautoxiaoketable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "buxiID", property = "buxiID"),
                @Result(column = "classID", property = "classID"),
                @Result(column = "keshiNum", property = "keshiNum"),
                @Result(column = "teaIDs", property = "teaIDs"),
                @Result(column = "teaNames", property = "teaNames"),
                @Result(column = "state", property = "state"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxautoxiaoketable"
            + "</script>")
    List<Pxautoxiaoketable> getAllList();
}