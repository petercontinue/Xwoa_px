package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstaffposttable;
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
 * @since 2021-06-10
 */
@Repository
public interface IPxstaffposttableDao extends BaseMapper<Pxstaffposttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffpostName", property = "staffpostName"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstaffposttable "
            + "</script>")
    List<Pxstaffposttable> getAllList();
}