package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.SumbxRemainVo;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-16
 */
public interface IPxbuxikechengtableService extends IService<Pxbuxikechengtable> {
    List<SumbxRemainVo> getSumzongRks(Long stuID);

    List<SumbxRemainVo> getSumRks(Long stuID, Long kechengID, Long qiyeID);
}
