package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxgonggaojiazhangtable;
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
public interface IPxgonggaojiazhangtableDao extends BaseMapper<Pxgonggaojiazhangtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "bianLiangName", property = "bianLiangName"),
                @Result(column = "modifyValue", property = "modifyValue"),
                @Result(column = "ParameterContent", property = "parameterContent"),
                @Result(column = "type", property = "type"),
                @Result(column = "tianjiastaff", property = "tianjiastaff"),
                @Result(column = "tianjiashijian", property = "tianjiashijian"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxgonggaojiazhangtable"
            + "</script>")
    List<Pxgonggaojiazhangtable> getAllList();
}