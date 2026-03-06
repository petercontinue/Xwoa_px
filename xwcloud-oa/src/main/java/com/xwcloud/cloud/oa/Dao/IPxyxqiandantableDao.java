package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxyxqiandantable;
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
public interface IPxyxqiandantableDao extends BaseMapper<Pxyxqiandantable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "qianDanMoney", property = "qianDanMoney"),
                @Result(column = "isBaomingOrChongzhi", property = "isBaomingOrChongzhi"),
                @Result(column = "yxShichangRenID", property = "yxShichangRenID"),
                @Result(column = "yxDengjiRenID", property = "yxDengjiRenID"),
                @Result(column = "yxFuzheRenID", property = "yxFuzheRenID"),
                @Result(column = "yxQiandanRenID", property = "yxQiandanRenID"),
                @Result(column = "yxDengjiDateTime", property = "yxDengjiDateTime"),
                @Result(column = "yxFenpeiDateTime", property = "yxFenpeiDateTime"),
                @Result(column = "yxQiandanDateTime", property = "yxQiandanDateTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxqiandantable"
            + "</script>")
    List<Pxyxqiandantable> getAllList();
}