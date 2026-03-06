package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscUserjiaoyi;
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
public interface IWscUserjiaoyiDao extends BaseMapper<WscUserjiaoyi> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "orderNumber", property = "orderNumber"),
                @Result(column = "wscUserID", property = "wscUserID"),
                @Result(column = "payMoney", property = "payMoney"),
                @Result(column = "giveMoney", property = "giveMoney"),
                @Result(column = "totalMoney", property = "totalMoney"),
                @Result(column = "style", property = "style"),
                @Result(column = "type", property = "type"),
                @Result(column = "addDate", property = "addDate"),
                @Result(column = "okDate", property = "okDate"),
                @Result(column = "state", property = "state"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_userjiaoyi"
            + "</script>")
    List<WscUserjiaoyi> getAllList();
}