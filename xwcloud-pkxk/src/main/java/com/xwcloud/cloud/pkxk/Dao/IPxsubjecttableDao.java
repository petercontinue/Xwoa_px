package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;
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

    /**
     * 查询所有科目信息
     * @return
     */
    @Select("<script>"+"SELECT a.id AS id ,a.subjectName AS name FROM pxsubjecttable AS a LEFT JOIN pxcampustable AS b ON a.campusID = b.id WHERE b.isOpen !=2 and a.qiyeID=#{qiyeID}"+"</script>")
    List<searchVO> GetAllKemuList(long qiyeID);
}