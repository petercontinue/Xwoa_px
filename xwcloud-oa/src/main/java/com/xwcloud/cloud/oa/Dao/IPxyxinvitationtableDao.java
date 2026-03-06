package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxyxinvitationtable;
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
public interface IPxyxinvitationtableDao extends BaseMapper<Pxyxinvitationtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "invitationTime", property = "invitationTime"),
                @Result(column = "invitationZhuangtai", property = "invitationZhuangtai"),
                @Result(column = "addTeacher", property = "addTeacher"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "shuoMing", property = "shuoMing"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxinvitationtable"
            + "</script>")
    List<Pxyxinvitationtable> getAllList();
}