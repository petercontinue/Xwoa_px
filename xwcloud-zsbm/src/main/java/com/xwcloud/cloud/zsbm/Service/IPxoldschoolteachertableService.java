package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxoldschoolteachertable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxoldschoolteachertableService extends IService<Pxoldschoolteachertable> {
    Pxoldschoolteachertable GetOldschoolteacherBytnameAndsID(String oldSchoolTeacherName,Long oldSchoolID);
}
