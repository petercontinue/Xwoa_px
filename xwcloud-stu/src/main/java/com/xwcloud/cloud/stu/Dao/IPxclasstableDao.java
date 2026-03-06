package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.*;

import com.xwcloud.cloud.model.entity.Pxclasstable;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
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
 * @since 2020-11-13
 */
@Repository
public interface IPxclasstableDao extends BaseMapper<Pxclasstable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "zidingyiClassID", property = "zidingyiClassID"),
            @Result(column = "className", property = "className"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "maxStuNum", property = "maxStuNum"),
            @Result(column = "is1v1Class", property = "is1v1Class"),
            @Result(column = "isdelete", property = "isdelete"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "classState", property = "classState"),
    })
    @Select("<script>" +
            "SELECT * from  pxclasstable"
            + "</script>")
    List<Pxclasstable> getAllList();

    @Select("<script>" +
            "SELECT * from  pxclasstable where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxclasstable> selectclass(@Param("ew") QueryWrapper queryWrapper);


    @Select("<script>" +
            "update pxclasstable set className=replace(className,#{oldstuName},#{newstuName}) where qiyeID=#{qiyeID}"
            + "</script>")
    List<Pxclasstable> updatestuOyOclass(String oldstuName, String newstuName,Long qiyeID);

    @Select("<script>" +
            "SELECT a.id id,a.className name from  pxclasstable a left join pxcampustable b on a.campusID=b.id where b.isOpen!=2 " + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<listVo> getAllClass(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT a.id id,a.className className,e.campusName campusName\n" +
            "from pxclasstable a \n" +
            "LEFT JOIN pxstuclasstable b on a.id=b.classID\n" +
            "LEFT JOIN pxbuxikechengtable c on b.buxiID=c.id\n" +
            "LEFT JOIN pxstutable d on c.stuID=d.id\n" +
            "LEFT JOIN pxcampustable e on a.campusID=e.id\n" +
            "WHERE e.isOpen!=2 and c.stuID=#{stuID} and a.qiyeID=#{qiyeID}"
            + "</script>")
    Page<LookstuClassVo> getstuclassPage(Page page, Long stuID, Long qiyeID);


    //学员班级
    @Select("<script>" +
            "select  cl.id as id,cl.isShow as isShow,pxcampustable.campusName as campusName,cl.className as className,cl.is1v1Class as is1v1Class,cl.zidingyiClassID zidingyiClassID," +
            "(select count(bx.id) from pxbuxikechengtable as bx " +
            "LEFT JOIN pxstuclasstable on bx.id=pxstuclasstable.buxiID " +
            "LEFT JOIN pxstutable as stu on bx.stuID=stu.id " +
            "where pxstuclasstable.classID=cl.id and (stu.buxiStateID=1 OR stu.buxiStateID=2 OR stu.buxiStateID=3 or stu.buxiStateID=6) ) as mingdan," +
            "cl.maxStuNum as maxStuNum,pxstafftable.staffName as jingbanren,cl.addTime as addTime," +
            "(select pxkechengtable.kechengName from pxbuxikechengtable as bs LEFT JOIN pxstuclasstable on bs.id= pxstuclasstable.buxiID LEFT JOIN pxkechengtable on bs.kechengID=pxkechengtable.id where pxstuclasstable.classID=cl.id and cl.is1v1Class=1 ) as Kc1v1Name," +
            "\t(\n" +
            "SELECT  c.type from pxclasstable a \n" +
            "LEFT JOIN pxstuclasstable b on a.id =b.classID\n" +
            "LEFT join pxbuxikechengtable c on b.buxiID=c.id\n" +
            "where a.is1v1Class=1 and a.qiyeID=cl.qiyeID and a.id=cl.id\n" +
            "\t) classtype " +
            "from pxclasstable as cl " +
            "LEFT JOIN pxcampustable on cl.campusID=pxcampustable.id " +
            "LEFT JOIN pxstafftable on cl.addStaffID=pxstafftable.id " +
            " WHERE pxcampustable.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<classTyleVo> getStuClass(Page page, @Param("ew") QueryWrapper queryWrapper);

    //修改1v1学员班级名称查重
    @Select("<script>" + "select * from pxclasstable where id=#{classID} and className=#{className} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxclasstable> getOtOName(Long classID, String className, Long qiyeID);

    //添加时学员班级（班课），编号查重
    @Select("<script>" + "select * from pxclasstable where is1v1Class!=1 and zidingyiClassID=#{zdID} and qiyeID=#{qiyeID} " + "</script>")
    List<Pxclasstable> getClasszdID(String zdID, Long qiyeID);

    /**
     * 自定义班级id（班级编号）查重
     */
    @Select("<script>" + "select * from pxclasstable where zidingyiClassID=#{zdID} and qiyeID=#{qiyeID} " + "</script>")
    List<Pxclasstable> getCfzdID(String zdID, Long qiyeID);

    /**
     * 获取全部可用的班课
     */
    @Select("<script>" + "select id id,className name from pxclasstable where isdelete!=true and is1v1Class !=1  and isShow=1 and qiyeID=#{qiyeID} and campusID=#{campusID}" + "</script>")
    List<listVo> getallbanke(Long qiyeID,String campusID);

    @Select("<script>" + "select * from pxclasstable where is1v1Class!=1 and zidingyiClassID=#{zdID} and qiyeID=#{qiyeID} and id !=#{id} " + "</script>")
    List<Pxclasstable> getupdateClasszdID(String zdID, Long qiyeID, Long id);

    //以班级名称查询非1对1
    @Select("<script>" + "select * from pxclasstable where is1v1Class!=1 and className=#{className} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxclasstable> getClassName(String className, Long qiyeID);

    @Select("<script>" + "select * from pxclasstable where is1v1Class!=1 and className=#{className} and qiyeID=#{qiyeID} and id !=#{id}  " + "</script>")
    List<Pxclasstable> getupdateClassName(String className, Long qiyeID, Long id);

    //以班级名称查询
    @Select("<script>" + "select * from pxclasstable where className=#{className} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxclasstable> FkClassName(String className, Long qiyeID);

    //修改时获取班级启用状态
    @Select("<script>" + "select * from pxclasstable where is1v1Class=#{Type} and id=#{classID} qiyeID=#{qiyeID}" + "</script>")
    Pxclasstable getShow(int Type, Long classID, Long qiyeID);

    //导出学员一对一班级
    @Select("<script>" +
            "select  cl.id as id,(case WHEN cl.isShow=0 THEN '不启用' ELSE (case WHEN cl.isShow=1 THEN '启用' ELSE '-' END) END) as isShow,pxcampustable.campusName as campusName,cl.className as className,cl.is1v1Class as is1v1Class," +
            "(select count(bx.id) from pxbuxikechengtable as bx " +
            "LEFT JOIN pxstuclasstable on bx.id=pxstuclasstable.buxiID " +
            "LEFT JOIN pxstutable as stu on bx.stuID=stu.id " +
            "where pxstuclasstable.classID=cl.id and (stu.buxiStateID=1 OR stu.buxiStateID=2 OR stu.buxiStateID=3 or stu.buxiStateID=6) ) as mingdan," +
            "(select GROUP_CONCAT(stuName) stuName from pxbuxikechengtable as bx " +
            "LEFT JOIN pxstuclasstable on bx.id=pxstuclasstable.buxiID " +
            "LEFT JOIN pxstutable as stu on bx.stuID=stu.id " +
            "where pxstuclasstable.classID=cl.id and (stu.buxiStateID=1 OR stu.buxiStateID=2 OR stu.buxiStateID=3 or stu.buxiStateID=6) ) as mingdanstu," +
            "cl.maxStuNum as maxStuNum,pxstafftable.staffName as jingbanren,cl.addTime as addTime," +
            "(select pxkechengtable.kechengName from pxbuxikechengtable as bs LEFT JOIN pxstuclasstable on bs.id= pxstuclasstable.buxiID LEFT JOIN pxkechengtable on bs.kechengID=pxkechengtable.id where pxstuclasstable.classID=cl.id and cl.is1v1Class=1 ) as Kc1v1Name " +
            "from pxclasstable as cl " +
            "LEFT JOIN pxcampustable on cl.campusID=pxcampustable.id " +
            "LEFT JOIN pxstafftable on cl.addStaffID=pxstafftable.id " +
            " WHERE pxcampustable.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<ExportClassVo> getClassInfoOtO(@Param("ew") QueryWrapper queryWrapper);

    //查询学员班级
    @Select("<script>" +
            "select pxclasstable.id as classID, pxclasstable.zidingyiClassID as zidingyiClassID,pxclasstable.className as className,pxclasstable.isShow as isShow,pxkechengtable.kechengName as kechengName " +
            "from pxbuxikechengtable as bx  " +
            "LEFT JOIN pxstuclasstable on bx.id=pxstuclasstable.buxiID " +
            "LEFT JOIN pxclasstable on pxstuclasstable.classID=pxclasstable.id " +
            "LEFT JOIN pxkechengtable on bx.kechengID=pxkechengtable.id " +
            "LEFT JOIN pxcampustable on pxclasstable.campusID=pxcampustable.id " +
            "WHERE pxcampustable.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<stuClassVo> getClassToStu(Page page, @Param("ew") QueryWrapper queryWrapper);

    //删除学员班级
    @Select("<script>" + "select * from pxkeshistutable where classID=#{classID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxkeshistutable> getKeHao(Long classID, Long qiyeID);

    //按照班级ID获取排课ID的集合
    @Select("<script>" + "select GROUP_CONCAT(id) as IDS from pxpaiketable where classID=#{classID} and qiyeID=#{qiyeID}" + "</script>")
    paikeVo getPaiKe(@Param("classID") Long classID, Long qiyeID);

    @Select("<script>" +
            "SELECT id ,className name from pxclasstable WHERE is1v1Class !=1 and isdelete !=TRUE and campusID=#{campusID} and qiyeID =#{qiyeID}" +
            "</script>")
    List<listVo> getkxqclass(Long campusID, Long qiyeID);
}