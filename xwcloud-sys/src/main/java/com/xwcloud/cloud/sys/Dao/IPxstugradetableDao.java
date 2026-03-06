package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstugradetable;
import com.xwcloud.cloud.model.entity.Pxstutable;
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
 * @since 2020-10-21
 */
@Repository
public interface IPxstugradetableDao extends BaseMapper<Pxstugradetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuGradeName", property = "stuGradeName"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstugradetable"
            + "</script>")
    List<Pxstugradetable> getAllList();

    /**
     * 查询该企业ID对应的年级是否存在
     * @param qiyeID
     * @param stuGradeName
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxstugradetable WHERE qiyeID = #{qiyeID} AND stuGradeName = #{stuGradeName}"+"</script>")
    List<Pxstugradetable> GetStuGradeListByqiyeIDAndGrade(Long qiyeID,String stuGradeName);

    /**
     *查询当前年级报名学生
     * @param qiyeID
     * @param stuGradeID
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxstutable WHERE qiyeID = #{qiyeID} AND stuGradeID = #{stuGradeID}"+"</script>")
    List<Pxstutable> GetstuByqiyeIDAndgradeID(Long qiyeID, String stuGradeID);
}