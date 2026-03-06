package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.wschuodongVO;
import com.xwcloud.cloud.model.entity.WscHuodong;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWscHuodongService extends IService<WscHuodong> {
    List<WscHuodong> GetAllWscHuodongList();
    List<wschuodongVO> GetAllhuodongList(long qiyeID);
}
