package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.SumbxRemainVo;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
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
 * @since 2021-07-29
 */
@Repository
public interface IPxbuxikechengtableDao extends BaseMapper<Pxbuxikechengtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "originalprice", property = "originalprice"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "remainkeshi", property = "remainkeshi"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "zongjia", property = "zongjia"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "buykeshiDateTime", property = "buykeshiDateTime"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "jifeiStyleID", property = "jifeiStyleID"),
            @Result(column = "type", property = "type"),
            @Result(column = "qianDanSubjectID", property = "qianDanSubjectID"),
            @Result(column = "qianDanInfoID", property = "qianDanInfoID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "shareBuxiID", property = "shareBuxiID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbuxikechengtable"
            + "</script>")
    List<Pxbuxikechengtable> getAllList();

    @Select("<script>" +
            "SELECT * from pxbuxikechengtable a LEFT JOIN pxstuclasstable b on a.id=b.buxiID where a.isShow=1 and  b.id is null " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            " GROUP BY a.id"
            + "</script>")
    List<Pxbuxikechengtable> getNOClasskc(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT sum(remainkeshi) as SumR from pxbuxikechengtable where stuID=#{stuID} and qiyeID=#{qiyeID} "
            + "</script>")
    List<SumbxRemainVo> getSumzongRks(Long stuID, Long qiyeID);


    @Select("<script>" +
            "SELECT sum(remainkeshi) as SumR from pxbuxikechengtable where stuID=#{stuID} and kechengID=#{kechengID} and qiyeID=#{qiyeID} "
            + "</script>")
    List<SumbxRemainVo> getSumRks(Long stuID, Long kechengID, Long qiyeID);
}