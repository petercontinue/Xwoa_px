package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.tuisongtypeVO;
import com.xwcloud.cloud.model.entity.Pxtuisongtypetable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-21
 */
public interface IPxtuisongtypetableService extends IService<Pxtuisongtypetable> {
    List<tuisongtypeVO> getAllTuisongTypeList();
}
