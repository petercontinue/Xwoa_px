package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscDongtaiinfo;
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
public interface IWscDongtaiinfoDao extends BaseMapper<WscDongtaiinfo> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "wscuserID", property = "wscuserID"),
                @Result(column = "dongtaiTitle", property = "dongtaiTitle"),
                @Result(column = "dongtaiContent", property = "dongtaiContent"),
                @Result(column = "Addtime", property = "addtime"),
                @Result(column = "iszhiding", property = "iszhiding"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "isShow", property = "isShow"),
                @Result(column = "yueduTimes", property = "yueduTimes"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_dongtaiinfo"
            + "</script>")
    List<WscDongtaiinfo> getAllList();
}