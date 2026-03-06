package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-19
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
            "SELECT a.id,CONCAT(b.campusName,'_',a.subjectName) name\n" +
            "FROM pxsubjecttable a\n" +
            "JOIN pxcampustable b ON a.campusID=b.id\n" +
            "<where>" +
            "<if test=\"qiyeID != null and qiyeID !=''\">" +
            " AND ${qiyeID}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<searchVO> getSubject(@Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT a.campusID,a.id subjectID, a.subjectName,d.campusName,COUNT(DISTINCT c.stuID) bmCount,SUM(IFNULL(c.zongjia,0)) bmMoney\n" +
            "FROM pxsubjecttable a\n" +
            "LEFT JOIN pxkechengtable b on a.id=b.subjectID\n" +
            "LEFT JOIN pxqiandansubjecttable c on b.id=c.kechengID\n" +
            "JOIN pxcampustable d ON a.campusID=d.id\n" +
            "LEFT JOIN pxqiandaninfotable e ON c.qianDanInfoID=e.id\n" +
            "WHERE ((e.id IS NULL) OR (e.id IS NOT NULL AND e.moneyStyle=1))\n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY a.campusID,a.id" +
            "</script>")
    Page<HashMap<String, Object>> getSubjectBmTongji(Page page, @Param("ew") QueryWrapper wrapper);
}