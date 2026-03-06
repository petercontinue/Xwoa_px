package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstuparamtypetable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-24
 */
public interface IPxstuparamtypetableDao extends BaseMapper<Pxstuparamtypetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuParamTypeName", property = "stuParamTypeName"),
            @Result(column = "IsBiTian", property = "IsBiTian"),
            @Result(column = "widthType", property = "widthType"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstuparamtypetable"
            + "</script>")
    List<Pxstuparamtypetable> getAllList();
}