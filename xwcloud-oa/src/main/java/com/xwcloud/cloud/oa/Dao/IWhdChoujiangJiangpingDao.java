package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdChoujiangJiangping;
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
public interface IWhdChoujiangJiangpingDao extends BaseMapper<WhdChoujiangJiangping> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "jiangpingLevel", property = "jiangpingLevel"),
                @Result(column = "jiangpingName", property = "jiangpingName"),
                @Result(column = "jiangpingImg", property = "jiangpingImg"),
                @Result(column = "choujiangHuodongID", property = "choujiangHuodongID"),
                @Result(column = "zhongjiangGailv", property = "zhongjiangGailv"),
                @Result(column = "jiangpingTotalNum", property = "jiangpingTotalNum"),
                @Result(column = "type", property = "type"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  whd_choujiang_jiangping"
            + "</script>")
    List<WhdChoujiangJiangping> getAllList();
}