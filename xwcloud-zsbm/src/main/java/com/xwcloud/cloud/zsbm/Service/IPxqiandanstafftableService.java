package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.qiandanstaffVO;
import com.xwcloud.cloud.model.entity.Pxqiandanstafftable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
public interface IPxqiandanstafftableService extends IService<Pxqiandanstafftable> {
    List<Pxqiandanstafftable> GetQiandanStaffByQiandanID(Long qiandanID);

    int dleteQiandanStaffbyQiandanID(Long qiandanID);

    List<qiandanstaffVO> GetqiandanStaffList(long qiandanID);

    List<HashMap<String, Object>> getyejitrentoPayweikuan( QueryWrapper queryWrapper);
}
