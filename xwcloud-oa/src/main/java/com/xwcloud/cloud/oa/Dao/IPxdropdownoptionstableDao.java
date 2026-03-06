package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxdropdownoptionstable;
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
public interface IPxdropdownoptionstableDao extends BaseMapper<Pxdropdownoptionstable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuParamTypeId", property = "stuParamTypeId"),
                @Result(column = "DropDownOptions", property = "dropDownOptions"),
                @Result(column = "isShow", property = "isShow"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxdropdownoptionstable"
            + "</script>")
    List<Pxdropdownoptionstable> getAllList();
}