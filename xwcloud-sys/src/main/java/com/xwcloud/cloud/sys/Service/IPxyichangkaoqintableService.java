package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.staffyichangkaoqinVO;
import com.xwcloud.cloud.model.entity.Pxyichangkaoqintable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-18
 */
public interface IPxyichangkaoqintableService extends IService<Pxyichangkaoqintable> {
    List<HashMap<String,String>> getyichangstaffbandCampusID(QueryWrapper queryWrapper);
    Page<staffyichangkaoqinVO> GetyichangkaoqinPages(Page page, QueryWrapper wrapper);

    List<staffyichangkaoqinVO> getyichangkaoqingList(QueryWrapper wrapper);
}
