package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkeshizhuansongtable;
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
public interface IPxkeshizhuansongtableDao extends BaseMapper<Pxkeshizhuansongtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "songstuID", property = "songstuID"),
                @Result(column = "songkechengID", property = "songkechengID"),
                @Result(column = "songkechengPrice", property = "songkechengPrice"),
                @Result(column = "songKeshiNum", property = "songKeshiNum"),
                @Result(column = "songXueFei", property = "songXueFei"),
                @Result(column = "shoustuID", property = "shoustuID"),
                @Result(column = "shoukechengID", property = "shoukechengID"),
                @Result(column = "shoukechengPrice", property = "shoukechengPrice"),
                @Result(column = "shouXueFei", property = "shouXueFei"),
                @Result(column = "shouKeshi", property = "shouKeshi"),
                @Result(column = "zhuansongDate", property = "zhuansongDate"),
                @Result(column = "shuoMing", property = "shuoMing"),
                @Result(column = "zhuansongXinXi", property = "zhuansongXinXi"),
                @Result(column = "addtime", property = "addtime"),
                @Result(column = "adduser", property = "adduser"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshizhuansongtable"
            + "</script>")
    List<Pxkeshizhuansongtable> getAllList();
}