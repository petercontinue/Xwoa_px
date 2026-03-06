package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscUser;
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
 * @since 2021-08-11
 */
@Repository
public interface IWscUserDao extends BaseMapper<WscUser> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "nickName", property = "nickName"),
                @Result(column = "openid", property = "openid"),
                @Result(column = "unionid", property = "unionid"),
                @Result(column = "phoneNumber", property = "phoneNumber"),
                @Result(column = "headImage", property = "headImage"),
                @Result(column = "sex", property = "sex"),
                @Result(column = "diqu", property = "diqu"),
                @Result(column = "addr", property = "addr"),
                @Result(column = "userType", property = "userType"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "isdongjie", property = "isdongjie"),
                @Result(column = "isKcUser", property = "isKcUser"),
                @Result(column = "fid", property = "fid"),
                @Result(column = "gfid", property = "gfid"),
                @Result(column = "scRemainMoney", property = "scRemainMoney"),
                @Result(column = "scJifen", property = "scJifen"),
                @Result(column = "scRemainyongjin", property = "scRemainyongjin"),
                @Result(column = "scWeijieYongjin", property = "scWeijieYongjin"),
                @Result(column = "scYijieYongjin", property = "scYijieYongjin"),
                @Result(column = "smsRemain", property = "smsRemain"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_user"
            + "</script>")
    List<WscUser> getAllList();
}