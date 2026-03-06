package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Qiandanapppaymoney;
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
 * @since 2021-05-28
 */
@Repository
public interface IQiandanapppaymoneyDao extends BaseMapper<Qiandanapppaymoney> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiandanAppayID", property = "qiandanAppayID"),
            @Result(column = "paymoneystyleID", property = "paymoneystyleID"),
            @Result(column = "paymoney", property = "paymoney"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  qiandanapppaymoney"
        + "</script>")
List<Qiandanapppaymoney> getAllList();
}