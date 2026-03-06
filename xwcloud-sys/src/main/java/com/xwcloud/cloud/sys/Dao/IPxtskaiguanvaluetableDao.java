package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxtskaiguanvaluetable;
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
 * @since 2021-01-08
 */
@Repository
public interface IPxtskaiguanvaluetableDao extends BaseMapper<Pxtskaiguanvaluetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "TSTypeID", property = "tstypeid"),
            @Result(column = "value", property = "value"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  pxtskaiguanvaluetable"
            + "</script>")
    List<Pxtskaiguanvaluetable> getAllList();

    @Select("<script>"+"SELECT * FROM pxtskaiguanvaluetable WHERE TSTypeID = #{TSTypeID} AND qiyeID = #{qiyeID} LIMIT 0,1"+"</script>")
    Pxtskaiguanvaluetable GetPxtsKaiguanvalueById(long TSTypeID,long qiyeID);
}