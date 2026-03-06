package com.xwcloud.cloud.caiwu.Dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Tuikechenginfo;
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
public interface ITuikechenginfoDao extends BaseMapper<Tuikechenginfo> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "tuibxkechengID", property = "tuibxkechengID"),
            @Result(column = "remainkeshi", property = "remainkeshi"),
            @Result(column = "tuikeshi", property = "tuikeshi"),
            @Result(column = "tuikechengPrice", property = "tuikechengPrice"),
            @Result(column = "adduser", property = "adduser"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "tuifeispID", property = "tuifeispID"),
            @Result(column = "tuimoney", property = "tuimoney"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  tuikechenginfo "
        + "</script>")
List<Tuikechenginfo> getAllList();
}