package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.ReclassVo;
import com.xwcloud.cloud.model.Vo.allxuankeVo;
import com.xwcloud.cloud.model.Vo.tuixiankeVo;
import com.xwcloud.cloud.model.Vo.zbInPaikeVo;
import com.xwcloud.cloud.model.entity.Pxxuanketable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxxuanketableService extends IService<Pxxuanketable> {
    List<Pxxuanketable> allxuankebypkID(Long paikeID, Long qiyeID);

    List<Pxxuanketable> xuankebypkstu(Long paikeID, Long stuID, Long qiyeID);

    List<Pxxuanketable> xxkbypkbx(Long paikeID, Long bxID, Long qiyeID);

    List<Pxxuanketable> getxkByPkStuBx(Long paikeID, Long stuID, Long bxID, Long qiyeID);

    List<ReclassVo> getJoinPaikeL(Long classID, Long qiyeID);

    List<ReclassVo> getJoinPaikeStuL(Long classID, Long stuID, Long qiyeID);

    List<Pxxuanketable> getBybxID(Long bxID, Long qiyeID);

    List<Pxxuanketable> getallxuankeTable(Long paikeID, Long qiyeID);

    List<allxuankeVo> getallxuanke(Long buxiID, Long qiyeID);

    List<Pxxuanketable> getNokehaoStu(Long buxiID, Long qiyeID);

    List<zbInPaikeVo> getInPaikeByClassandBuxi(String buxiID, String classID, Long qiyeID);

    List<tuixiankeVo> tuixuankePaike(@Param("ew") QueryWrapper queryWrapper);
}
