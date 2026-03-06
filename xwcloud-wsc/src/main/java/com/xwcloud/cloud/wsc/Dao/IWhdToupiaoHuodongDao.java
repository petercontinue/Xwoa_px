package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.WhdToupiaoHuodongVo;
import com.xwcloud.cloud.model.entity.WhdToupiaoHuodong;
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
 * @since 2021-01-17
 */
@Repository
public interface IWhdToupiaoHuodongDao extends BaseMapper<WhdToupiaoHuodong> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "toupiaoHuodongName", property = "toupiaohuodongname"),
            @Result(column = "miaoshu", property = "miaoshu"),
            @Result(column = "logo", property = "logo"),
            @Result(column = "toupiaoStyle", property = "toupiaostyle"),
            @Result(column = "jiangpin", property = "jiangpin"),
            @Result(column = "rules", property = "rules"),
            @Result(column = "jigouJianjie", property = "jigoujianjie"),
            @Result(column = "addTime", property = "addtime"),
            @Result(column = "addUser", property = "adduser"),
            @Result(column = "startTime", property = "starttime"),
            @Result(column = "endTime", property = "endtime"),
            @Result(column = "isUp", property = "isup"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  whd_toupiao_huodong"
            + "</script>")
    List<WhdToupiaoHuodong> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.staffName,(select COUNT(*) from whd_toupiao_tprecord WHERE toupiaoHuodongID = a.id) AS toupiaoCount\n" +
            "FROM whd_toupiao_huodong a\n" +
            "JOIN pxstafftable b ON a.addUser=b.id\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<WhdToupiaoHuodongVo> getWhdToupiaoHuodongPage(Page page, @Param("ew") QueryWrapper wrapper);

    @Select("<script>" +
            "select COUNT(DISTINCT(wscUserID)) toupiaopeople from whd_toupiao_huodong a \n" +
            "LEFT JOIN whd_toupiao_tprecord b on a.id=b.toupiaoHuodongID \n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    String gettoupaioNum(@Param("ew") QueryWrapper wrapper);


    @Select("<script>" + "SELECT a.*,(UNIX_TIMESTAMP(a.endTime)-UNIX_TIMESTAMP(NOW())) as shijianchuo FROM whd_toupiao_huodong AS a WHERE id = #{toupiaohdID}" + "</script>")
    List<HashMap<String, Object>> GetToupiaoHuodongInfoByID(long toupiaohdID);

}