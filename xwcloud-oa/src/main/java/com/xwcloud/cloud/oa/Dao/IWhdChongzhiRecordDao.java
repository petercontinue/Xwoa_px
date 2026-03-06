package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdChongzhiRecord;
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
 * @since 2021-08-25
 */
@Repository
public interface IWhdChongzhiRecordDao extends BaseMapper<WhdChongzhiRecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "wscUserID", property = "wscUserID"),
                @Result(column = "czHuodongID", property = "czHuodongID"),
                @Result(column = "chongzhiMoney", property = "chongzhiMoney"),
                @Result(column = "songMoney", property = "songMoney"),
                @Result(column = "shideMoney", property = "shideMoney"),
                @Result(column = "chongzhiShuoming", property = "chongzhiShuoming"),
                @Result(column = "chongzhiTime", property = "chongzhiTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_chongzhi_record"
            + "</script>")
    List<WhdChongzhiRecord> getAllList();
}