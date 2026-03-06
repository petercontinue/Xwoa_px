package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxoldschoolteachertable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxoldschoolteachertableService extends IService<Pxoldschoolteachertable> {
    List<Pxoldschoolteachertable> getoldTeacher(String oldSchoolTeacherName, Long oldSchoolID, Long qiyeID);
}
