package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.zuoyeVO;
import com.xwcloud.cloud.model.entity.Pxzuoyetable;
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
 * @since 2021-05-07
 */
@Repository
public interface IPxzuoyetableDao extends BaseMapper<Pxzuoyetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "neirong", property = "neirong"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "zuoyeImg", property = "zuoyeImg"),
            @Result(column = "zuoyeMp3", property = "zuoyeMp3"),
            @Result(column = "zuoyeVideo", property = "zuoyeVideo"),
            @Result(column = "otherFile", property = "otherFile"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxzuoyetable"
            + "</script>")
    List<Pxzuoyetable> getAllList();

    /**
     * 分页查询作业信息
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>"+"SELECT zuoye.id,zuoye.neirong,zuoye.addTime,zuoye.endDate,class.className,staff.staffName,zuoye.zuoyeImg,zuoye.zuoyeMp3,zuoye.zuoyeVideo,zuoye.otherFile FROM pxzuoyetable AS zuoye \n" +
            "LEFT JOIN pxclasstable AS class ON zuoye.classID = class.id\n" +
            "LEFT JOIN pxstafftable AS staff ON zuoye.addStaffID = staff.id\n" +
            "LEFT JOIN pxstuclasstable AS stucla ON zuoye.classID = stucla.classID\n" +
            "LEFT JOIN pxbuxikechengtable AS buxi ON stucla.buxiID = buxi.id\n" +
            "WHERE 1 = 1 "+ "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<zuoyeVO> GetAllstuZuoyeList(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT zuoye.id,zuoye.neirong,zuoye.addTime,zuoye.endDate,class.className,staff.staffName,zuoye.zuoyeImg,zuoye.zuoyeMp3,zuoye.zuoyeVideo,zuoye.otherFile FROM pxzuoyetable AS zuoye \n" +
            "LEFT JOIN pxclasstable AS class ON zuoye.classID = class.id\n" +
            "LEFT JOIN pxstafftable AS staff ON zuoye.addStaffID = staff.id\n" +
            "LEFT JOIN pxstuclasstable AS stucla ON zuoye.classID = stucla.classID\n" +
            "LEFT JOIN pxbuxikechengtable AS buxi ON stucla.buxiID = buxi.id\n" +
            "WHERE 1 = 1 "+ "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +" LIMIT 1"+
            "</script>")
    List<zuoyeVO> GetZuoyexiangqing(@Param("ew") Wrapper wrapper);

}