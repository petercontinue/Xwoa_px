package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstukxqtable;
import com.xwcloud.cloud.model.Vo.listVo;
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
 * @since 2020-11-29
 */
@Repository
public interface IPxstukxqtableDao extends BaseMapper<Pxstukxqtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "bxkcID", property = "bxkcID"),
            @Result(column = "kcID", property = "kcID"),
            @Result(column = "kxqCampusID", property = "kxqCampusID"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstukxqtable"
            + "</script>")
    List<Pxstukxqtable> getAllList();

    @Select("<script>" +
            "SELECT * from  pxstukxqtable where stuID=#{stuID} and bxkcID=#{bxkcID} and kxqCampusID=#{kxqID} and qiyeID=#{qiyeID}"
            + "</script>")
    List<Pxstukxqtable> getBystubx(Long stuID, Long bxkcID, Long kxqID,Long qiyeID);

    @Select("<script>" +
            "SELECT a.stuID id ,concat(c.campusName,'-',b.stuName) name\n" +
            "from pxstukxqtable a \n" +
            "LEFT JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable c on b.campusID=c.id \n" +
            "where c.isOpen !=2 and a.qiyeID=#{qiyeID} " +
            "GROUP BY a.stuID "
            + "</script>")
    List<listVo> Getkxqstu(Long qiyeID);

    @Select("<script>" +
            "SELECT a.bxkcID id ,b.kechengName name from pxstukxqtable a\n" +
            "LEFT JOIN pxkechengtable b on a.kcID=b.id\n" +
            "where a.stuID=#{stuID} and a.qiyeID=#{qiyeID} " +
            "GROUP BY a.bxkcID "
            + "</script>")
    List<listVo> Getkxqbxkecheng(Long stuID, Long qiyeID);


    @Select("<script>" +
            "SELECT b.id id ,b.campusName name from pxstukxqtable a \n" +
            "LEFT JOIN pxcampustable b on a.kxqCampusID=b.id\n" +
            "where a.bxkcID=#{buxiID} and b.isOpen !=2 and a.qiyeID=#{qiyeID} "+
            "GROUP BY a.kxqCampusID "
            + "</script>")
    List<listVo> GetKxqCampus(Long buxiID,Long qiyeID);
}