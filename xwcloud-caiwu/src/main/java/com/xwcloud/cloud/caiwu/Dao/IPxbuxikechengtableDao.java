package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
@Repository
public interface IPxbuxikechengtableDao extends BaseMapper<Pxbuxikechengtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "originalprice", property = "originalprice"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "remainkeshi", property = "remainkeshi"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "zongjia", property = "zongjia"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "buykeshiDateTime", property = "buykeshiDateTime"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "jifeiStyleID", property = "jifeiStyleID"),
            @Result(column = "type", property = "type"),
            @Result(column = "qianDanSubjectID", property = "qianDanSubjectID"),
            @Result(column = "qianDanInfoID", property = "qianDanInfoID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbuxikechengtable "
            + "</script>")
    List<Pxbuxikechengtable> getAllList();
}