package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.jizanfaqiVO;
import com.xwcloud.cloud.model.entity.WhdJizanHuodong;
import org.apache.ibatis.annotations.Param;
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
 * @since 2021-06-03
 */
@Repository
public interface IWhdJizanHuodongDao extends BaseMapper<WhdJizanHuodong> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "jizanHuodongName", property = "jizanHuodongName"),
            @Result(column = "jizanLogoUrl", property = "jizanLogoUrl"),
            @Result(column = "jizanShuoming", property = "jizanShuoming"),
            @Result(column = "startTime", property = "startTime"),
            @Result(column = "endTime", property = "endTime"),
            @Result(column = "isOpen", property = "isOpen"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addUser", property = "addUser"),
            @Result(column = "liulantimes", property = "liulantimes"),
            @Result(column = "fenxiangtimes", property = "fenxiangtimes"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_jizan_huodong"
            + "</script>")
    List<WhdJizanHuodong> getAllList();

    @Select("<script>"+"\n" +
            "SELECT a.*,(SELECT COUNT(*) FROM whd_jizan_faqimyjizan  " +
            "WHERE whd_jizan_huodong_id = a.id) AS canyucount FROM whd_jizan_huodong AS a"+ "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    List<WhdJizanHuodong> GetAlljizanList(@Param("ew") QueryWrapper wrapper);

    /**
     * 查询集赞活动发起人信息
     * @param huodongID
     * @param faqirenID
     * @return
     */
    @Select("<script>"+"SELECT\n" +
            "\tu.nickName,u.headImage,a.jizanxuanyan,a.id AS faqiID \n" +
            "FROM\n" +
            "\twhd_jizan_faqimyjizan AS a\n" +
            "\tLEFT JOIN wsc_user AS u ON a.wxUserIDFaqiren = u.id\n" +
            "\tLEFT JOIN whd_h5_huodongfabu AS c ON a.whd_jizan_huodong_id = c.id\n" +
            "\tWHERE c.id = #{huodongID} AND a.wxUserIDFaqiren = #{faqirenID}"+"</script>")
    List<jizanfaqiVO> GetjizanfaqiInfo(long huodongID, long faqirenID);
}