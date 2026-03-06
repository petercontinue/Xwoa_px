package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxclasstimestyletable;
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
 * @since 2021-01-11
 */
@Repository
public interface IPxclasstimestyletableDao extends BaseMapper<Pxclasstimestyletable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "classTimeStyleName", property = "classtimestylename"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  pxclasstimestyletable"
            + "</script>")
    List<Pxclasstimestyletable> getAllList();

    /**
     * 查询所有的课程时长信息（查询下拉框绑定）
     * @param qiyeID
     * @return
     */
    @Select("<script>"+"SELECT a.id,case when a.classTimeStyleName=-1 then '一次' when a.classTimeStyleName=-2 then '一天' else CONCAT(a.classTimeStyleName,'分钟') end AS `name` " +
            " FROM pxclasstimestyletable AS a WHERE a.qiyeID = #{qiyeID}"+"</script>")
    List<searchVO> GetAllclasstimestyleList(long qiyeID);
}