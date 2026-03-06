package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxyxtelleveltable;
import com.xwcloud.cloud.sys.Dao.IPxyxtelleveltableDao;
import com.xwcloud.cloud.sys.Service.IPxyxtelleveltableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-24
 */
@Service
//@DS("#header.DBname")
public class PxyxtelleveltableServiceImpl extends ServiceImpl<IPxyxtelleveltableDao, Pxyxtelleveltable> implements IPxyxtelleveltableService {

    @Autowired
    IPxyxtelleveltableDao iPxyxtelleveltableDao;

    @Override
    public List<Pxstutable> GetStuBytelLevelID(String telLevelID) {
        return iPxyxtelleveltableDao.GetStuBytelLevelID(telLevelID);
    }
}
