package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxjifentable;
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
 * @since 2020-11-18
 */
@Repository
public interface IPxjifentableDao extends BaseMapper<Pxjifentable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "type", property = "type"),
            @Result(column = "oldIntegral", property = "oldIntegral"),
            @Result(column = "integral", property = "integral"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "createTime", property = "createTime"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxjifentable"
            + "</script>")
    List<Pxjifentable> getAllList();

    @Select("<script>"+"DELETE FROM pxjifentable WHERE stuID=#{stuID} AND qiyeID=#{qiyeID}"+"</script>")
    Integer deleteJiifenByStuID(Long stuID,long qiyeID);
}