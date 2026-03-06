package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdJizanFaqimyjizan;
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
public interface IWhdJizanFaqimyjizanDao extends BaseMapper<WhdJizanFaqimyjizan> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "whd_jizan_huodong_id", property = "whdJizanHuodongId"),
                @Result(column = "wxUserIDFaqiren", property = "wxUserIDFaqiren"),
                @Result(column = "jizanxuanyan", property = "jizanxuanyan"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "dianzantimes", property = "dianzantimes"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_jizan_faqimyjizan"
            + "</script>")
    List<WhdJizanFaqimyjizan> getAllList();
}