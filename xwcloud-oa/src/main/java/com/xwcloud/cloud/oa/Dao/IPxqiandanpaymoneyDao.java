package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxqiandanpaymoney;
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
public interface IPxqiandanpaymoneyDao extends BaseMapper<Pxqiandanpaymoney> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "qiandanID", property = "qiandanID"),
                @Result(column = "paymoneyStyleID", property = "paymoneyStyleID"),
                @Result(column = "payMoney", property = "payMoney"),
                @Result(column = "qianDanDate", property = "qianDanDate"),
                @Result(column = "isWeikuan", property = "isWeikuan"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandanpaymoney"
            + "</script>")
    List<Pxqiandanpaymoney> getAllList();
}