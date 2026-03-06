package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.birthdayzhufuVO;
import com.xwcloud.cloud.model.Vo.birthinfoVO;
import com.xwcloud.cloud.model.Vo.dianzangVO;
import com.xwcloud.cloud.model.entity.Birthdayzhufu;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-18
 */
public interface IBirthdayzhufuService extends IService<Birthdayzhufu> {
    List<dianzangVO> GetbirthDianzanInfo(long beidianzanUserID);

    List<birthdayzhufuVO>GetAllbirthdayZhufu(long zhufuUserID);

    List<birthinfoVO> GetshengriStuInfo(long stuID);
}
