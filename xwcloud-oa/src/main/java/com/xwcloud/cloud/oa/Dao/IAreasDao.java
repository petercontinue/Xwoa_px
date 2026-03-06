package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.OA.Areas;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
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
 * @since 2021-07-23
 */
@Repository
public interface IAreasDao extends BaseMapper<Areas> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "arealevel", property = "arealevel"),
            @Result(column = "areaname", property = "areaname"),
            @Result(column = "lat", property = "lat"),
            @Result(column = "lng", property = "lng"),
            @Result(column = "parentid", property = "parentid"),
            @Result(column = "position", property = "position"),
            @Result(column = "shortname", property = "shortname"),
            @Result(column = "sort", property = "sort"),
    })
    @Select("<script>" +
            "SELECT * from  areas"
            + "</script>")
    List<Areas> getAllList();


    //根据省份获取下级城市
    @ResultMap(value = "BaseResultMap")
    @Select("SELECT child.* from areas as child inner JOIN areas as parent on child.parentid=parent.id WHERE parent.id=#{id}")
    List<Areas> getAllAreasSubInfo(String id);
}