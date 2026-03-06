package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.Vo.subjectVO;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
public interface IPxsubjecttableService extends IService<Pxsubjecttable> {

    Pxsubjecttable GetSubjectById(Long Id);

    List<searchVO> GetAllSubjectList(Long qiyeID);

    List<searchVO> GetAllSubjectListnocampus(Long qiyeID);

    List<searchVO> GetAllSubjectByxqIDAndqiyeID(Long qiyeID,Long campusID);

    Page<subjectVO> getAllsubjectPages(Page page, QueryWrapper wrapper);

    List<Pxsubjecttable> GetsubjectList(Long campusID,String subjectName);
}
