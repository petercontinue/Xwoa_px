package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.classstuinfoVO;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;

import com.xwcloud.cloud.model.Vo.ziyouqiandaoLookStuVo;
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
 * @since 2020-11-16
 */
@Repository
public interface IPxstuclasstableDao extends BaseMapper<Pxstuclasstable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buxiID", property = "buxiID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstuclasstable"
            + "</script>")
    List<Pxstuclasstable> getAllList();

    /**
     * 自由签到班级学员详情
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT b.stuID as stuID,c.zidingyiStuID as zidingyiStuID,c.stuName as stuName,d.campusName as campusName  \n" +
            "FROM pxstuclasstable as a \n" +
            "LEFT JOIN pxbuxikechengtable as b on a.buxiID=b.id\n" +
            "LEFT JOIN pxstutable as c on b.stuID=c.id\n" +
            "LEFT JOIN pxcampustable as d on c.campusID=d.id " +
            "WHERE d.isOpen !=2  " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<ziyouqiandaoLookStuVo> getziyouqiandaoLookStuPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxstuclasstable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxstuclasstable> selectstuclass(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 根据班级获取班级下面的所有学生信息
     *
     * @param classID
     * @param qiyeID
     * @return
     */
    @Select("<script>" + "SELECT\n" +
            "\tc.id,b.id AS bxID,c.stuName,d.kechengName ,e.is1v1Class,1 AS type\n" +
            "FROM\n" +
            "\tpxstuclasstable AS a\n" +
            "\tLEFT JOIN pxbuxikechengtable AS b ON a.buxiID = b.id\n" +
            "\tLEFT JOIN pxstutable AS c ON b.stuID = c.id\n" +
            "\tLEFT JOIN pxkechengtable AS d ON b.kechengID = d.id \n" +
            "\tLEFT JOIN pxclasstable AS e ON a.classID = e.id\n" +
            "WHERE\n" +
            "\tb.isShow = 1 AND a.classID = #{classID} AND a.qiyeID=#{qiyeID}" + "</script>")
    List<classstuinfoVO> getClassStuInfobyClassID(long classID, Long qiyeID);

    /**
     * 根据排课获取排课下的所有学生信息
     *
     * @param paikeID
     * @param qiyeID
     * @return
     */
    @Select("<script>" + "SELECT\n" +
            "\tb.id,a.buxiID AS bxID,b.stuName,a.type,d.kechengName,f.is1v1Class,a.type\n" +
            "FROM\n" +
            "\tpxxuanketable AS a\n" +
            "\tLEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "\tLEFT JOIN pxbuxikechengtable AS c ON a.buxiID = c.id\n" +
            "\tLEFT JOIN pxkechengtable AS d ON c.kechengID = d.id \n" +
            "\tLEFT JOIN pxpaiketable AS e ON a.paikeID = e.id\n" +
            "\tLEFT JOIN pxclasstable AS f ON e.classID = f.id\n" +
            "WHERE\n" +
            "\ta.qiyeID = #{qiyeID} AND a.paikeID = #{paikeID}" + "</script>")
    List<classstuinfoVO> getstuInfoBypaikeID(long paikeID, Long qiyeID);


}