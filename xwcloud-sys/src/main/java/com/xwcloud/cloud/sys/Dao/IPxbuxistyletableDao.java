package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxbuxistyletable;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
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
 * @author yinqi
 * @since 2020-10-20
 */
@Repository
public interface IPxbuxistyletableDao extends BaseMapper<Pxbuxistyletable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buxiStyleName", property = "buxiStyleName"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbuxistyletable "
            + "</script>")
    List<Pxbuxistyletable> getAllList();

    @Select("<script>" + "SELECT * FROM pxkechengtable WHERE buxiStyleID = #{buxiStyleID}" + "</script>")
    List<Pxkechengtable> GetKechengByBuxistyleID(String buxiStyleID);
}