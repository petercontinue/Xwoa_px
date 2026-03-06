package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstafftable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-11
 */
public interface IPxstafftableService extends IService<Pxstafftable> {
    List<HashMap<String, Object>> GetStaffInfo(QueryWrapper wrapper);
    List<HashMap<String, Object>> GetgerenStaffInfo(QueryWrapper wrapper);
    List<HashMap<String, Object>> GetgerenDongtaiInfo(QueryWrapper wrapper);
    List<HashMap<String, Object>> GetgerenStaffInfotoIndexpage(QueryWrapper wrapper);
    List<HashMap<String,Object>> getappteaInfo(QueryWrapper wrapper);
}
