package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.qiandanstaffVO;
import com.xwcloud.cloud.model.entity.Pxqiandanstafftable;
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
 * @since 2020-11-11
 */
@Repository
public interface IPxqiandanstafftableDao extends BaseMapper<Pxqiandanstafftable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "yejiMoney", property = "yejiMoney"),
            @Result(column = "yejidateTime", property = "yejidateTime"),
            @Result(column = "isWeikuan", property = "isWeikuan"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandanstafftable"
            + "</script>")
    List<Pxqiandanstafftable> getAllList();

    @Select("<script>" + "SELECT * FROM pxqiandanstafftable WHERE qiandanID=#{qiandanID}" + "</script>")
    List<Pxqiandanstafftable> GetQiandanStaffByQiandanID(Long qiandanID);

    @Select("<script>" + "DELETE * FROM pxqiandanstafftable WHERE qiandanID=#{qiandanID}" + "</script>")
    int dleteQiandanStaffbyQiandanID(Long qiandanID);

    /**
     * 根据签单ID查询签单业绩人信息
     *
     * @param qiandanID
     * @return
     */
    @Select("<script>" + "select a.staffID AS staffID,b.staffName AS staffName,a.yejiMoney AS yejiMoney from pxqiandanstafftable AS a LEFT JOIN pxstafftable AS b on a.staffID = b.id WHERE a.qiandanID=#{qiandanID}" + "</script>")
    List<qiandanstaffVO> GetqiandanStaffList(long qiandanID);

    /**
     * 补交尾款时获取签单人信息
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "select a.staffID,b.staffName from pxqiandanstafftable a LEFT JOIN pxstafftable b on a.staffID=b.id " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, Object>> getyejitrentoPayweikuan(@Param("ew") QueryWrapper queryWrapper);
}