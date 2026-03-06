package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.OA.OaYingjianbuyrecord;
import com.xwcloud.cloud.model.OA.Vo.YingjianInfoVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
@Repository
public interface IOaYingjianbuyrecordDao extends BaseMapper<OaYingjianbuyrecord> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "yingjianID", property = "yingjianID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "buyNum", property = "buyNum"),
            @Result(column = "price", property = "price"),
            @Result(column = "totalMoney", property = "totalMoney"),
            @Result(column = "xiadanState", property = "xiadanState"),
            @Result(column = "yingjianLiushuiID", property = "yingjianLiushuiID"),
            @Result(column = "yingjianBuyUser", property = "yingjianBuyUser"),
            @Result(column = "yingjianBuyTime", property = "yingjianBuyTime"),
            @Result(column = "addUser", property = "addUser"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "shuoming", property = "shuoming"),
    })
    @Select("<script>" +
            "SELECT * from  oa_yingjianbuyrecord"
            + "</script>")
    List<OaYingjianbuyrecord> getAllList();

    @Results(id = "YingjianrecordInfo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "yingjianTypeName", property = "yingjianTypeName"),
            @Result(column = "kehucompanyname", property = "kehucompanyname"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "buyNum", property = "buyNum"),
            @Result(column = "price", property = "price"),
            @Result(column = "totalMoney", property = "totalMoney"),
            @Result(column = "xiadanState", property = "xiadanState"),
            @Result(column = "yingjianLiushuiID", property = "yingjianLiushuiID"),
            @Result(column = "yingjianBuyTime", property = "yingjianBuyTime"),
            @Result(column = "staffName", property = "staffName"),
            @Result(column = "buyUser", property = "buyUser"),
            @Result(column = "taobaoID", property = "taobaoID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "yingjianID", property = "yingjianID"),
            @Result(column = "yingjianBuyUser", property = "yingjianBuyUser"),
    })
    @Select("<script>" + "SELECT yjbuyrecord.id,\n" +
            "yjbuyrecord.qiandanID,\n" +
            "yjbuyrecord.buyNum,\n" +
            "yjbuyrecord.price,\n" +
            "yjbuyrecord.totalMoney,\n" +
            "yjbuyrecord.xiadanState,\n" +
            "yjbuyrecord.yingjianLiushuiID,\n" +
            "yjbuyrecord.qiyeID,\n" +
            "yjbuyrecord.taobaoID,\n" +
            "yjbuyrecord.yingjianBuyTime,\n" +
            "yjbuyrecord.addTime,\n" +
            "yjbuyrecord.shuoming,\n" +
            "yjbuyrecord.yingjianID,\n" +
            "yjbuyrecord.yingjianBuyUser,\n" +
            "yjtype.yingjianTypeName,\n" +
            "\n" +
            "kehu.kehucompanyname,\n" +
            "staff.staffName as buyUser,\n" +
            "staff2.staffName\n" +
            "from oa_yingjianbuyrecord yjbuyrecord\n" +
            "LEFT JOIN oa_yingjiantype yjtype on yjbuyrecord.yingjianID=yjtype.id\n" +
            "LEFT JOIN oa_kehu kehu on yjbuyrecord.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff staff on yjbuyrecord.yingjianBuyUser=staff.id\n " +
            "LEFT JOIN oa_staff staff2 on yjbuyrecord.addUser=staff2.id\n "
            + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" + "</script>")
        //分页获取所有的硬件购买记录
    IPage<YingjianInfoVo> getAllYingjianrecordInfo(Page<YingjianInfoVo> page, @Param("ew") Wrapper wrapper);

    @ResultMap(value = "YingjianrecordInfo")
    @Select("SELECT yjbuyrecord.id,\n" +
            "yjbuyrecord.qiandanID,\n" +
            "yjbuyrecord.buyNum,\n" +
            "yjbuyrecord.price,\n" +
            "yjbuyrecord.totalMoney,\n" +
            "yjbuyrecord.xiadanState,\n" +
            "yjbuyrecord.yingjianLiushuiID,\n" +
            "yjbuyrecord.qiyeID,\n" +
            "yjbuyrecord.taobaoID,\n" +
            "yjbuyrecord.yingjianBuyTime,\n" +
            "yjbuyrecord.addTime,\n" +
            "yjbuyrecord.shuoming,\n" +
            "yjbuyrecord.yingjianID,\n" +
            "yjbuyrecord.yingjianBuyUser,\n" +
            "yjtype.yingjianTypeName,\n" +
            "\n" +
            "kehu.kehucompanyname,\n" +
            "staff.staffName as buyUser,\n" +
            "staff2.staffName\n" +
            "from oa_yingjianbuyrecord yjbuyrecord\n" +
            "LEFT JOIN oa_yingjiantype yjtype on yjbuyrecord.yingjianID=yjtype.id\n" +
            "LEFT JOIN oa_kehu kehu on yjbuyrecord.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff staff on yjbuyrecord.yingjianBuyUser=staff.id\n " +
            "LEFT JOIN oa_staff staff2 on yjbuyrecord.addUser=staff2.id\n " +
            " where yjbuyrecord.id=#{id}")
    YingjianInfoVo getOneYingjianrecordById(Long id);

}