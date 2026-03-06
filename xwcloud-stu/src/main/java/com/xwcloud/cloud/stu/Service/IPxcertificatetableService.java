package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxcertificatetable;
import com.xwcloud.cloud.model.Vo.ExportZSVo;
import com.xwcloud.cloud.model.Vo.zhengshuSTVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxcertificatetableService extends IService<Pxcertificatetable> {
    Page<zhengshuSTVo> getzhengshuPage(Page page, QueryWrapper queryWrapper1, @Param("ew") QueryWrapper queryWrapper);

    List<ExportZSVo> Exportzhengshu(Long qiyeID);

    List<Pxcertificatetable> getcfZS(String zsName, Long qiyeID);
}
