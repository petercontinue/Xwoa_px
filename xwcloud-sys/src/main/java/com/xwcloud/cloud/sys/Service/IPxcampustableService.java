package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.cxCampusVO;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-24
 */
public interface IPxcampustableService extends IService<Pxcampustable> {
    List<Pxcampustable> getAllList();

    List<cxCampusVO> GetSearchCampusList(QueryWrapper queryWrapper);
    List<HashMap<String,Object>> getjigou(@Param("ew") QueryWrapper queryWrapper);
}
