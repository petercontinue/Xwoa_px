package com.xwcloud.cloud.wsc.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.Vo.jizanfaqiVO;
import com.xwcloud.cloud.model.entity.WhdJizanFaqimyjizan;
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
 * @since 2021-05-24
 */
@Repository
public interface IWhdJizanFaqimyjizanDao extends BaseMapper<WhdJizanFaqimyjizan> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "whd_jizan_huodong_id", property = "whdJizanHuodongId"),
            @Result(column = "wxUserIDFaqiren", property = "wxUserIDFaqiren"),
            @Result(column = "jizanxuanyan", property = "jizanxuanyan"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_jizan_faqimyjizan"
            + "</script>")
    List<WhdJizanFaqimyjizan> getAllList();

    @Select("<script>"+"SELECT b.nickName,b.headImage,a.dianzantimes FROM whd_jizan_faqimyjizan AS a LEFT JOIN wsc_user AS b " +
            "ON a.wxUserIDFaqiren = b.id WHERE a.whd_jizan_huodong_id = #{huodongID} "+"</script>")
    List<jizanfaqiVO> GetjizanFaqiList(long huodongID);
}