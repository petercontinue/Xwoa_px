package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.Pxtingke;
import com.xwcloud.cloud.stu.Dao.IPxtingkeDao;
import com.xwcloud.cloud.stu.Service.IPxtingkeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-27
 */
@Service
public class PxtingkeServiceImpl extends ServiceImpl<IPxtingkeDao, Pxtingke> implements IPxtingkeService {
    @Autowired
    IPxtingkeDao iPxtingkeDao;

    @Override
    public List<Pxtingke> getstuTk(Long stuID, Long qiyeID) {
        return iPxtingkeDao.getstuTk(stuID, qiyeID);
    }
}
