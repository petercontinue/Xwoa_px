package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
import com.xwcloud.cloud.model.entity.Pxshouzhistyletable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-24
 */
public interface IPxshouzhistyletableService extends IService<Pxshouzhistyletable> {
    public List<Pxliushuizhangtable> queryLiushuizhang();
}
