package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2021-07-29
 */
public interface IPxbuxikechengtableService extends IService<Pxbuxikechengtable> {
    List<Pxbuxikechengtable> getNOClasskc(QueryWrapper queryWrapper);
    List<SumbxRemainVo> getSumzongRks(Long stuID,Long qiyeID);
    List<SumbxRemainVo> getSumRks(Long stuID, Long kechengID, Long qiyeID);

}
