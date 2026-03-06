package com.xwcloud.cloud.sys.Service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.menubuttonslistVO;
import com.xwcloud.cloud.model.Vo.quanxianfanhui;
import com.xwcloud.cloud.model.entity.Pxmenutable;
import com.xwcloud.cloud.model.entity.Pxpowerbuttontable;
import com.xwcloud.cloud.model.entity.Pxpowermenubuttontable;
import com.xwcloud.cloud.model.entity.Pxpowertable;
import com.xwcloud.cloud.sys.Dao.IPxmenutableDao;
import com.xwcloud.cloud.sys.Service.IPxmenutableService;
import com.xwcloud.cloud.sys.Service.IPxpowerbuttontableService;
import com.xwcloud.cloud.sys.Service.IPxpowermenubuttontableService;
import com.xwcloud.cloud.sys.Service.IPxpowertableService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yinqi
 * @since 2020-10-20
 */
@Service
public class PxmenutableServiceImpl extends ServiceImpl<IPxmenutableDao, Pxmenutable> implements IPxmenutableService {
    @Autowired
    IPxmenutableDao iPxmenutableDao;

    @Autowired
    IPxpowertableService iPxpowertableService;

    @Autowired
    IPxpowermenubuttontableService iPxpowermenubuttontableService;

    @Autowired
    IPxpowerbuttontableService iPxpowerbuttontableService;

    @Autowired
    IPxmenutableService iPxmenutableService;

    @Override
    public List<Pxmenutable> getErjimenu(long id) {
        return iPxmenutableDao.getErjiMenu(id);
    }

