package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Qiandanapppayyejiren;
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
 * @since 2021-05-28
 */
@Repository
public interface IQiandanapppayyejirenDao extends BaseMapper<Qiandanapppayyejiren> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiandanAppayID", property = "qiandanAppayID"),
            @Result(column = "qiandanstaffID", property = "qiandanstaffID"),
            @Result(column = "yejiMoney", property = "yejiMoney"),
            @Result(column = "yejidate", property = "yejidate"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  qiandanapppayyejiren"
            + "</script>")
    List<Qiandanapppayyejiren> getAllList();
}