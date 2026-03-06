package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.tuisongtypeVO;
import com.xwcloud.cloud.model.entity.Pxtuisongtypetable;
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
 * @since 2020-12-21
 */
@Repository
public interface IPxtuisongtypetableDao extends BaseMapper<Pxtuisongtypetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "tuisongType", property = "tuisongtype"),
    })
    @Select("<script>" +
            "SELECT * from  pxtuisongtypetable"
            + "</script>")
    List<Pxtuisongtypetable> getAllList();

    @Select("<script>"+"select id,tuisongType AS name from  pxtuisongtypetable "+"</script>")
    List<tuisongtypeVO> getAllTuisongTypeList();
}