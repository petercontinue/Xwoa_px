package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.yuexiqokeVo;
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
 * @since 2020-11-20
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
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengName", property = "kechengName"),
    })
    @Select("<script>" +
            "SELECT * from  pxchongzhipaytable"
            + "</script>")
    List<Pxchongzhipaytable> getAllList();


    /**
     * 分页获取余额消课
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as id,c.campusName as campusName,a.stuID as stuID,b.zidingyiStuID as zidingyiStuID, b.stuName as stuName,\n" +
            "(select cardNumber from pxstucardtable where stuID=a.stuID) as cardNum,a.kechengName kechengName,\n" +
            "a.beizhu as beizhu,a.chongzhiPayMoney as chongzhiPayMoney,d.staffName as addUser,a.addTime as addTime\n" +
            "from pxchongzhipaytable as a\n" +
            "LEFT JOIN pxstutable as b on a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable as c on b.campusID=c.id\n" +
            "LEFT JOIN pxstafftable as d on a.addStaffID=d.id\n" +
            " WHERE a.type=3 and c.isOpen!=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<yuexiqokeVo> getyuexiaokePage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 导出余额消课
     */
    @Select("<script>" +
            "SELECT a.id as id,c.campusName as campusName,b.stuName as stuName," +
            "(case WHEN b.zidingyiStuID is null THEN a.stuID ELSE b.zidingyiStuID END) stuID," +
            "(select cardNumber from pxstucardtable where stuID=a.stuID) as cardNum,a.kechengName kechengName,\n" +
            "a.beizhu as beizhu,a.chongzhiPayMoney as chongzhiPayMoney,d.staffName as addUser,a.addTime as addTime\n" +
            "from pxchongzhipaytable as a\n" +
            "LEFT JOIN pxstutable as b on a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable as c on b.campusID=c.id\n" +
            "LEFT JOIN pxstafftable as d on a.addStaffID=d.id\n" +
            " WHERE a.type=3 and c.isOpen!=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<yuexiqokeVo> ExportyuexiaokePage(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxchongzhipaytable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxchongzhipaytable> selectChongzhiPay(@Param("ew") QueryWrapper queryWrapper);
}