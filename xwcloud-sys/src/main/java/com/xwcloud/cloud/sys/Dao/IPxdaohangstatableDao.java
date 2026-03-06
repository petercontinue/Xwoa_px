package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxdaohangstatable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-30
 */
@Repository
public interface IPxdaohangstatableDao extends BaseMapper<Pxdaohangstatable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "staffID", property = "staffID"),
                @Result(column = "dhID", property = "dhID"),
                @Result(column = "paixu", property = "paixu"),
                @Result(column = "isShow", property = "isShow"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxdaohangstatable"
            + "</script>")
    List<Pxdaohangstatable> getAllList();

    @Select("<script>" +
            "SELECT a.*,b.staffName,c.* from pxdaohangstatable a \n" +
            "LEFT JOIN pxstafftable b on a.staffID=b.id\n" +
            "LEFT JOIN pxdaohangtable c on a.dhID=c.id "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String,Object>> getstaffdaohang(@Param("ew")QueryWrapper queryWrapper);

}