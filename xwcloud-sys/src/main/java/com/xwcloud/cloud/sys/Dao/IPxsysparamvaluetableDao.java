package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxsysparamvaluetable;
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
 * @since 2020-12-30
 */
@Repository
public interface IPxsysparamvaluetableDao extends BaseMapper<Pxsysparamvaluetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "sysparamTypeID", property = "sysparamtypeid"),
            @Result(column = "modifyValue", property = "modifyvalue"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  pxsysparamvaluetable"
            + "</script>")
    List<Pxsysparamvaluetable> getAllList();

    @Select("<script>"+"SELECT * FROM pxsysparamvaluetable WHERE sysparamTypeID = #{ValueID} AND qiyeID =#{qiyeID} "+"</script>")
    Pxsysparamvaluetable GetPxsysparamvalueByQiyeIDAndValueID(Long qiyeID,Long ValueID);


}