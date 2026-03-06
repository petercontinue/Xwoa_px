package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
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
 * @since 2021-07-29
 */
@Repository
public interface IPxpaiketableDao extends BaseMapper<Pxpaiketable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
                @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
                @Result(column = "haveClassDate", property = "haveClassDate"),
                @Result(column = "teacherIDs", property = "teacherIDs"),
                @Result(column = "zhujiaoID", property = "zhujiaoID"),
                @Result(column = "teacherNames", property = "teacherNames"),
                @Result(column = "classID", property = "classID"),
                @Result(column = "classRoomID", property = "classRoomID"),
                @Result(column = "weekN", property = "weekN"),
                @Result(column = "MaxStuNum", property = "maxStuNum"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "kechengContent", property = "kechengContent"),
                @Result(column = "dakaoqin", property = "dakaoqin"),
                @Result(column = "tags", property = "tags"),
                @Result(column = "canqingjiaBeforeHours", property = "canqingjiaBeforeHours"),
                @Result(column = "istuisongTixingMsg", property = "istuisongTixingMsg"),
                @Result(column = "shuakaTimeArea", property = "shuakaTimeArea"),
                @Result(column = "paikeType", property = "paikeType"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "huodongImg", property = "huodongImg"),
                @Result(column = "huodongTitle", property = "huodongTitle"),
                @Result(column = "huodongshuoming", property = "huodongshuoming"),
                @Result(column = "liulangtime", property = "liulangtime"),
                @Result(column = "fenxiangtime", property = "fenxiangtime"),
                @Result(column = "zixunphone", property = "zixunphone"),
                @Result(column = "shitingprice", property = "shitingprice"),
    })
    @Select("<script>" +
            "SELECT * from  pxpaiketable"
            + "</script>")
    List<Pxpaiketable> getAllList();
}