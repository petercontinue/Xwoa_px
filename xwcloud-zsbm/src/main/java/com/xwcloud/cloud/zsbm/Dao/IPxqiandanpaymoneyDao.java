package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.Vo.qiandanpaymoneyVO;
import com.xwcloud.cloud.model.entity.Pxqiandanpaymoney;
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
public interface IPxqiandanpaymoneyDao extends BaseMapper<Pxqiandanpaymoney> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "paymoneyStyleID", property = "paymoneyStyleID"),
            @Result(column = "payMoney", property = "payMoney"),
            @Result(column = "qianDanDate", property = "qianDanDate"),
            @Result(column = "isWeikuan", property = "isWeikuan"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandanpaymoney"
            + "</script>")
    List<Pxqiandanpaymoney> getAllList();

    @Select("<script>" + "SELECT * FROM pxqiandanpaymoney WHERE qiandanID=#{qiandanID} AND paymoneyStyleID=#{paymoneyStyleID}" + "</script>")
    Pxqiandanpaymoney GetQiandanPayMoneyStyleByqdID(Long qiandanID, Long paymoneyStyleID);

    @Select("<script>" + "SELECT * FROM pxqiandanpaymoney WHERE qiandanID=#{qiandanID}" + "</script>")
    List<Pxqiandanpaymoney> getQiandanPayMoneyList(Long qiandanID);

    @Select("<script>" + "DELETE * FROM pxqiandanpaymoney WHERE qiandanID=#{qiandanID}" + "</script>")
    int deleteQiandanPayMoneybyqiandanID(Long qiandanID);

    /**
     * 根据签单ID查询签单支付方式和支付金额信息
     *
     * @param qiandanID
     * @return
     */
    @Select("<script>" + "SELECT a.paymoneyStyleID AS paymoneyStyleID,b.moneystyleName AS zhifustyleName,a.payMoney AS payMoney FROM pxqiandanpaymoney AS a" +
            " LEFT JOIN pxpaymoneystyletable AS b ON a.paymoneyStyleID = b.id WHERE a.qiandanID =#{qiandanID}" + "</script>")
    List<qiandanpaymoneyVO> getqiandanPayMoneyList(Long qiandanID);


    @Select("<script>" +
            "select a.id,b.moneystyleName,b.id paymoneyStyleID " +
            " from pxqiandanpaymoney a " +
            "LEFT JOIN pxpaymoneystyletable b on a.paymoneyStyleID=b.id " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, Object>> getPaystyletoPayweikuan(@Param("ew") QueryWrapper queryWrapper);


}