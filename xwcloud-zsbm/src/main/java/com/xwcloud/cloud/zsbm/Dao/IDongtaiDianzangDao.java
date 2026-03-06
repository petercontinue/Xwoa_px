package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.DongtaiDianzang;
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
 * @since 2021-08-06
 */
@Repository
public interface IDongtaiDianzangDao extends BaseMapper<DongtaiDianzang> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "dongtaiID", property = "dongtaiID"),
                @Result(column = "dianzanUserID", property = "dianzanUserID"),
                @Result(column = "dianzangDatetime", property = "dianzangDatetime"),
    })
    @Select("<script>" +
            "SELECT * from  dongtai_dianzang"
            + "</script>")
    List<DongtaiDianzang> getAllList();
}