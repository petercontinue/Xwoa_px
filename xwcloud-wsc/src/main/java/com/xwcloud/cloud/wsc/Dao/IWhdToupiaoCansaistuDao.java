package com.xwcloud.cloud.wsc.Dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.WhdToupiaoCansaiStuVo;
import com.xwcloud.cloud.model.entity.WhdToupiaoCansaistu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Repository
public interface IWhdToupiaoCansaistuDao extends BaseMapper<WhdToupiaoCansaistu> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuName", property = "stuname"),
                @Result(column = "xuanYan", property = "xuanyan"),
                @Result(column = "introduction", property = "introduction"),
                @Result(column = "logo", property = "logo"),
                @Result(column = "lookTimes", property = "looktimes"),
                @Result(column = "piaoshu", property = "piaoshu"),
                @Result(column = "toupiaoHuodongID", property = "toupiaohuodongid"),
                @Result(column = "addUser", property = "adduser"),
                @Result(column = "addTime", property = "addtime"),
                @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  whd_toupiao_cansaistu"
            + "</script>")
    List<WhdToupiaoCansaistu> getAllList();

    @Select("<script>" +
            "SELECT a.*,b.toupiaoHuodongName,c.staffName\n" +
            "FROM whd_toupiao_cansaistu a\n" +
            "JOIN whd_toupiao_huodong b ON a.toupiaoHuodongID=b.id\n" +
            "JOIN pxstafftable c ON a.addUser=c.id\n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<WhdToupiaoCansaiStuVo> getWhdToupiaoCansaiStuPage(Page page, @Param("ew") QueryWrapper Wrapper);


}