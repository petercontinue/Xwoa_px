package com.xwcloud.cloud.stu.Dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxdropdownoptionstable;

import com.xwcloud.cloud.model.Vo.listVo;
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
 * @since 2021-03-08
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

    @Select("<script>" +
            "SELECT id id ,DropDownOptions name from  pxdropdownoptionstable where stuParamTypeId=#{stuParamTypeId} and qiyeID =#{qiyeID} and isShow=1 "
            + "</script>")
    List<listVo> getparamTypeList(Long stuParamTypeId , Long qiyeID);


}