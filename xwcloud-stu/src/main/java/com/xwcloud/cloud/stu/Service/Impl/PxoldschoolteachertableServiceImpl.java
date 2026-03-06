package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxoldschoolteachertable;
import com.xwcloud.cloud.stu.Dao.IPxoldschoolteachertableDao;
import com.xwcloud.cloud.stu.Service.IPxoldschoolteachertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Service
public class PxoldschoolteachertableServiceImpl extends ServiceImpl<IPxoldschoolteachertableDao, Pxoldschoolteachertable> implements IPxoldschoolteachertableService {
	@Autowired
    IPxoldschoolteachertableDao iPxoldschoolteachertableDao;

    @Override
    public List<Pxoldschoolteachertable> getoldTeacher(String oldSchoolTeacherName, Long oldSchoolID,Long qiyeID) {
        return iPxoldschoolteachertableDao.getoldTeacher(oldSchoolTeacherName, oldSchoolID,qiyeID);
    }
}
