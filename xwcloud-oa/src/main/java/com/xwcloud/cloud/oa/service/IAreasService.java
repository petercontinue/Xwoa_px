package com.xwcloud.cloud.oa.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.Areas;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-23
 */
public interface IAreasService extends IService<Areas> {

    //根据省份获取下级城市
    public List<Areas> getAllAreasSubInfo(String id);

}
