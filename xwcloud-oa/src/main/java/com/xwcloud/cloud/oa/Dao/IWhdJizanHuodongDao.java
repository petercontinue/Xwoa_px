package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdJizanHuodong;
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
public interface IWhdJizanHuodongDao extends BaseMapper<WhdJizanHuodong> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "jizanHuodongName", property = "jizanHuodongName"),
                @Result(column = "jizanLogoUrl", property = "jizanLogoUrl"),
                @Result(column = "jizanShuoming", property = "jizanShuoming"),
                @Result(column = "startTime", property = "startTime"),
                @Result(column = "endTime", property = "endTime"),
                @Result(column = "isOpen", property = "isOpen"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "liulantimes", property = "liulantimes"),
                @Result(column = "fenxiangtimes", property = "fenxiangtimes"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_jizan_huodong"
            + "</script>")
    List<WhdJizanHuodong> getAllList();
}