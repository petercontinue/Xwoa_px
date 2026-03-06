package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.assetsVO;
import com.xwcloud.cloud.model.Vo.asstestyleVO;
import com.xwcloud.cloud.model.Vo.dengjiassetsVO;
import com.xwcloud.cloud.model.entity.Pxassetstable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
public interface IPxassetstableService extends IService<Pxassetstable> {
    Page<assetsVO> getassetsPages(Page page, QueryWrapper wrapper);

    List<asstestyleVO> getAllasstestyleList(QueryWrapper wrapper);

    List<assetsVO>GetAllAssetsList(QueryWrapper queryWrapper);

    List<dengjiassetsVO>GetListAssetsDengjiList(QueryWrapper queryWrapper);

    List<dengjiassetsVO> GetListAssetsBaofeiList(QueryWrapper queryWrapper);
}
