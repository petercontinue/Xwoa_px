package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxkeshiresettable;

import com.xwcloud.cloud.model.Vo.SutClearVo;
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
 * @since 2020-12-08
 */
@Repository
public interface IPxkeshiresettableDao extends BaseMapper<Pxkeshiresettable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "buxiID", property = "buxiID"),
            @Result(column = "buxiName", property = "buxiName"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "xuefei", property = "xuefei"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshiresettable"
            + "</script>")
    List<Pxkeshiresettable> getAllList();

    /**
     * 分页获取学员课时清零记录
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as id,c.campusName as campusName,b.stuName as stuName,a.buxiName as buxiName,a.keshiNum as keshiNum,a.xuefei as xuefei,a.beizhu as beizhu,a.addDate as addDate " +
            "from pxkeshiresettable  as a " +
            "LEFT JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxcampustable as c on b.campusID=c.id " +
            " WHERE 1=1 and c.isOpen != 2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<SutClearVo> getClearPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT " +
            "( CASE WHEN SUM(a.kechengprice*a.remainkeshi) is not null THEN SUM(a.kechengprice*a.remainkeshi) ELSE 0 END ) " +
            " from pxbuxikechengtable a where stuID=#{stuID} and a.type!=2  and  a.qiyeID=#{qiyeID}" +
            "</script>")
    String getClearkeshiMoney(Long stuID, Long qiyeID);

    /**
     * 导出学员课时清零
     */
    @Select("<script>" +
            "SELECT a.id as id,c.campusName as campusName,b.stuName as stuName,a.buxiName as buxiName,a.keshiNum as keshiNum,a.xuefei as xuefei,a.beizhu as beizhu,a.addDate as addDate " +
            "from pxkeshiresettable  as a " +
            "LEFT JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxcampustable as c on b.campusID=c.id " +
            " WHERE 1=1 and c.isOpen != 2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<SutClearVo> ExporestuClear(@Param("ew") QueryWrapper queryWrapper);
}