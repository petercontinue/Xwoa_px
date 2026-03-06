package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.djqSumVo;
import com.xwcloud.cloud.model.entity.Pxdaijinquantable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */

public interface IPxdaijinquantableService extends IService<Pxdaijinquantable> {
    List<Pxdaijinquantable> getstudjq(Long stuID, Long qiyeID);

    List<djqSumVo> getstudjqSum(Long stuID, Long qiyeID);
}
