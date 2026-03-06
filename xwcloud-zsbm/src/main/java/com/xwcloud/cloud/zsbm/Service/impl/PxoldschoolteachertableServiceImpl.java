package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxoldschoolteachertable;
import com.xwcloud.cloud.zsbm.Dao.IPxoldschoolteachertableDao;
import com.xwcloud.cloud.zsbm.Service.IPxoldschoolteachertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Service
public class PxoldschoolteachertableServiceImpl extends ServiceImpl<IPxoldschoolteachertableDao, Pxoldschoolteachertable> implements IPxoldschoolteachertableService {
    @Autowired
    IPxoldschoolteachertableDao iPxoldschoolteachertableDao;

    @Override
    public Pxoldschoolteachertable GetOldschoolteacherBytnameAndsID(String oldSchoolTeacherName, Long oldSchoolID) {
        return iPxoldschoolteachertableDao.GetOldschoolteacherBytnameAndsID(oldSchoolTeacherName,oldSchoolID);
    }
}
