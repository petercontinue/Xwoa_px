package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.youhuizhengceVO;
import com.xwcloud.cloud.model.Vo.youhuizhengcexuanzeVO;
import com.xwcloud.cloud.model.entity.Pxyouhuizhengcetable;
import com.xwcloud.cloud.zsbm.Dao.IPxyouhuizhengcetableDao;
import com.xwcloud.cloud.zsbm.Service.IPxyouhuizhengcetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
@Service
public class PxyouhuizhengcetableServiceImpl extends ServiceImpl<IPxyouhuizhengcetableDao, Pxyouhuizhengcetable> implements IPxyouhuizhengcetableService {

    @Autowired
    IPxyouhuizhengcetableDao iPxyouhuizhengcetableDao;

    @Override
    public Pxyouhuizhengcetable getYouhuizhengceById(Long Id) {
        return iPxyouhuizhengcetableDao.getYouhuizhengceById(Id);
    }

    @Override
    public Page<youhuizhengceVO> GetYouhuizhengcePages(Page page, QueryWrapper wrapper) {
        return iPxyouhuizhengcetableDao.GetYouhuizhengcePages(page,wrapper);
    }

    @Override
    public List<youhuizhengcexuanzeVO> youhuizhengceListBystuGrade(String stuGradeID, QueryWrapper queryWrapper) {
        return iPxyouhuizhengcetableDao.youhuizhengceListBystuGrade(stuGradeID,queryWrapper);
    }

}
