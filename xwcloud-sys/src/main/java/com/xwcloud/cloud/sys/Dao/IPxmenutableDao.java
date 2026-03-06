package com.xwcloud.cloud.sys.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxmenutable;
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
 * @author yinqi
 * @since 2020-10-20
 */
@Repository
public interface IPxmenutableDao extends BaseMapper<Pxmenutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "text", property = "text"),
            @Result(column = "fid", property = "fid"),
            @Result(column = "url", property = "url"),
            @Result(column = "iconCls", property = "iconCls"),
            @Result(column = "paixu", property = "paixu"),
            @Result(column = "levelID", property = "levelID"),
            @Result(column = "isShow", property = "isShow"),
    })
    @Select("<script>" +
            "SELECT * from  pxmenutable WHERE isShow=1"
            + "</script>")
    List<Pxmenutable> getAllList();

    /**
     * 查询对应菜单的子菜单
     *
     * @param id
     * @return
     */
    @Select("<script>SELECT * from pxmenutable WHERE fid = #{id} and isShow=1</script>")
    List<Pxmenutable> getErjiMenu(@Param("id") long id);
}