package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.StuYueInfoVo;
import com.xwcloud.cloud.model.Vo.exportqdInfoVo;
import com.xwcloud.cloud.model.Vo.qianDanInFoVo;
import com.xwcloud.cloud.model.Vo.updateqiandanVO;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;
import com.xwcloud.cloud.zsbm.Dao.IPxqiandaninfotableDao;
import com.xwcloud.cloud.zsbm.Service.IPxqiandaninfotableService;
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
public class PxqiandaninfotableServiceImpl extends ServiceImpl<IPxqiandaninfotableDao, Pxqiandaninfotable> implements IPxqiandaninfotableService {

    @Autowired
    IPxqiandaninfotableDao iPxqiandaninfotableDao;

    @Override
    public Page<qianDanInFoVo> GetQiandanInfoPages(Page page, QueryWrapper wrapper) {
        return iPxqiandaninfotableDao.GetQiandanInfoPages(page, wrapper);
    }

    @Override
    public Page<StuYueInfoVo> GetAllStuYuePages(Page page, QueryWrapper wrapper) {
        return iPxqiandaninfotableDao.GetAllStuYuePages(page, wrapper);
    }

    @Override
    public List<Pxqiandaninfotable> GetStuQianDanList(Long stuID, Long qiyeID) {
        return iPxqiandaninfotableDao.GetStuQianDanList(stuID, qiyeID);
    }

    @Override
    public List<Pxqiandaninfotable> GetXqOrXfList(Long stuID, Long qiyeID) {
        return iPxqiandaninfotableDao.GetXqOrXfList(stuID, qiyeID);
    }

    @Override
    public Integer deleteQiandanByStuID(Long stuID, Long qiyeID) {
        return iPxqiandaninfotableDao.deleteQiandanByStuID(stuID, qiyeID);
    }

    @Override
    public Pxqiandaninfotable GetXqOrXfinfo(Long stuID, Long qiyeID) {
        return iPxqiandaninfotableDao.GetXqOrXfinfo(stuID, qiyeID);
    }

    @Override
    public List<exportqdInfoVo> GetQiandanInfoList(QueryWrapper wrapper) {
        return iPxqiandaninfotableDao.GetQiandanInfoList(wrapper);
    }

    @Override
    public List<updateqiandanVO> GetQiandanInfoLits(long qiandanID) {
        return iPxqiandaninfotableDao.GetQiandanInfoLits(qiandanID);
    }

    @Override
    public qianDanInFoVo GetqiandanInfoByqiandanID(Long qiandanID) {
        return iPxqiandaninfotableDao.GetqiandanInfoByqiandanID(qiandanID);
    }

}
