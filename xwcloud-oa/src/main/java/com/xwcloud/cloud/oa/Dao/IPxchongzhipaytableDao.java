package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxchongzhipaytable;
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
public interface IPxchongzhipaytableDao extends BaseMapper<Pxchongzhipaytable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "chongzhiPayMoney", property = "chongzhiPayMoney"),
                @Result(column = "type", property = "type"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "qiandanID", property = "qiandanID"),
                @Result(column = "tuifeiID", property = "tuifeiID"),
                @Result(column = "xiaokeID", property = "xiaokeID"),
                @Result(column = "jixiaocunID", property = "jixiaocunID"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "kechengName", property = "kechengName"),
    })
    @Select("<script>" +
            "SELECT * from  pxchongzhipaytable"
            + "</script>")
    List<Pxchongzhipaytable> getAllList();
}