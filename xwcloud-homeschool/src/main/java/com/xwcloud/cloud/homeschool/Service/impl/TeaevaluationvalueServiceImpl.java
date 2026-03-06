package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.ITeaevaluationvalueDao;
import com.xwcloud.cloud.homeschool.Service.ITeaevaluationvalueService;
import com.xwcloud.cloud.model.Vo.addtearateVo;
import com.xwcloud.cloud.model.entity.Teaevaluationvalue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-02
 */
@Service
public class TeaevaluationvalueServiceImpl extends ServiceImpl<ITeaevaluationvalueDao, Teaevaluationvalue> implements ITeaevaluationvalueService {

    @Autowired
    ITeaevaluationvalueDao iTeaevaluationvalueDao;

    @Override
    public List<addtearateVo> gethavepjrate(QueryWrapper queryWrapper) {
        return iTeaevaluationvalueDao.gethavepjrate(queryWrapper);
    }
}
