package com.xwcloud.cloud.zsbm.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Qiandanshenpi;
import com.xwcloud.cloud.model.Vo.qiandanshenpiVO;
import com.xwcloud.cloud.zsbm.Dao.IQiandanshenpiDao;
import com.xwcloud.cloud.zsbm.Service.IQiandanshenpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
@Service
public class QiandanshenpiServiceImpl extends ServiceImpl<IQiandanshenpiDao, Qiandanshenpi> implements IQiandanshenpiService {

    @Autowired
    IQiandanshenpiDao iQiandanshenpiDao;
    @Override
    public Page<qiandanshenpiVO> GetAllQiandanshenpiPages(Page page, QueryWrapper wrapper) {
        return iQiandanshenpiDao.GetAllQiandanshenpiPages(page,wrapper);
    }
}
