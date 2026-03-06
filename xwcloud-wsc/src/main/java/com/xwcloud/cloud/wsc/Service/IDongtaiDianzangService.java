package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.wsc.Dao.dongtaidianzanVO;
import com.xwcloud.cloud.model.entity.DongtaiDianzang;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-19
 */
public interface IDongtaiDianzangService extends IService<DongtaiDianzang> {
    List<dongtaidianzanVO> GetAllDongtaiDianzangInfo(long dongtaiID);
}
