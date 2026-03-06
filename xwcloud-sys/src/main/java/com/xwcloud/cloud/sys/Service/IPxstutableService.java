package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstutable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinqi
 * @since 2020-10-18
 */
public interface IPxstutableService extends IService<Pxstutable> {
    Pxstutable GetstuInfoByParentTel(String parentTel);
    String loginByWeixin(String code, String encryptedData, String iv);
    List<HashMap<String,String>> getlongtimenokeshiStu(Long qiyeID);
    List<HashMap<String,String>> getwxNoUserList(QueryWrapper queryWrapper);
}
