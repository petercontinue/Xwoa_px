package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxyxgengjintable;
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
public interface IPxyxgengjintableDao extends BaseMapper<Pxyxgengjintable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "gengjinText", property = "gengjinText"),
                @Result(column = "gengjinTime", property = "gengjinTime"),
                @Result(column = "adduser", property = "adduser"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "isRead", property = "isRead"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxgengjintable"
            + "</script>")
    List<Pxyxgengjintable> getAllList();
}