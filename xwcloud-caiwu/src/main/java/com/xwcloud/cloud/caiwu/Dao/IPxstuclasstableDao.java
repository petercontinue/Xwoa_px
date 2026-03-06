package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;
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
public interface IPxstuclasstableDao extends BaseMapper<Pxstuclasstable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buxiID", property = "buxiID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstuclasstable "
            + "</script>")
    List<Pxstuclasstable> getAllList();
}