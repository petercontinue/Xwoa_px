package com.xwcloud.cloud.sys.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxdaibantable;
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
 * @since 2021-07-29
 */
@Repository
public interface IPxdaibantableDao extends BaseMapper<Pxdaibantable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "daibanItemName", property = "daibanItemName"),
            @Result(column = "daibanShuoming", property = "daibanShuoming"),
            @Result(column = "daibanTypeID", property = "daibanTypeID"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "menuID", property = "menuID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxdaibantable"
            + "</script>")
    List<Pxdaibantable> getAllList();
}