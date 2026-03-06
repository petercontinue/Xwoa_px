package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabuprice;
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
public interface IPxyueketeacherfabupriceDao extends BaseMapper<Pxyueketeacherfabuprice> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "yuekeID", property = "yuekeID"),
                @Result(column = "yuekerenshu", property = "yuekerenshu"),
                @Result(column = "yuekeprice", property = "yuekeprice"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyueketeacherfabuprice"
            + "</script>")
    List<Pxyueketeacherfabuprice> getAllList();
}