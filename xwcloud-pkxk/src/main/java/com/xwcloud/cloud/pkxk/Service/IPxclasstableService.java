package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.getclassVo;
import com.xwcloud.cloud.model.entity.Pxclasstable;


import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-01
 */
public interface IPxclasstableService extends IService<Pxclasstable> {
    List<getclassVo> getclassbycam(Long campusID, Long qiyeID);

    List<getclassVo> NOpaikegetclass(QueryWrapper queryWrapper);
}
