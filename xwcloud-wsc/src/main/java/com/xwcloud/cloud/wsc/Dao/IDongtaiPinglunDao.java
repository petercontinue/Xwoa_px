package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.DongtaiPinglun;
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
 * @since 2021-05-19
 */
@Repository
public interface IDongtaiPinglunDao extends BaseMapper<DongtaiPinglun> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "dongtaiID", property = "dongtaiID"),
            @Result(column = "pluserID", property = "pluserID"),
            @Result(column = "plcontent", property = "plcontent"),
            @Result(column = "pinglunDatetime", property = "pinglunDatetime"),
    })
    @Select("<script>" +
            "SELECT * from  dongtai_pinglun"
            + "</script>")
    List<DongtaiPinglun> getAllList();

    /**
     * 查询动态的评论信息
     * @param dongtaiID
     * @return
     */
    @Select("<script>" + "SELECT b.headImage,b.nickName,a.plcontent,a.pinglunDatetime " +
            "FROM dongtai_pinglun AS a LEFT JOIN wsc_user AS b on a.pluserID = b.id WHERE a.dongtaiID=#{dongtaiID}" + "</script>")
    List<dongtaipinglunVO> GetDongtaiPinglunInfos(long dongtaiID);
}