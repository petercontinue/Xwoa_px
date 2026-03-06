package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.assetsVO;
import com.xwcloud.cloud.model.Vo.asstestyleVO;
import com.xwcloud.cloud.model.Vo.dengjiassetsVO;
import com.xwcloud.cloud.model.entity.Pxassetstable;
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
public interface IPxassetstableDao extends BaseMapper<Pxassetstable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "assetsName", property = "assetsName"),
            @Result(column = "leibie", property = "leibie"),
            @Result(column = "guige", property = "guige"),
            @Result(column = "num", property = "num"),
            @Result(column = "danwei", property = "danwei"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxassetstable"
            + "</script>")
    List<Pxassetstable> getAllList();

    /**
     * 分页查询固定资产信息
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>"+
            "SELECT a.id,a.assetsName,c.assetsName AS styleName,a.guige,b.campusName,a.num,a.danwei,a.campusID,a.leibie " +
            "FROM pxassetstable AS a " +
            "LEFT JOIN pxcampustable AS b ON a.campusID = b.id " +
            "LEFT JOIN pxassetsstyletable AS c ON a.leibie = c.id " +
            "WHERE b.isOpen!=2"
            +"<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<assetsVO> getassetsPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT a.id,a.assetsName AS name FROM pxassetsstyletable AS a"  + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<asstestyleVO> getAllasstestyleList(@Param("ew") Wrapper wrapper);

    /**
     * 查询所有固定资产信息
     * @return
     */
    @Select("<script>"+
            "SELECT a.id,a.assetsName,c.assetsName AS styleName,a.guige,b.campusName,a.num,a.danwei " +
            "FROM pxassetstable AS a" +
            " LEFT JOIN pxcampustable AS b ON a.campusID = b.id" +
            " LEFT JOIN pxassetsstyletable AS c ON a.leibie = c.id" +
            " WHERE b.isOpen!=2 \n"+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "</script>")
    List<assetsVO>GetAllAssetsList(@Param("ew")QueryWrapper queryWrapper);

    /**
     * 固定资产登记记录
     * @return
     */
    @Select("<script>"+
            "SELECT d.campusName,b.assetsName,c.assetsName AS styleName,b.guige,a.num,b.danwei,a.addTime,a.beizhu,e.staffName FROM pxassetsaddtable AS a \n" +
            "LEFT JOIN pxassetstable AS b ON a.assetsID = b.id\n" +
            "LEFT JOIN pxassetsstyletable AS c ON b.leibie = c.id \n" +
            "LEFT JOIN pxcampustable AS d ON b.campusID = d.id\n" +
            "LEFT JOIN pxstafftable AS e ON a.addStaffID = e.id " +
            "WHERE d.isOpen!=2  "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            +"</script>")
    List<dengjiassetsVO>GetListAssetsDengjiList(@Param("ew")QueryWrapper queryWrapper);

    /**
     * 固定资产报废记录
     * @return
     */
    @Select("<script>"+
            "SELECT d.campusName,b.assetsName,c.assetsName AS styleName,b.guige,a.num,b.danwei,a.addTime,a.beizhu,e.staffName FROM pxassetsouttable AS a \n" +
            "LEFT JOIN pxassetstable AS b ON a.assetsID = b.id\n" +
            "LEFT JOIN pxassetsstyletable AS c ON b.leibie = c.id \n" +
            "LEFT JOIN pxcampustable AS d ON b.campusID = d.id\n" +
            "LEFT JOIN pxstafftable AS e ON a.addStaffID = e.id " +
            "WHERE d.isOpen!=2 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "</script>")
    List<dengjiassetsVO> GetListAssetsBaofeiList(@Param("ew")QueryWrapper queryWrapper);
}