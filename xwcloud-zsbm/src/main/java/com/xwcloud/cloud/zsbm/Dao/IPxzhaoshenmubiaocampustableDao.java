package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.zhaoshengStaffMubaoVo;
import com.xwcloud.cloud.model.Vo.zhaoshengmubiaoVo;
import com.xwcloud.cloud.model.entity.Pxzhaoshenmubiaocampustable;
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
 * @since 2020-11-26
 */
@Repository
public interface IPxzhaoshenmubiaocampustableDao extends BaseMapper<Pxzhaoshenmubiaocampustable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "yearMonth", property = "yearMonth"),
            @Result(column = "monthMoney", property = "monthMoney"),
            @Result(column = "monthSum", property = "monthSum"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxzhaoshenmubiaocampustable"
            + "</script>")
    List<Pxzhaoshenmubiaocampustable> getAllList();

    //分页查询校区招生目标
    @Select("<script>" +
            "SELECT  a.id,a.monthMoney,a.monthSum,a.yearMonth,a.campusID,b.campusName,a.addtime, \n" +
            "( SELECT SUM( monthMoney ) FROM pxzhaoshenmubiaostafftable c JOIN pxstafftable d ON c.staffID = d.id WHERE a.yearMonth = c.yearMonth AND a.campusID = d.campusID ) yuangong\n" +
            "FROM pxzhaoshenmubiaocampustable AS a \n" +
            "LEFT JOIN pxcampustable AS b ON a.campusID = b.id" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<zhaoshengmubiaoVo> GetZhaoshengmubiaoCampusPages(Page page, @Param("ew") Wrapper wrapper);

    //获取校区年月的招生目标
    @Select("<script>"+"SELECT * FROM pxzhaoshenmubiaocampustable WHERE campusID=#{campusID} AND yearMonth = #{yearMonth}"+"</script>")
    List<Pxzhaoshenmubiaocampustable> GetListcampusMubiao(Long campusID,String yearMonth);

    @Select("<script>" +
            "SELECT a.*, b.staffName\n" +
            "FROM pxzhaoshenmubiaostafftable a\n" +
            "JOIN pxstafftable b ON a.staffID=b.id"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<zhaoshengStaffMubaoVo> getStaffZhaoshengmubiaoPages(Page<zhaoshengStaffMubaoVo> page, @Param("ew") QueryWrapper<zhaoshengStaffMubaoVo> wrapper);
}