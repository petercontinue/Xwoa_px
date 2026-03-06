package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.assetAddVO;
import com.xwcloud.cloud.model.entity.Pxassetsouttable;
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
 * @since 2020-10-22
 */
@Repository
public interface IPxassetsouttableDao extends BaseMapper<Pxassetsouttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "AssetsID", property = "AssetsID"),
            @Result(column = "num", property = "num"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxassetsouttable"
            + "</script>")
    List<Pxassetsouttable> getAllList();

    @Select("<script>"+"SELECT b.assetsName,c.assetsName AS styleName,a.num,a.beizhu,d.staffName AS jinbanStaffName,a.addTime FROM pxassetsouttable AS a \n" +
            "LEFT JOIN pxassetstable AS b ON a.assetsID=b.id \n" +
            "LEFT JOIN pxassetsstyletable AS c ON b.leibie = c.id\n" +
            "LEFT JOIN pxstafftable AS d ON a.addStaffID = d.id\n" +
            "LEFT JOIN pxcampustable AS e ON b.campusID = e.id\n" +
            "WHERE e.isOpen!=2"+"<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<assetAddVO> GetassetsOutPages(Page page, @Param("ew") Wrapper wrapper);
}