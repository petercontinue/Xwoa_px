package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxbuxistyletable;
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
 * @since 2020-11-20
 */
@Repository
public interface IPxbuxistyletableDao extends BaseMapper<Pxbuxistyletable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buxiStyleName", property = "buxiStyleName"),
            @Result(column = "is1v1", property = "is1v1"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbuxistyletable"
            + "</script>")
    List<Pxbuxistyletable> getAllList();

    @Select("<script>" +
            "SELECT a.id id,a.buxiStyleName name from  pxbuxistyletable a where a.qiyeID=#{qiyeID} "
            + "</script>")
    List<listVo> getAllbuxiStyleList(Long qiyeID);


    @Select("<script>" +
            "SELECT * from  pxbuxistyletable where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxbuxistyletable> selectbxstyle(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT * from  pxbuxistyletable where qiyeID=#{qiyeID} limit 1"
            + "</script>")
    Pxbuxistyletable getOne(Long qiyeID);
}