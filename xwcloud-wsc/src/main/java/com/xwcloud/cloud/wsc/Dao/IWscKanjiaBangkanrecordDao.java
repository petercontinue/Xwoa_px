package com.xwcloud.cloud.wsc.Dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.WscKanHelpRecordVo;
import com.xwcloud.cloud.model.entity.WscKanjiaBangkanrecord;
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
public interface IWscKanjiaBangkanrecordDao extends BaseMapper<WscKanjiaBangkanrecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "kanjiaFaqiID", property = "kanjiafaqiid"),
                @Result(column = "bangkanrenWxUserID", property = "bangkanrenwxuserid"),
                @Result(column = "kanMoney", property = "kanmoney"),
                @Result(column = "addTime", property = "addtime"),
                @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_kanjia_bangkanrecord"
            + "</script>")
    List<WscKanjiaBangkanrecord> getAllList();

    @Select("<script>" +
            "SELECT a.*, b.userName\n" +
            "FROM wsc_kanjia_bangkanrecord a\n" +
            "JOIN wsc_user b ON a.bangkanrenWxUserID = b.id\n"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    Page<WscKanHelpRecordVo> getWscKanHelpRecordPage(Page page, @Param("ew") QueryWrapper wrapper);

    /**
     * 查询砍价活动价的帮忙砍价信息
     * @param kanjiaGoodsID
     * @return
     */
    @Select("<script>"+"SELECT a.*,d.nickName as faqiName,b.nickName AS bankanName FROM wsc_kanjia_bangkanrecord AS a LEFT JOIN wsc_user AS b ON a.bangkanrenWxUserID = b.id \n" +
            "LEFT JOIN wsc_kanjia_faqirecord AS c ON a.kanjiaFaqiID = c.id LEFT JOIN wsc_user AS d ON c.kanjiaFaqiRenWxUserID = d.id\n" +
            "WHERE c.kanjiaGoodsID = #{kanjiaGoodsID}"+"</script>")
    List<WscKanjiaBangkanrecord> GetBangkanRecords(long kanjiaGoodsID);

}