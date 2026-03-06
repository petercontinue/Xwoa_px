package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxkeshizhuansongtable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-22
 */
public interface IPxkeshizhuansongtableService extends IService<Pxkeshizhuansongtable> {
	List<Pxkeshizhuansongtable> getksZhuangSong(Long qiyeID,Long stuID);
}
