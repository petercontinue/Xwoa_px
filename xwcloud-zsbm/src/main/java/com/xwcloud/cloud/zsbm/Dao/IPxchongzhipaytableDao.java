package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.chongzhiPayListVo;
import com.xwcloud.cloud.model.Vo.chongzhixiangqingVO;
import com.xwcloud.cloud.model.entity.Pxchongzhipaytable;

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
 * @since 2020-11-12
 */
@Repository
public interface IPxchongzhipaytableDao extends BaseMapper<Pxchongzhipaytable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "chongzhiPayMoney", property = "chongzhiPayMoney"),
            @Result(column = "type", property = "type"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "tuifeiID", property = "tuifeiID"),
            @Result(column = "xiaokeID", property = "xiaokeID"),
            @Result(column = "jixiaocunID", property = "jixiaocunID"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxchongzhipaytable"
            + "</script>")
    List<Pxchongzhipaytable> getAllList();

    @Select("<script>"+"SELECT a.id,a.chongzhiPayMoney,a.type,a.beizhu,a.addTime,b.stuName,c.staffName FROM pxchongzhipaytable AS a LEFT JOIN pxstutable as b ON a.stuID = b.id LEFT JOIN pxstafftable as c ON a.addStaffID = c.id"+ " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<chongzhiPayListVo> GetUserChongzhiPayListPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT a.id,a.chongzhiPayMoney,a.type,a.beizhu,a.addTime,b.stuName,c.staffName FROM pxchongzhipaytable AS a LEFT JOIN pxstutable as b ON a.stuID = b.id LEFT JOIN pxstafftable as c ON a.addStaffID = c.id"+ " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<chongzhiPayListVo> GetUserChongzhiPayListList(@Param("ew") Wrapper wrapper);

    /**
     * 查询充值详情
     * @param page
     * @return
     */
    @Select("<script>"+"select a.id,a.chongzhiPayMoney AS money ,0 AS zengsongMoney,b.remainChongzhi," +
            "c.staffName AS yejistaffName,a.beizhu AS beizhu,a.addTime " +
            "from pxchongzhipaytable AS a " +
            "LEFT JOIN pxstutable AS b ON a.stuID = b.id  " +
            "LEFT JOIN pxstafftable AS c ON a.addStaffID = c.id " +
            "where a.qiyeID=#{qiyeID} AND a.stuID = #{stuID} \n" +
            "union all \n" +
            "select cz.id,cz.shijiChongzhiMoney AS money,cz.songMoney AS zengsongMoney," +
            "cz.shideTotalMoney remainChongzhi,staff.staffName AS yejistaffName,cz.shuoming AS beizhu,cz.addTime   " +
            "from pxchongzhitable AS cz LEFT JOIN pxstutable AS stu ON cz.stuID = stu.id " +
            "LEFT JOIN pxstafftable AS staff ON cz.yejiStaffID = staff.id " +
            "where cz.qiyeID=#{qiyeID} AND cz.stuID = #{stuID} "+
            "</script>")
    Page<chongzhixiangqingVO> GetUserChongzhixiangqingPages(Page page, long qiyeID, long stuID);
}