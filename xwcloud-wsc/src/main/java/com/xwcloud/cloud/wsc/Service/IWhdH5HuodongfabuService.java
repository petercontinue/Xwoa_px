package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.huodongdetailVO;
import com.xwcloud.cloud.model.Vo.mymobanVO;
import com.xwcloud.cloud.model.entity.WhdH5Huodongfabu;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-19
 */
public interface IWhdH5HuodongfabuService extends IService<WhdH5Huodongfabu> {
    Page<mymobanVO> GetMyzhaoxuexiuMobanPages(Page page, long qiyeID);

    huodongdetailVO getHuodongDetailInfo(long qiyeID, long huodongID);

    List<HashMap<String, Object>> GetMubanHuodongDetail(QueryWrapper wrapper);
}
