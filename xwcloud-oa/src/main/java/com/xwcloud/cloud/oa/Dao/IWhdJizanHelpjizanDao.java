package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdJizanHelpjizan;
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
public interface IWhdJizanHelpjizanDao extends BaseMapper<WhdJizanHelpjizan> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "whd_jizan_faqimyjizan_id", property = "whdJizanFaqimyjizanId"),
                @Result(column = "helpjizanWxUserID", property = "helpjizanWxUserID"),
                @Result(column = "helpjizanTime", property = "helpjizanTime"),
                @Result(column = "helpjizanLiuyan", property = "helpjizanLiuyan"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_jizan_helpjizan"
            + "</script>")
    List<WhdJizanHelpjizan> getAllList();
}