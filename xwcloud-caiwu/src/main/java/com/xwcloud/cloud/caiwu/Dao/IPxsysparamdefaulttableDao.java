package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxsysparamdefaulttable;
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
 * @since 2021-04-08
 */
@Repository
public interface IPxsysparamdefaulttableDao extends BaseMapper<Pxsysparamdefaulttable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "bianLiangName", property = "bianLiangName"),
            @Result(column = "defaultValue", property = "defaultValue"),
            @Result(column = "ParameterContent", property = "parameterContent"),
            @Result(column = "type", property = "type"),
            @Result(column = "paixu", property = "paixu"),
})
@Select("<script>" +
        "SELECT * from  pxsysparamdefaulttable"
        + "</script>")
List<Pxsysparamdefaulttable> getAllList();
}