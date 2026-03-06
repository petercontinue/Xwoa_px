package com.xwcloud.cloud.wsc.Dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.huodongdetailVO;
import com.xwcloud.cloud.model.Vo.mymobanVO;
import com.xwcloud.cloud.model.entity.WhdH5Huodongfabu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-19
 */
@Repository
public interface IWhdH5HuodongfabuDao extends BaseMapper<WhdH5Huodongfabu> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "mobanID", property = "mobanID"),
                @Result(column = "mbTypeID", property = "mbTypeID"),
                @Result(column = "zixunEwm", property = "zixunEwm"),
                @Result(column = "huodongTitle", property = "huodongTitle"),
                @Result(column = "huodongImage", property = "huodongImage"),
                @Result(column = "jigouName", property = "jigouName"),
                @Result(column = "huodongStartDateTime",property = "huodongStartDateTime"),
                @Result(column = "huodongEndDateTime", property = "huodongEndDateTime"),
                @Result(column = "maxStuNum", property = "maxStuNum"),
                @Result(column = "jigouTel", property = "jigouTel"),
                @Result(column = "musicID", property = "musicID"),
                @Result(column = "haveStuSex", property = "haveStuSex"),
                @Result(column = "haveAge", property = "haveAge"),
                @Result(column = "haveBirthday", property = "haveBirthday"),
                @Result(column = "haveSchool", property = "haveSchool"),
                @Result(column = "haveGrade", property = "haveGrade"),
                @Result(column = "haveYxkecheng", property = "haveYxkecheng"),
                @Result(column = "huodongShuoMing", property = "huodongShuoMing"),
                @Result(column = "lookNum", property = "lookNum"),
                @Result(column = "baomingStuNum", property = "baomingStuNum"),
                @Result(column = "isfabu", property = "isfabu"),
                @Result(column = "fabuTime", property = "fabuTime"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_h5_huodongfabu"
            + "</script>")
    List<WhdH5Huodongfabu> getAllList();

    /**
     * 分页查询我的招学秀信息
     *
     * @return
     */
    @Select("<script>" + "select * from whd_h5_huodongfabu AS a LEFT JOIN whd_h5_moban AS b on a.mobanID = b.id " +
            "LEFT JOIN wsc_huodong AS c ON a.mbTypeID = c.id WHERE a.qiyeID = #{qiyeID}" + "</script>")
    Page<mymobanVO> GetMyzhaoxuexiuMobanPages(Page page, long qiyeID);

    @Select("<script>"+"SELECT * FROM whd_h5_huodongfabu AS a LEFT JOIN whd_h5_huodongfabu_jigoujianjie AS b ON a.id= b.whd_h5_huodongfabu_id " +
            "WHERE a.id = #{huodongID} AND a.qiyeID=#{qiyeID}"+"</script>")
    huodongdetailVO getHuodongDetailInfo(long qiyeID,long huodongID);

    @Select("<script>"+"SELECT a.*,(UNIX_TIMESTAMP(a.huodongEndDateTime)-UNIX_TIMESTAMP(NOW())) as shijianchuo FROM whd_h5_huodongfabu AS a "+ "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"+
            "</script>")
    List<HashMap<String, Object>> GetMubanHuodongDetail(@Param("ew") QueryWrapper wrapper);

}