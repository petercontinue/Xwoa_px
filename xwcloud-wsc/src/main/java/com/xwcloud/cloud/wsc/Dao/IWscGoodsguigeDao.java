package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.Vo.goodsInfoVO;
import com.xwcloud.cloud.model.entity.WscGoodsguige;
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
 * @since 2021-01-17
 */
@Repository
public interface IWscGoodsguigeDao extends BaseMapper<WscGoodsguige> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "guigeTypeName", property = "guigetypename"),
            @Result(column = "goodsID", property = "goodsid"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_goodsguige"
            + "</script>")
    List<WscGoodsguige> getAllList();

    /**
     * 查询商品的属性组合信息
     * @param qiyeID
     * @param goodsID
     * @return
     */
    @Select("<script>"+"SELECT a.id,a.guigeTypeName,(SELECT * FROM wsc_goodsshuxinglist AS b ON b.goodsGuigeTypeID = a.id AND b.goodsID = a.goodsID) as WscGoodsshuxinglist  from  wsc_goodsguige AS a " +
            " WHERE a.qiyeID=#{qiyeID} AND a.goodsID = #{goodsID}"+"</script>")
    List<goodsInfoVO> GetgoodsGuigeList(long qiyeID,long goodsID);

    @Select("<script>"+"SELECT id,guigeTypeName,NULL AS WscGoodsshuxinglist FROM wsc_goodsguige WHERE qiyeID = #{qiyeID} AND goodsID = #{goodsID}"+"</script>")
    List<goodsInfoVO> GetAllGoodsGuigeInfo(long qiyeID,long goodsID);
}