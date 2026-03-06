package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.Vo.listVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-21
 */
public interface IOaKehuService extends IService<OaKehu> {
    List<listVo> GetAlljigouByPhoneNumber(String PhoneNumber);
    List<listVo> GetAlljigouByStuPhoneNumber(String PhoneNumber);
}
