package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.SumbxRemainVo;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
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
 * @since 2021-08-16
 */
@Repository
public interface IPxbuxikechengtableDao extends BaseMapper<Pxbuxikechengtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "originalprice", property = "originalprice"),
                @Result(column = "kechengprice", property = "kechengprice"),
                @Result(column = "remainkeshi", property = "remainkeshi"),
                @Result(column = "keshiNum", property = "keshiNum"),
                @Result(column = "zongjia", property = "zongjia"),
                @Result(column = "startDate", property = "startDate"),
                @Result(column = "endDate", property = "endDate"),
                @Result(column = "buykeshiDateTime", property = "buykeshiDateTime"),
                @Result(column = "isShow", property = "isShow"),
                @Result(column = "jifeiStyleID", property = "jifeiStyleID"),
                @Result(column = "type", property = "type"),
                @Result(column = "qianDanSubjectID", property = "qianDanSubjectID"),
                @Result(column = "qianDanInfoID", property = "qianDanInfoID"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "shareBuxiID", property = "shareBuxiID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbuxikechengtable"
            + "</script>")
    List<Pxbuxikechengtable> getAllList();

    /**
     * 人工扣课时时
     * 获取学员课程的全部课时
     *
     * @return
     */
    @Select("<script>" +
            "SELECT sum(remainkeshi) as SumR from pxbuxikechengtable where stuID=#{stuID} and kechengID=#{kechengID} and qiyeID=#{qiyeID} "
            + "</script>")
    List<SumbxRemainVo> getSumRks(Long stuID, Long kechengID, Long qiyeID);

    /**
     * 人工消课
     * 家长推送孩子的剩余课时
     *
     * @param stuID
     * @return
     */
    @Select("<script>" +
            "SELECT sum(remainkeshi) as SumR from pxbuxikechengtable where stuID=#{stuID} "
            + "</script>")
    List<SumbxRemainVo> getSumzongRks(Long stuID);
}