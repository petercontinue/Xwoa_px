package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxcertificatetable;

import com.xwcloud.cloud.model.Vo.ExportZSVo;
import com.xwcloud.cloud.model.Vo.zhengshuSTVo;
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
 * @since 2020-11-24
 */
@Repository
public interface IPxcertificatetableDao extends BaseMapper<Pxcertificatetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "zsName", property = "zsName"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxcertificatetable"
            + "</script>")
    List<Pxcertificatetable> getAllList();

    @Select("<script>" +
            "SELECT * from pxcertificatetable a where a.zsName =#{zsName} and a.qiyeID=#{qiyeID}"
            + "</script>")
    List<Pxcertificatetable> getcfZS(String zsName, Long qiyeID);

    //分页获取证书
    @Select("<script>" +
            "select zs.id as id,zs.zsName as zsName,\n" +
            "(select COUNT(id) from pxkechengtable where ZSid=zs.id " +
            "<if test='ews != null'>" +
            " AND ${ews.SqlSegment}" +
            "</if>" +
            ") as kcCount \n" +
            "FROM pxcertificatetable as zs " +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<zhengshuSTVo> getzhengshuPage(Page page, @Param("ews") QueryWrapper queryWrapper1, @Param("ew") QueryWrapper queryWrapper);

    //导出证书
    @Select("<script>" +
            "SELECT stu.id id ,stu.stuName,pxkechengtable.kechengName as kechengName,pxcertificatetable.zsName as zsName,\n" +
            "(SELECT (case WHEN COUNT(id) &gt; 0 THEN '已发证' ELSE '未发证' END)  from pxfazhengtable where Stuid=stu.id AND ZSid=pxkechengtable.ZSid) as FZstate,\n" +
            "(SELECT (case WHEN COUNT(pxfazhengtable.id) &gt; 0 THEN pxstafftable.staffName ELSE '-' END) from pxfazhengtable LEFT JOIN pxstafftable on pxfazhengtable.FZstaff=pxstafftable.id where Stuid=stu.id and ZSid=pxkechengtable.ZSid ) as FZstaff,\n" +
            "(SELECT (case WHEN COUNT(id) &gt; 0 THEN FZdate ELSE '-' END)  from pxfazhengtable where Stuid=stu.id AND ZSid=pxkechengtable.ZSid) as FZdate \n" +
            "from pxstutable as stu \n" +
            "LEFT JOIN pxbuxikechengtable on stu.id=pxbuxikechengtable.stuID \n" +
            "LEFT JOIN pxcampustable on stu.campusID = pxcampustable.id \n" +
            "LEFT JOIN pxkechengtable on pxbuxikechengtable.kechengID=pxkechengtable.id \n" +
            "LEFT JOIN pxstuclasstable on pxbuxikechengtable.id = pxstuclasstable.buxiID \n" +
            "LEFT JOIN pxclasstable on pxstuclasstable.classID=pxclasstable.id \n" +
            "LEFT JOIN pxcertificatetable on pxkechengtable.ZSid=pxcertificatetable.id \n" +
            " WHERE pxcampustable.isOpen !=2 and stu.qiyeID=#{qiyeID}; " +
            "</script>")
    List<ExportZSVo> Exportzhengshu(Long qiyeID);
}