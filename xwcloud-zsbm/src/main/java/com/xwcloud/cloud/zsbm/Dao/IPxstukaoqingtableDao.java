package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstukaoqingtable;
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
 * @since 2020-11-18
 */
@Repository
public interface IPxstukaoqingtableDao extends BaseMapper<Pxstukaoqingtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "haveclassDate", property = "haveclassDate"),
            @Result(column = "startClassDateTime", property = "startClassDateTime"),
            @Result(column = "endClassDateTime", property = "endClassDateTime"),
            @Result(column = "kaoqingStyle", property = "kaoqingStyle"),
            @Result(column = "kaoqingBeizhu", property = "kaoqingBeizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstukaoqingtable"
            + "</script>")
    List<Pxstukaoqingtable> getAllList();

    @Select("<script>" + "SELECT * FROM pxstukaoqingtable where haveclassDate=#{haveclassDate} AND startClassDateTime=#{startClassDateTime} AND endClassDateTime=#{endClassDateTime} AND classID=#{classID} AND teacherNames=#{teacherNames}" + "</script>")
    List<Pxstukaoqingtable> GetStuKaoqing(String haveclassDate, String startClassDateTime, String endClassDateTime, Long classID, String teacherNames);

    @Select("<script>" + "SELECT * FROM pxstukaoqingtable WHERE stuID=#{stuID}" + "</script>")
    List<Pxstukaoqingtable> GetKaoqingByStuID(Long stuID);

    @Select("<script>" + "DELETE FROM Pxstukaoqingtable where  stuID=#{stuID}" + "</script>")
    int deleteStuKaoqingByStuID(Long stuID);
}