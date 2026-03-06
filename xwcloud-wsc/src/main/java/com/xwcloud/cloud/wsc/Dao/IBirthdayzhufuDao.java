package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.Vo.birthdayzhufuVO;
import com.xwcloud.cloud.model.Vo.birthinfoVO;
import com.xwcloud.cloud.model.Vo.dianzangVO;
import com.xwcloud.cloud.model.entity.Birthdayzhufu;
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
 * @since 2021-05-18
 */
@Repository
public interface IBirthdayzhufuDao extends BaseMapper<Birthdayzhufu> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "zhufuUserID", property = "zhufuUserID"),
            @Result(column = "zhufuContent", property = "zhufuContent"),
            @Result(column = "liuyanUserid", property = "liuyanUserid"),
            @Result(column = "liuyanTimes", property = "liuyanTimes"),
    })
    @Select("<script>" +
            "SELECT * from  birthdayzhufu"
            + "</script>")
    List<Birthdayzhufu> getAllList();

    /**
     * 查询用户生日点赞信息
     * @param beidianzanUserID
     * @return
     */
    @Select("<script>"+"SELECT a.dianzanDatetime,u.nickName FROM birthdaydianzang AS a LEFT JOIN wsc_user " +
            "AS u ON a.dianzanUserID = u.id WHERE a.beidianzanUserID=#{beidianzanUserID}"+"</script>")
    List<dianzangVO> GetbirthDianzanInfo(long beidianzanUserID);

    /**
     * 查询生日祝福信息
     * @param zhufuUserID
     * @return
     */
    @Select("<script>"+"SELECT u.nickName,u.headImage,zhufu.liuyanTimes,zhufu.zhufuContent FROM birthdayzhufu " +
            "AS zhufu LEFT JOIN wsc_user AS u ON zhufu.liuyanUserid = u.id WHERE zhufu.zhufuUserID = #{zhufuUserID}"+"</script>")
    List<birthdayzhufuVO>GetAllbirthdayZhufu(long zhufuUserID);

    /**
     * 查询学员生日主页学员信息
     * @param stuID
     * @return
     */
    @Select("<script>"+"SELECT stu.id,stu.stuName,stu.stuPhoto,stu.stubirth,\n" +
            "(SELECT COUNT(*)FROM birthdaydianzang WHERE beidianzanUserID = stu.id ) AS dianzangCount,\n" +
            "(SELECT COUNT(*) FROM birthdayzhufu WHERE zhufuUserID = stu.id) AS pinglunCount\n" +
            "FROM pxstutable AS stu WHERE stu.id = #{stuID}"+"</script>")
    List<birthinfoVO> GetshengriStuInfo(long stuID);
}