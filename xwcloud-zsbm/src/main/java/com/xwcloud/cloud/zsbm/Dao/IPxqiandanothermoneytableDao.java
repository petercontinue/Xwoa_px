package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.buyZafeiVo;
import com.xwcloud.cloud.model.entity.Pxqiandanothermoneytable;

import com.xwcloud.cloud.model.Vo.searchVO;
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
 * @since 2020-11-11
 */
@Repository
public interface IPxqiandanothermoneytableDao extends BaseMapper<Pxqiandanothermoneytable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "otherMoneyName", property = "otherMoneyName"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandanothermoneytable"
            + "</script>")
    List<Pxqiandanothermoneytable> getAllList();

    @Select("<script>"+"SELECT a.id,a.otherMoneyName AS name from pxqiandanothermoneytable AS a WHERE a.qiyeID=#{qiyeID}"+"</script>")
    List<searchVO> GetQiandanOtherMoneyList(long qiyeID);

    /**
     * 查询签单对应的杂费信息
     * @param qiandanID
     * @return
     */
    @Select("<script>"+"SELECT b.otherMoneyName AS zafeiName, a.zongMoney AS zafeiZongjia,b.id AS ZafeiID FROM pxqiandaninfo2table AS a LEFT JOIN pxqiandanothermoneytable AS b on a.qianDanOtherMoneyID = b.id" +
            " LEFT JOIN pxqiandaninfotable AS c ON a.qianInfoTabID = c.id WHERE a.id = #{qiandanID}"+"</script>")
    List<buyZafeiVo> GetQiandanZafeiList(long qiandanID);
}