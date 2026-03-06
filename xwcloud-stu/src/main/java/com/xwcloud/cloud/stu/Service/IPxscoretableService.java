package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxscoretable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxscoretableService extends IService<Pxscoretable> {
    List<Pxscoretable> getTestT(String testTitle,Long qiyeID);

}
