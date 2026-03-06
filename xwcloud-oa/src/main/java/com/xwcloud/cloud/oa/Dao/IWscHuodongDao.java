package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscHuodong;
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
public interface IWscHuodongDao extends BaseMapper<WscHuodong> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "huodongName", property = "huodongName"),
                @Result(column = "isShow", property = "isShow"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_huodong"
            + "</script>")
    List<WscHuodong> getAllList();
}