package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxtingke;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-27
 */
public interface IPxtingkeService extends IService<Pxtingke> {
    List<Pxtingke> getstuTk(Long stuID, Long qiyeID);
}
