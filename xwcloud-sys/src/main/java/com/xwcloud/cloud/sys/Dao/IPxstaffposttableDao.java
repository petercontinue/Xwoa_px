package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.cxStaffpostVO;
import com.xwcloud.cloud.model.Vo.staffpostVo;
import com.xwcloud.cloud.model.entity.Pxstaffposttable;
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
 * @author yinqi
 * @since 2020-10-20
 */
@Repository
public interface IPxstaffposttableDao extends BaseMapper<Pxstaffposttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffpostName", property = "staffpostName"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstaffposttable"
            + "</script>")
    List<Pxstaffposttable> getAllList();

    /**
     * 分页查询岗位信息
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>"+"SELECT a.id,a.staffpostName,b.campusName,a.campusID FROM pxstaffposttable as a LEFT JOIN pxcampustable as b on a.campusID=b.id"+  " WHERE 1=1"+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<staffpostVo> getStaffpostList(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 根据校区获取该校区下面的所有岗位
     * @param campusID
     * @return
     */
    @Select("<script>"+"select  a.id,a.staffpostName,b.campusName from  pxstaffposttable AS a LEFT JOIN pxcampustable AS b ON a.campusID = b.id\n" +
            "WHERE b.id =#{campusID}"+"</script>")
    List<staffpostVo> GetStaffPostListByCampusID(long campusID);

    @Select("<script>"+"SELECT a.id,CONCAT(a.staffpostName,'_',b.campusName) AS name FROM pxstaffposttable AS a LEFT JOIN pxcampustable AS b ON a.campusID = b.id WHERE a.qiyeID = #{qiyeID}"+"</script>")
    List<cxStaffpostVO> GetSearchStaffPostList(long qiyeID);
}