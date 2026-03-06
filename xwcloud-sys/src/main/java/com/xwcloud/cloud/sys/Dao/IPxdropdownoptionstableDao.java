package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxdropdownoptionstable;
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
 * @since 2020-11-05
 */
public interface IPxdropdownoptionstableDao extends BaseMapper<Pxdropdownoptionstable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuParamTypeId", property = "stuParamTypeId"),
            @Result(column = "DropDownOptions", property = "DropDownOptions"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxdropdownoptionstable"
            + "</script>")
    List<Pxdropdownoptionstable> getAllList();

    /**
     * 查询下拉属性对应的下拉选择项
     * @param stuParamTypeId
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxdropdownoptionstable WHERE stuParamTypeId=#{stuParamTypeId} "+"</script>")
    List<Pxdropdownoptionstable> GetOptionsById(String stuParamTypeId);
}