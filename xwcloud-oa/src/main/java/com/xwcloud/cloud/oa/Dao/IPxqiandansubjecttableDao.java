package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxqiandansubjecttable;
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
public interface IPxqiandansubjecttableDao extends BaseMapper<Pxqiandansubjecttable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "qiandandate", property = "qiandandate"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "kechengprice", property = "kechengprice"),
                @Result(column = "originalprice", property = "originalprice"),
                @Result(column = "buykeshiNum", property = "buykeshiNum"),
                @Result(column = "zongjia", property = "zongjia"),
                @Result(column = "startDate", property = "startDate"),
                @Result(column = "endDate", property = "endDate"),
                @Result(column = "qianDanInfoID", property = "qianDanInfoID"),
                @Result(column = "kechengStyle", property = "kechengStyle"),
                @Result(column = "discount", property = "discount"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandansubjecttable"
            + "</script>")
    List<Pxqiandansubjecttable> getAllList();
}