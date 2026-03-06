package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.getclassVo;
import com.xwcloud.cloud.model.Vo.haveTimeTeaVo;
import com.xwcloud.cloud.model.entity.Pxstafftable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-11
 */
public interface IPxstafftableService extends IService<Pxstafftable> {
    Page<haveTimeTeaVo> getstaff(Page page, QueryWrapper queryWrapper);
    List<getclassVo> getstaffBycam(Long campusID, Long qiyeID);
    List<getclassVo>getallstaff(Long qiyeID);
}
