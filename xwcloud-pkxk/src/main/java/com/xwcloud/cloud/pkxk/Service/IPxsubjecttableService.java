package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
public interface IPxsubjecttableService extends IService<Pxsubjecttable> {
    List<searchVO> GetAllKemuList(long qiyeID);
}
