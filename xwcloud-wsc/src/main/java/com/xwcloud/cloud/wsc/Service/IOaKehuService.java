package com.xwcloud.cloud.wsc.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaKehu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-06
 */
public interface IOaKehuService extends IService<OaKehu> {
    List<OaKehu> GetPhoneIncloudJigouStu(String parentTel);

    List<OaKehu> GetPhoneIncloudjigouStaff(String staffTel);
}
