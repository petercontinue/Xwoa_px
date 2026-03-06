package com.xwcloud.cloud.homeschool.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.addtearateVo;
import com.xwcloud.cloud.model.entity.Teaevaluationvalue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-02
 */
public interface ITeaevaluationvalueService extends IService<Teaevaluationvalue> {
    List<addtearateVo> gethavepjrate(@Param("ew") QueryWrapper queryWrapper);
}
