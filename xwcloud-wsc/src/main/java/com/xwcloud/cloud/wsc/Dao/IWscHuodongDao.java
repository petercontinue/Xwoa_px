package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.wschuodongVO;
import com.xwcloud.cloud.model.entity.WscHuodong;
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
 * @since 2021-01-17
 */
@Repository
public interface IWscHuodongDao extends BaseMapper<WscHuodong> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "huodongName", property = "huodongname"),
            @Result(column = "isShow", property = "isshow"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_huodong"
            + "</script>")
    List<WscHuodong> getAllList();

    @Select("<script>"+"SELECT * FROM wsc_huodong WHERE isShow = 1 AND id >6"+"</script>")
    List<WscHuodong>GetAllWscHuodongList();

    @Select("<script>"+"SELECT * FROM wsc_huodong_value AS a LEFT JOIN wsc_huodong AS b ON a.huodongID = b.id WHERE a.qiyeID =#{qiyeID}"+"</script>")
    List<wschuodongVO> GetAllhuodongList(long qiyeID);
}