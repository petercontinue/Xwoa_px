package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Dao.IWhdJizanHelpjizanDao;
import com.xwcloud.cloud.wsc.Service.IWhdJizanHelpjizanService;

import com.xwcloud.cloud.model.Vo.jizancanyuVO;
import com.xwcloud.cloud.model.entity.WhdJizanHelpjizan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-24
 */
@Service
public class WhdJizanHelpjizanServiceImpl extends ServiceImpl<IWhdJizanHelpjizanDao, WhdJizanHelpjizan> implements IWhdJizanHelpjizanService {

    @Autowired
    IWhdJizanHelpjizanDao iWhdJizanHelpjizanDao;

    @Override
    public List<jizancanyuVO> GetHelpdianzanRecordsList(long huodongID) {
        return iWhdJizanHelpjizanDao.GetHelpdianzanRecordsList(huodongID);
    }
}
