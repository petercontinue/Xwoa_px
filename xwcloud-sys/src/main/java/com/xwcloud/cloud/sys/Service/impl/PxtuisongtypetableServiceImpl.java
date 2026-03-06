package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.tuisongtypeVO;
import com.xwcloud.cloud.model.entity.Pxtuisongtypetable;
import com.xwcloud.cloud.sys.Dao.IPxtuisongtypetableDao;
import com.xwcloud.cloud.sys.Service.IPxtuisongtypetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-21
 */
@Service
public class PxtuisongtypetableServiceImpl extends ServiceImpl<IPxtuisongtypetableDao, Pxtuisongtypetable> implements IPxtuisongtypetableService {

    @Autowired
    IPxtuisongtypetableDao iPxtuisongtypetableDao;

    @Override
    public List<tuisongtypeVO> getAllTuisongTypeList() {
        return iPxtuisongtypetableDao.getAllTuisongTypeList();
    }
}
