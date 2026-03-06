package com.xwcloud.cloud.zsbm.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Qiandanshenpisubject;
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
 * @since 2021-04-08
 */
@Repository
public interface IQiandanshenpisubjectDao extends BaseMapper<Qiandanshenpisubject> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiandanshenpiID", property = "qiandanshenpiID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandandate", property = "qiandandate"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "originalprice", property = "originalprice"),
            @Result(column = "zengsongkeshi", property = "zengsongkeshi"),
            @Result(column = "buykeshiNum", property = "buykeshiNum"),
            @Result(column = "zongjia", property = "zongjia"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "kechengStyle", property = "kechengStyle"),
            @Result(column = "discount", property = "discount"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "charukebiao",property = "charukebiao"),
            @Result(column = "pkid", property = "pkid"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  qiandanshenpisubject"
            + "</script>")
    List<Qiandanshenpisubject> getAllList();
}