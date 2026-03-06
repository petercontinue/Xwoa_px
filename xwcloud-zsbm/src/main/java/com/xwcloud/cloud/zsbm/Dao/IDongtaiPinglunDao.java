package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.DongtaiPinglun;
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
 * @since 2021-08-06
 */
@Repository
public interface IDongtaiPinglunDao extends BaseMapper<DongtaiPinglun> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "dongtaiID", property = "dongtaiID"),
                @Result(column = "pluserID", property = "pluserID"),
                @Result(column = "plcontent", property = "plcontent"),
                @Result(column = "pinglunDatetime", property = "pinglunDatetime"),
    })
    @Select("<script>" +
            "SELECT * from  dongtai_pinglun"
            + "</script>")
    List<DongtaiPinglun> getAllList();
}