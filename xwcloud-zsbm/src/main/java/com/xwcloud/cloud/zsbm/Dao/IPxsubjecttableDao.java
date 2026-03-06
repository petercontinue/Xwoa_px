package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.Vo.subjectVO;
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
 * @since 2020-11-10
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

    @Select("<script>" + "SELECT * FROM pxsubjecttable WHERE id=#{Id} ORDER BY id LIMIT 0,1" + "</script>")
    Pxsubjecttable GetSubjectById(Long Id);

    /**
     * 查询所有科目信息（绑定table下拉查询）
     * @param qiyeID
     * @return
     */
    @Select("<script>"+"SELECT a.id,CONCAT(b.campusName,'_',a.subjectName) AS `name` FROM pxsubjecttable AS a LEFT JOIN pxcampustable AS b ON a.campusID = b.id WHERE a.qiyeID=#{qiyeID}"+"</script>")
    List<searchVO> GetAllSubjectList(long qiyeID);

    @Select("<script>"+"SELECT a.id,a.subjectName AS `name` FROM pxsubjecttable AS a LEFT JOIN pxcampustable AS b ON a.campusID = b.id WHERE a.qiyeID=#{qiyeID} AND\n" +
            "(SELECT COUNT(*) FROM pxkechengtable WHERE subjectID = a.id AND qiyeID = #{qiyeID} AND showInApp = 1)>0"+"</script>")
    List<searchVO> GetAllSubjectListnocampus(long qiyeID);


    /**
     * 根据校区ID查询科目信息
     * @param qiyeID
     * @param campusID
     * @return
     */
    @Select("<script>"+"SELECT a.id,a.subjectName AS `name` FROM pxsubjecttable AS a" +
            " LEFT JOIN pxcampustable AS b ON a.campusID = b.id WHERE a.qiyeID=#{qiyeID}" +"<if test='campusID!=null'>" +
            "AND a.campusID=#{campusID}" +
            "</if>"+
            "</script>")
    List<searchVO> GetAllSubjectByxqIDAndqiyeID(long qiyeID,Long campusID);

    @Select("<script>"+"SELECT a.id,a.subjectName,b.campusName,a.campusID FROM pxsubjecttable AS a LEFT JOIN pxcampustable AS b ON a.campusID=b.id WHERE b.isOpen!=2"+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<subjectVO> getAllsubjectPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT * FROM pxsubjecttable WHERE campusID = #{campusID} AND subjectName=#{subjectName}"+"</script>")
    List<Pxsubjecttable> GetsubjectList(long campusID,String subjectName);
}