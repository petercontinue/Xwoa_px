package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxclasstimestyletable;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
import com.xwcloud.cloud.sys.Dao.IPxclasstimestyletableDao;
import com.xwcloud.cloud.sys.Service.IPxclasstimestyletableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-21
 */
@Service
//@DS("#header.DBname")
public class PxclasstimestyletableServiceImpl extends ServiceImpl<IPxclasstimestyletableDao, Pxclasstimestyletable> implements IPxclasstimestyletableService {

    @Autowired
    IPxclasstimestyletableDao iPxclasstimestyletableDao;

    @Override
    public List<Pxkechengtable> GetkechengByKechengshichang(String classTimeStyleID) {
        return iPxclasstimestyletableDao.GetkechengByKechengshichang(classTimeStyleID);
    }

}