    /**
     * 查询系统所有菜单
     * 返回菜单树
     */
    @Override
    public List<Pxmenutable> querymenuList(long staffpostID, long qiyeID) {
        List<Pxmenutable> data = iPxmenutableDao.getAllList();
        List<Pxmenutable> menutableList = iPxmenutableDao.getErjiMenu(0L);
        for (Pxmenutable category : menutableList) {
            category.setTreeList(getChilde(category.getId(), data, staffpostID, qiyeID));
        }
        return menutableList;
    }
    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootList 要查找的列表
     * @return
     */
    private List<Pxmenutable> getChilde(Long id, List<Pxmenutable> rootList, long staffpostID, long qiyeID) {
        List<Pxmenutable> childList = new ArrayList<>();
        for (Pxmenutable category : rootList) {
            if (category.getFid().equals(id)) {
                childList.add(category);
                if (category.getLevelID() == 3) {
                    category.setMenubtns(getpowerbutton(category.getId(), staffpostID, qiyeID));
                    Pxpowertable pxpowertable = iPxpowermenubuttontableService.GetStaffpostmenuPower(staffpostID, category.getId(), qiyeID);
                    category.setDatafanwei(pxpowertable == null ? "1" : pxpowertable.getDataFanwei());
                    category.setCheckmenu(pxpowertable == null ? false : StringUtils.isNotEmpty(pxpowertable.getDataFanwei()) ? true : false);
                    category.setDataFanweicheck(category.getDatafanwei().equals("0")||category.getDatafanwei().equals("1")||category.getDatafanwei().equals("-1")
                    ||category.getDatafanwei().equals("-2")?category.getDatafanwei():"-3");
                }
            }
        }
        for (Pxmenutable category : childList) {
            category.setTreeList(getChilde(category.getId(), rootList, staffpostID, qiyeID));
        }
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    private List<menubuttonslistVO> getpowerbutton(long menuid, long staffpostID, long qiyeID) {
        List<menubuttonslistVO> menubuttonList = new ArrayList<>();
        List<quanxianfanhui> pxpowermenubuttontableList = iPxpowermenubuttontableService.getmenusbuttonList(menuid);
        for (quanxianfanhui item : pxpowermenubuttontableList) {
            menubuttonslistVO menubuttonslistVO = new menubuttonslistVO();
            menubuttonslistVO.setId(item.getButtonID());
            menubuttonslistVO.setButtonName(item.getButtonName());
            Pxpowertable pxpowertable = iPxpowermenubuttontableService.GetStaffpostmenuPower(staffpostID, menuid, qiyeID);
            if (pxpowertable != null) {
                if (pxpowertable.getInsertbtn() && item.getButtonName().equals("添加")) {
                    menubuttonslistVO.setIscheck(true);
                } else if (pxpowertable.getDeletebtn() && item.getButtonName().equals("删除")) {
                    menubuttonslistVO.setIscheck(true);
                } else if (pxpowertable.getUpdatebtn() && item.getButtonName().equals("修改")) {
                    menubuttonslistVO.setIscheck(true);
                } else if (pxpowertable.getPringbtn() && item.getButtonName().equals("打印")) {
                    menubuttonslistVO.setIscheck(true);
                } else if (pxpowertable.getDaochubtn() && item.getButtonName().equals("导出")) {
                    menubuttonslistVO.setIscheck(true);
                } else if (pxpowertable.getDaorubtn() && item.getButtonName().equals("导入")) {
                    menubuttonslistVO.setIscheck(true);
                } else if (StringUtils.isNotEmpty(pxpowertable.getQitabtn())) {
                    List<String> strs = Arrays.asList(pxpowertable.getQitabtn().split(","));
                    if (strs.contains(item.getButtonID().toString())) {
                        menubuttonslistVO.setIscheck(true);
                    } else {
                        menubuttonslistVO.setIscheck(false);
                    }
                } else {
                    menubuttonslistVO.setIscheck(false);
                }
            } else {
                menubuttonslistVO.setIscheck(false);
            }
            menubuttonList.add(menubuttonslistVO);
        }
        return menubuttonList;
    }

    @Override
    public List<Pxpowerbuttontable> getPowerbuttonList(Long menuID, Long staffPostID, long qiyeID) {
        List<Pxpowertable> pxpowertable = iPxpowertableService.GetPowersBystaffpostIDandmenuID(menuID, staffPostID, qiyeID);
        List<Pxpowermenubuttontable> pxpowermenubuttontable = iPxpowermenubuttontableService.GetpowermenuButtonListBymenuID(menuID);
        List<Pxpowerbuttontable> fhlist = new ArrayList<>();
        if (pxpowertable.size() == 0) {
            return null;
        } else {
            Pxpowertable pxpowertableList = pxpowertable.get(0);
            if (pxpowertableList.getInsertbtn() == true && iPxpowermenubuttontableService.GetpowermenuButtonListBymenuIDAndbuttonID(menuID, 1L) != null) {
                Pxpowerbuttontable one = new Pxpowerbuttontable();
                Pxpowerbuttontable annius = iPxpowerbuttontableService.getById("1");
                one.setId(1L);
                one.setButtonName(annius.getButtonName());
                one.setOnclicks(annius.getOnclicks());
                one.setIcons(annius.getIcons());
                one.setBtnClass(annius.getBtnClass());
                fhlist.add(one);
            }
            if (pxpowertableList.getUpdatebtn() == true && iPxpowermenubuttontableService.GetpowermenuButtonListBymenuIDAndbuttonID(menuID, 2L) != null) {
                Pxpowerbuttontable one = new Pxpowerbuttontable();
                Pxpowerbuttontable annius = iPxpowerbuttontableService.getById("2");
                one.setId(2L);
                one.setButtonName(annius.getButtonName());
                one.setOnclicks(annius.getOnclicks());
                one.setIcons(annius.getIcons());
                one.setBtnClass(annius.getBtnClass());
                fhlist.add(one);
            }
            if (pxpowertableList.getDeletebtn() == true && iPxpowermenubuttontableService.GetpowermenuButtonListBymenuIDAndbuttonID(menuID, 3L) != null) {
                Pxpowerbuttontable one = new Pxpowerbuttontable();
                Pxpowerbuttontable annius = iPxpowerbuttontableService.getById("3");
                one.setId(3L);
                one.setButtonName(annius.getButtonName());
                one.setOnclicks(annius.getOnclicks());
                one.setIcons(annius.getIcons());
                one.setBtnClass(annius.getBtnClass());
                fhlist.add(one);
            }
            if (pxpowertableList.getDaochubtn() == true && iPxpowermenubuttontableService.GetpowermenuButtonListBymenuIDAndbuttonID(menuID, 4L) != null)  //导出
            {
                Pxpowerbuttontable one = new Pxpowerbuttontable();
                Pxpowerbuttontable annius = iPxpowerbuttontableService.getById("4");
                one.setId(4L);
                one.setButtonName(annius.getButtonName());
                one.setOnclicks(annius.getOnclicks());
                one.setIcons(annius.getIcons());
                one.setBtnClass(annius.getBtnClass());
                fhlist.add(one);
            }
            if (pxpowertableList.getDaorubtn() == true && iPxpowermenubuttontableService.GetpowermenuButtonListBymenuIDAndbuttonID(menuID, 53L) != null)  //导入
            {
                Pxpowerbuttontable one = new Pxpowerbuttontable();
                Pxpowerbuttontable annius = iPxpowerbuttontableService.getById("53");
                one.setId(53L);
                one.setButtonName(annius.getButtonName());
                one.setOnclicks(annius.getOnclicks());
                one.setIcons(annius.getIcons());
                one.setBtnClass(annius.getBtnClass());
                fhlist.add(one);
            }
            if (StringUtils.isNotBlank(pxpowertableList.getQitabtn())) {
                String[] strs = pxpowertableList.getQitabtn().trim().split(",");
                for (String item : strs) {
                    Pxpowerbuttontable annius = iPxpowerbuttontableService.getById(item);
                    List<Pxpowermenubuttontable> annius2 = iPxpowermenubuttontableService.GetpowermenuButtonListBymenuIDAndbuttonID(menuID, Long.parseLong(item));
                    if (annius != null && annius2 != null) {
                        Pxpowerbuttontable one = new Pxpowerbuttontable();
                        one.setId(annius.getId());
                        one.setButtonName(annius.getButtonName());
                        one.setOnclicks(annius.getOnclicks());
                        one.setIcons(annius.getIcons());
                        one.setBtnClass(annius.getBtnClass());
                        fhlist.add(one);
                    }
                }
            }
            return fhlist;
        }
    }
}

