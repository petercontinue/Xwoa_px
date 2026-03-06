package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import com.xwcloud.cloud.stu.Dao.IPxcampustableDao;
import com.xwcloud.cloud.stu.Service.IPxcampustableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxcampustableServiceImpl extends ServiceImpl<IPxcampustableDao, Pxcampustable> implements IPxcampustableService {
    @Autowired
    IPxcampustableDao iPxcampustableDao;

    @Override
    public List<listVo> getStuKxqCanCampus(Long campusID, Long qiyeID) {
        return iPxcampustableDao.getStuKxqCanCampus(campusID, qiyeID);
    }

    @Override
    public List<Pxcampustable> getOneCampus(Long qiyeID, String campusName) {
        return iPxcampustableDao.getOneCampus(qiyeID, campusName);
    }

}
