package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxqiandaninfo2tableDao;
import com.xwcloud.cloud.caiwu.Service.IPxqiandaninfo2tableService;
import com.xwcloud.cloud.model.entity.Pxqiandaninfo2table;
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
public class Pxqiandaninfo2tableServiceImpl extends ServiceImpl<IPxqiandaninfo2tableDao, Pxqiandaninfo2table> implements IPxqiandaninfo2tableService {
    @Autowired
    IPxqiandaninfo2tableDao iPxqiandaninfo2tableDao;

    @Override
    public List<Pxqiandaninfo2table> tuiallzf(Wrapper wrapper) {
        return iPxqiandaninfo2tableDao.tuiallzf(wrapper);
    }
}
