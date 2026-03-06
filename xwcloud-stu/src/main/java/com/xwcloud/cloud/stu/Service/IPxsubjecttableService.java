package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
public interface IPxsubjecttableService extends IService<Pxsubjecttable> {
    List<listVo> GetcampusIDkemu(Long campusID, Long qiyeID);

    List<Pxsubjecttable> selectsub(QueryWrapper queryWrapper);
    List<listVo> getallkemuName(Long qiyeID);
}
