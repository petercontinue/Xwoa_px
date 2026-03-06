package com.xwcloud.cloud.wsc.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.Vo.jizancanyuVO;
import com.xwcloud.cloud.model.entity.WhdJizanHelpjizan;
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

    /**
     * 查询点赞记录信息
     * @param huodongID
     * @return
     */
    @Select("<script>"+"SELECT a.helpjizanTime,c.nickName,d.nickName AS fquser,c.headImage FROM whd_jizan_helpjizan AS a LEFT JOIN whd_jizan_faqimyjizan AS b ON a.whd_jizan_faqimyjizan_id = b.id LEFT JOIN wsc_user AS c ON a.helpjizanWxUserID = c.id\n" +
            "LEFT JOIN wsc_user AS d ON b.wxUserIDFaqiren = d.id WHERE b.whd_jizan_huodong_id = #{huodongID}"+"</script>")
    List<jizancanyuVO> GetHelpdianzanRecordsList(long huodongID);
}