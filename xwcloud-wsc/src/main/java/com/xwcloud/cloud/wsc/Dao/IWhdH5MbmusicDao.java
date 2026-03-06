package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.WhdH5Mbmusic;
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
 * @since 2021-04-01
 */
@Repository
public interface IWhdH5MbmusicDao extends BaseMapper<WhdH5Mbmusic> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "mbMusicName", property = "mbMusicName"),
            @Result(column = "mbMusicUrl", property = "mbMusicUrl"),
    })
    @Select("<script>" +
            "SELECT * from  whd_h5_mbmusic"
            + "</script>")
    List<WhdH5Mbmusic> getAllList();
}