package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxtesttypetable;

import com.xwcloud.cloud.model.Vo.listVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-22
 */
public interface IPxtesttypetableService extends IService<Pxtesttypetable> {
    List<listVo> getTesttype(Long qiyeID);

    Pxtesttypetable getOnetest(Long qiyeID);

    List<Pxtesttypetable> selecttesttype(QueryWrapper queryWrapper);
}
