
package com.xwcloud.cloud.homeschool.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
 * @since 2021-03-01
 */
@Repository
public interface IPxstutableDao extends BaseMapper<Pxstutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "zidingyiStuID", property = "zidingyiStuID"),
            @Result(column = "stuName", property = "stuName"),
            @Result(column = "parentTel", property = "parentTel"),
            @Result(column = "passwd", property = "passwd"),
            @Result(column = "activity", property = "activity"),
            @Result(column = "stuSex", property = "stuSex"),
            @Result(column = "stuTel", property = "stuTel"),
            @Result(column = "parentTelRelation", property = "parentTelRelation"),
            @Result(column = "buxiStateID", property = "buxiStateID"),
            @Result(column = "stuGradeID", property = "stuGradeID"),
            @Result(column = "stuXuexi", property = "stuXuexi"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "banzhurenTeacherID", property = "banzhurenTeacherID"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "unionid", property = "unionid"),
            @Result(column = "remainXuefei", property = "remainXuefei"),
            @Result(column = "remainChongzhi", property = "remainChongzhi"),
            @Result(column = "stubirth", property = "stubirth"),
            @Result(column = "jifenNum", property = "jifenNum"),
            @Result(column = "IDImage", property = "iDImage"),
            @Result(column = "IDnumber", property = "iDnumber"),
            @Result(column = "roomid", property = "roomid"),
            @Result(column = "oldSchoolTeacher", property = "oldSchoolTeacher"),
            @Result(column = "oldSchool", property = "oldSchool"),
            @Result(column = "ruxuechengji", property = "ruxuechengji"),
            @Result(column = "laoshiyaoqiu", property = "laoshiyaoqiu"),
            @Result(column = "jijixing", property = "jijixing"),
            @Result(column = "xingge", property = "xingge"),
            @Result(column = "yxSubjects", property = "yxSubjects"),
            @Result(column = "lastHuifangTime", property = "lastHuifangTime"),
            @Result(column = "nextHuifangTime", property = "nextHuifangTime"),
            @Result(column = "stuPhoto", property = "stuPhoto"),
            @Result(column = "yxFromID", property = "yxFromID"),
            @Result(column = "yxLevelID", property = "yxLevelID"),
            @Result(column = "yixiangkemu", property = "yixiangkemu"),
            @Result(column = "yxshichangTeacherID", property = "yxshichangTeacherID"),
            @Result(column = "yxfenpeistaffID", property = "yxfenpeistaffID"),
            @Result(column = "yxfenpeiDate", property = "yxfenpeiDate"),
            @Result(column = "daoruDate", property = "daoruDate"),
            @Result(column = "lastFollowDate", property = "lastFollowDate"),
            @Result(column = "luruType", property = "luruType"),
            @Result(column = "dengjiTeacherID", property = "dengjiTeacherID"),
            @Result(column = "dengjiTime", property = "dengjiTime"),
            @Result(column = "nextGenjinTime", property = "nextGenjinTime"),
            @Result(column = "tingkeTime", property = "tingkeTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxstutable"
            + "</script>")
    List<Pxstutable> getAllList();

    /**
     * 修改学生联系电话
     *
     * @param id
     * @param newTel
     * @return
     */
    @Select("<script>" + "update pxstutable SET parentTel= #{newTel} WHERE id = #{id}" + "</script>")
    Integer UpdateStuParentTel(long id, String newTel);

    /**
     * 重置密码
     *
     * @param newpasswd
     * @param stuId
     * @param qiyeID
     * @return
     */
    @Select("<script>" + "\tUPDATE pxstutable SET passwd = #{newpasswd} WHERE id = #{stuId} AND qiyeID = #{qiyeID}" + "</script>")
    Integer UpdateStuWechatPassword(String newpasswd, long stuId, long qiyeID);

    /**
     * 解除微信绑定
     *
     * @param tel
     * @param qiyeID
     * @return
     */
    @Select("<script>" + "DELETE FROM pxwxusertable WHERE tel = #{tel} AND qiyeID =#{qiyeID}" + "</script>")
    Integer DeleteWxuser(String tel, long qiyeID);

}