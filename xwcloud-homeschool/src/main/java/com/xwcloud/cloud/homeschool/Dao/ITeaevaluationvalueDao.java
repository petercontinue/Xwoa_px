package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.addtearateVo;
import com.xwcloud.cloud.model.entity.Teaevaluationvalue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-02
 */
@Repository
public interface ITeaevaluationvalueDao extends BaseMapper<Teaevaluationvalue> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "pjid", property = "pjid"),
                @Result(column = "rateid", property = "rateid"),
                @Result(column = "pfvalue", property = "pfvalue"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  teaevaluationvalue"
            + "</script>")
    List<Teaevaluationvalue> getAllList();


    @Select("<script>" +
            "SELECT c.*,a.pfvalue pvalue from teaevaluationvalue a \n" +
            "LEFT JOIN pxevaluationtable b on a.pjid=b.id\n" +
            "LEFT JOIN evaluationpingfen c on a.rateid=c.id\n" +
            "where 1=1 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<addtearateVo> gethavepjrate(@Param("ew")QueryWrapper queryWrapper);
}