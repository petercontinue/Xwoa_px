package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.teachSubjectVo;
import com.xwcloud.cloud.model.entity.Pxteachsubjecttable;
import com.xwcloud.cloud.sys.Dao.IPxteachsubjecttableDao;
import com.xwcloud.cloud.sys.Service.IPxteachsubjecttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-08
 */
@Service
//@DS("user")
public class PxteachsubjecttableServiceImpl extends ServiceImpl<IPxteachsubjecttableDao, Pxteachsubjecttable> implements IPxteachsubjecttableService {

    @Autowired
    IPxteachsubjecttableDao iPxteachsubjecttableDao;

    @Override
    public Page<teachSubjectVo> GetTeacheSubjectPages(Page page,String staffID, QueryWrapper wrapper) {
        return iPxteachsubjecttableDao.GetTeacheSubjectPages(page, wrapper,staffID);
    }
}
