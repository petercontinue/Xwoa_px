package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxzhaoshenmubiaostafftable;
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
 * @since 2020-11-26
 */
@Repository
public interface IPxzhaoshenmubiaostafftableDao extends BaseMapper<Pxzhaoshenmubiaostafftable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "yearMonth", property = "yearMonth"),
            @Result(column = "monthMoney", property = "monthMoney"),
            @Result(column = "monthSum", property = "monthSum"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxzhaoshenmubiaostafftable"
            + "</script>")
    List<Pxzhaoshenmubiaostafftable> getAllList();


}