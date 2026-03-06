package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscTuikeYongjin;
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
 * @since 2021-08-19
 */
@Repository
public interface IWscTuikeYongjinDao extends BaseMapper<WscTuikeYongjin> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "orderNumber", property = "orderNumber"),
                @Result(column = "money", property = "money"),
                @Result(column = "fid", property = "fid"),
                @Result(column = "fidMoney", property = "fidMoney"),
                @Result(column = "fidJiesuanDateTime", property = "fidJiesuanDateTime"),
                @Result(column = "topfid", property = "topfid"),
                @Result(column = "topfidMoney", property = "topfidMoney"),
                @Result(column = "topfidJiesuanDateTime", property = "topfidJiesuanDateTime"),
                @Result(column = "yongjinType", property = "yongjinType"),
                @Result(column = "shuoming", property = "shuoming"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_tuike_yongjin"
            + "</script>")
    List<WscTuikeYongjin> getAllList();
}