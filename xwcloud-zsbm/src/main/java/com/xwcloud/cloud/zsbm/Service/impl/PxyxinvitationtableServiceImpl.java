package com.xwcloud.cloud.zsbm.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.yaoyueVo;
import com.xwcloud.cloud.model.Vo.yaoyuedaofangVo;
import com.xwcloud.cloud.model.entity.Pxyxinvitationtable;
import com.xwcloud.cloud.zsbm.Dao.IPxyxinvitationtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxyxinvitationtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxyxinvitationtableServiceImpl extends ServiceImpl<IPxyxinvitationtableDao, Pxyxinvitationtable> implements IPxyxinvitationtableService {
    @Autowired
    IPxyxinvitationtableDao iPxyxinvitationtableDao;

    @Override
    public List<Pxyxinvitationtable> GetInvitationListByStuID(long stuID) {
        return iPxyxinvitationtableDao.GetInvitationListByStuID(stuID);
    }

    @Override
    public Page<yaoyueVo> GetinvitationByStuIDPages(Page<yaoyueVo> page, QueryWrapper<yaoyueVo> wrapper) {
        return iPxyxinvitationtableDao.GetinvitationByStuIDPages(page, wrapper);
    }

    @Override
    public Page<yaoyuedaofangVo> GetyaoyueDaofangPages(Page page, QueryWrapper wrapper) {
        return iPxyxinvitationtableDao.GetyaoyueDaofangPages(page, wrapper);
    }

    @Override
    public List<yaoyuedaofangVo> getyaoyuedaofangList(QueryWrapper wrapper) {
        return iPxyxinvitationtableDao.getyaoyuedaofangList(wrapper);
    }
}
