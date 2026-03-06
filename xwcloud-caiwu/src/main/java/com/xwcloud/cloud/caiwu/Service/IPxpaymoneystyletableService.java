package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
public interface IPxpaymoneystyletableService extends IService<Pxpaymoneystyletable> {
    List<listVo> getpaystyle(Long qiyeID);
}
