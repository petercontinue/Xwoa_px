package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;
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
 * @since 2020-11-25
 */
@Repository
public interface IPxsubjecttableDao extends BaseMapper<Pxsubjecttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "subjectName", property = "subjectName"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxsubjecttable"
            + "</script>")
    List<Pxsubjecttable> getAllList();

    @Select("<script>" +
            "SELECT a.id id ,a.subjectName name from  pxsubjecttable a " +
            "join pxcampustable b ON a.campusID = b.id " +
            "where b.isOpen !=2 and a.qiyeID=#{qiyeID} "
            + "</script>")
    List<listVo> getallkemuName(Long qiyeID);

    @Select("<script>" +
            "SELECT * from  pxsubjecttable where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxsubjecttable> selectsub(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT a.id id ,a.subjectName name from pxsubjecttable a where a.campusID=#{campusID}  and a.qiyeID=#{qiyeID} "
            + "</script>")
    List<listVo> GetcampusIDkemu(Long campusID, Long qiyeID);

}