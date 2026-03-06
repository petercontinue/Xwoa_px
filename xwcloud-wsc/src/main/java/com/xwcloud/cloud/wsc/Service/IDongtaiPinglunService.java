package com.xwcloud.cloud.wsc.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.wsc.Dao.dongtaipinglunVO;
import com.xwcloud.cloud.model.entity.DongtaiPinglun;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-19
 */
public interface IDongtaiPinglunService extends IService<DongtaiPinglun> {
    List<dongtaipinglunVO> GetDongtaiPinglunInfos(long dongtaiID);
}
