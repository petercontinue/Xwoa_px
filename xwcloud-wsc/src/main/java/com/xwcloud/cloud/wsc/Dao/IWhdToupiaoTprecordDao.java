package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.WhdToupiaoRecordVo;
import com.xwcloud.cloud.model.entity.WhdToupiaoTprecord;
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
public interface IWhdToupiaoTprecordDao extends BaseMapper<WhdToupiaoTprecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "canSaiStuID", property = "cansaistuid"),
                @Result(column = "wscUserID", property = "wscuserid"),
                @Result(column = "toupiaoTime", property = "toupiaotime"),
                @Result(column = "toupiaoHuodongID", property = "toupiaohuodongid"),
                @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  whd_toupiao_tprecord"
            + "</script>")
    List<WhdToupiaoTprecord> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.stuName, c.nickName AS userName,d.toupiaoHuodongName\n" +
            "FROM whd_toupiao_tprecord a\n" +
            "JOIN whd_toupiao_cansaistu b ON a.canSaiStuID=b.id\n" +
            "JOIN wsc_user c ON a.wscUserID=c.id\n" +
            "JOIN whd_toupiao_huodong d ON a.toupiaoHuodongID=d.id\n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<WhdToupiaoRecordVo> getWhdToupiaoRecordPage(Page page, @Param("ew") QueryWrapper wrapper);

}