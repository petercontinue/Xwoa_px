package com.xwcloud.cloud.pkxk.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.common.Message.TemplateParam;
import com.xwcloud.cloud.common.Message.WXTmplMsgUtils;
import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.pkxk.Service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component

public class sendMessage {
    @Autowired
    IPxtuisongtableService iPxtuisongtableService;

    @Autowired
    IPxstutableService iPxstutableService;

    @Autowired
    IWscUserBindService iWscUserBindService;

    @Autowired
    IPxkechengtableService iPxkechengtableService;

    @Autowired
    IPxsysparamvaluetableService iPxsysparamvaluetableService;

    @Autowired
    IPxsysparamdefaulttableService iPxsysparamdefaulttableService;

    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @Autowired
    IWscUserService iWscUserService;

    @Autowired
    IGzhAlluserService iGzhAlluserService;

    @Autowired
    IPxstafftableService iPxstafftableService;

    @Autowired
    IPxclassroomtableService iPxclassroomtableService;

    public boolean sendMessage(Long tuisongtableID) {
        Pxtuisongtable pxtuisongtable = iPxtuisongtableService.getById(tuisongtableID);
        String templateId = "";
        if (pxtuisongtable.getRole() == 1) {//推送学生消息
            Pxstutable pxstutable = iPxstutableService.getById(pxtuisongtable.getStuID());
            Pxcampustable pxcampustable = iPxcampustableService.getById(pxstutable.getCampusID());
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("stuId", pxstutable.getId());
            List<WscUserBind> wscUserBinds = iWscUserBindService.list(queryWrapper);

            if (pxtuisongtable.getTuisongTypeName()==5) {//推送消课通知
                templateId = "66c5liowLy0kxCkLXjpVsmp8JpFVEHs_SAZcakX5AgQ";
                Pxkechengtable pxkechengtable = iPxkechengtableService.getById(pxtuisongtable.getKechengID());
                BigDecimal stuKechengRemainKeshi = new BigDecimal(0);
                String classWarning = iPxsysparamvaluetableService.getsysvalue(pxtuisongtable.getQiyeID(), 13L) == null ? iPxsysparamdefaulttableService.getById(13).getDefaultValue() : iPxsysparamvaluetableService.getsysvalue(pxtuisongtable.getQiyeID(), 13L).getModifyValue();
                if (classWarning == "总课时预警") {
                    stuKechengRemainKeshi = iPxbuxikechengtableService.getSumzongRks(pxtuisongtable.getStuID()).get(0).getSumR();
                } else {
                    stuKechengRemainKeshi = iPxbuxikechengtableService.getSumRks(pxtuisongtable.getStuID(), pxtuisongtable.getKechengID(), pxtuisongtable.getQiyeID()).get(0).getSumR();
                }
                //点击消息跳转小程序链接
                String url = "";
                //模板消息内容信息，与模板消息使用一致
                List<TemplateParam> paras = new ArrayList<>();
                paras.add(new TemplateParam("first", pxstutable.getStuName() + "同学，你有一个消课通知", "#FF3333"));
                paras.add(new TemplateParam("keyword1", pxkechengtable.getKechengName(), "#0044BB"));
                paras.add(new TemplateParam("keyword2", stuKechengRemainKeshi + "", "#0044BB"));
                paras.add(new TemplateParam("keyword3", DateUtil.formatDate2(pxtuisongtable.getHaveclassDate()) + " " + pxtuisongtable.getStartLessonDateTime().toString().substring(0, 5) + "-" + pxtuisongtable.getEndLessonDateTime().toString().substring(0, 5), "#0044BB"));
                paras.add(new TemplateParam("keyword4", pxtuisongtable.getNote(), "#0044BB"));
                paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                        }
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==4) {//积分变动提醒
                templateId = "wnStU0KbsNW6adcYSCdLEyY6Vs3bE1x71BRwtKO3PK4";
                List<TemplateParam> parasjf = new ArrayList<>();
                parasjf.add(new TemplateParam("first", pxstutable.getStuName() + "同学，你有一个积分变动通知", "#FF3333"));
                parasjf.add(new TemplateParam("keyword1", "积分变动通知", "#0044BB"));
                parasjf.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                parasjf.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            boolean tsback = WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), "", parasjf);
                            pxtuisongtable.setWxstate(tsback ? 1 : 0);
                        }
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==2) {//到校签到
                templateId = "2SvkQxH4NqjmxY-QNaQaToBz7wjB9u5ai8VcbPuvS3M";
                //点击消息跳转小程序链接
                String url = "";
                //模板消息内容信息，与模板消息使用一致
                List<TemplateParam> paras = new ArrayList<>();
                paras.add(new TemplateParam("first", pxstutable.getStuName() + "同学,你已成功签到", "#FF3333"));
                paras.add(new TemplateParam("keyword1", pxtuisongtable.getNote(), "#0044BB"));
                paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                        }
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==3) {//离校签退
                templateId = "HAOa5mfR5bWbekQVuvd79EB5Y2sYkGH1p0aBLzVENO8";
                String url = "";
                List<TemplateParam> paras = new ArrayList<>();
                paras.add(new TemplateParam("first", pxstutable.getStuName() + "同学,你已成功签退", "#FF3333"));
                paras.add(new TemplateParam("keyword1", pxstutable.getStuName(), "#0044BB"));
                paras.add(new TemplateParam("keyword2", StringUtils.isNotEmpty(pxstutable.getZidingyiStuID()) ? pxstutable.getZidingyiStuID() : pxstutable.getId() + "", "#0044BB"));
                paras.add(new TemplateParam("keyword3", DateUtil.formatDate1(pxtuisongtable.getAddTime()), "#0044BB"));
                paras.add(new TemplateParam("keyword4", "学员离校签退", "#0044BB"));
                paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                        }
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==6) {//上课提醒
                templateId = "buBlQGiUN6QNC4qxAC_TLfPy-vga4UjGK5xP3b-k4gk";
                String url = "";
                Pxkechengtable pxkechengtable = iPxkechengtableService.getById(pxtuisongtable.getKechengID());
                List<TemplateParam> paras = new ArrayList<>();
                paras.add(new TemplateParam("first", pxstutable.getStuName() + "同学,你有新的上课提醒信息", "#FF3333"));
                paras.add(new TemplateParam("keyword1", pxkechengtable.getKechengName(), "#0044BB"));
                paras.add(new TemplateParam("keyword2", DateUtil.formatDate2(pxtuisongtable.getHaveclassDate()) + " " + pxtuisongtable.getStartLessonDateTime().toString().substring(0, 5) + "-" + pxtuisongtable.getEndLessonDateTime().toString().substring(0, 5), "#0044BB"));
                paras.add(new TemplateParam("keyword3", pxtuisongtable.getNote(), "#0044BB"));
                paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                        }
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==7) {//学员约课审批结果通知
                templateId = "EMFI4qlAg6_BpXZ38ckrf3VfCdbhlw0kJ9u_6eciV7c";
                String url = "";
                List<TemplateParam> paras = new ArrayList<>();
                paras.add(new TemplateParam("first", pxstutable.getStuName() + "同学,你有新的约课审批信息！", "#FF3333"));
                paras.add(new TemplateParam("keyword1", DateUtil.formatDate1(pxtuisongtable.getAddTime()), "#0044BB"));
                paras.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                        }
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==8) {//学员请假审批结果
                templateId = "EMFI4qlAg6_BpXZ38ckrf3VfCdbhlw0kJ9u_6eciV7c";
                String url = "";
                List<TemplateParam> paras = new ArrayList<>();
                paras.add(new TemplateParam("first", pxstutable.getStuName() + "同学,你有新的请假审批信息！", "#FF3333"));
                paras.add(new TemplateParam("keyword1", DateUtil.formatDate1(pxtuisongtable.getAddTime()), "#0044BB"));
                paras.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                        }
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==9) {//老师课后评价学生
                templateId = "XZ6zVbUVARIHM9jw5aPVtGiAL2a9ekWDbp9etczqN20";
                String url = "";
                Pxstafftable pxstafftable = iPxstafftableService.getById(pxtuisongtable.getAddStaffID());
                List<TemplateParam> paras = new ArrayList<>();
                paras.add(new TemplateParam("first", pxstutable.getStuName() + "同学,你有新的课后评价！", "#FF3333"));
                paras.add(new TemplateParam("keyword1", pxstafftable.getStaffName(), "#0044BB"));
                paras.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                        }
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==10) {//老师对学员作业的点评
                templateId = "XZ6zVbUVARIHM9jw5aPVtGiAL2a9ekWDbp9etczqN20";
                Pxstafftable pxstafftable = iPxstafftableService.getById(pxtuisongtable.getAddStaffID());
                List<TemplateParam> paras = new ArrayList<>();
                paras.add(new TemplateParam("first", pxstutable.getStuName() + "同学,你有新的作业点评信息！", "#FF3333"));
                paras.add(new TemplateParam("keyword1", pxstafftable.getStaffName(), "#0044BB"));
                paras.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), "", paras);
                        }
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==11) {//学员相册有新照片提醒
                templateId = "ztfQkuYPmXKd-F3HxyR-478YfyioohHQ_gFUROANWXM";
                String url = "";
                Pxstafftable pxstafftable = iPxstafftableService.getById(pxtuisongtable.getAddStaffID());
                List<TemplateParam> paras = new ArrayList<>();
                paras.add(new TemplateParam("first", pxstutable.getStuName() + "同学,你有新的照片更新信息！", "#FF3333"));
                paras.add(new TemplateParam("keyword1", pxcampustable.getCampusName(), "#0044BB"));
                paras.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                paras.add(new TemplateParam("keyword3", DateUtil.formatDate1(pxtuisongtable.getAddTime()), "#0044BB"));
                paras.add(new TemplateParam("remark", "学员新照片已经上传，请登录小程序查看详细信息", "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                        }
                    }
                }
            } else {//其他通知信息
                templateId = "wnStU0KbsNW6adcYSCdLEyY6Vs3bE1x71BRwtKO3PK4";
                String url = "";
                List<TemplateParam> paras = new ArrayList<>();
                paras.add(new TemplateParam("first", pxstutable.getStuName() + "同学,你有一个通知信息！", "#FF3333"));
                paras.add(new TemplateParam("keyword1", "通知信息", "#0044BB"));
                paras.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                for (WscUserBind binduser : wscUserBinds) {
                    WscUser wscUser = iWscUserService.getById(binduser.getWscuserid());
                    if (wscUser != null) {
                        if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                            QueryWrapper quer = new QueryWrapper<>();
                            quer.eq("unionid", wscUser.getUnionid());
                            GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                            WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                        }
                    }
                }
            }
            return true;

        } else {//推送教师消息
            Pxstafftable pxstafftable = iPxstafftableService.getById(pxtuisongtable.getStaffID());
            Pxcampustable pxcampustable = iPxcampustableService.getById(pxstafftable.getCampusID());
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("phoneNumber", pxstafftable.getStaffTel());
            queryWrapper.eq("qiyeID", pxstafftable.getQiyeID());
            WscUser wscUser = iWscUserService.getOne(queryWrapper);
            if (pxtuisongtable.getTuisongTypeName()==12) { //新签，续费，退费，审批结果消息
                if (wscUser != null) {
                    templateId = "EMFI4qlAg6_BpXZ38ckrf3VfCdbhlw0kJ9u_6eciV7c";
                    String url = "";
                    List<TemplateParam> paras = new ArrayList<>();
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！你有一个新的审批通知！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", DateUtil.formatDate1(pxtuisongtable.getAddTime()), "#0044BB"));
                    paras.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==13) {//课表更新通知
                if (wscUser != null) {
                    templateId = "wnStU0KbsNW6adcYSCdLEyY6Vs3bE1x71BRwtKO3PK4";
                    String url = "";
                    List<TemplateParam> paras = new ArrayList<>();
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！你有新的排课信息！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", "课表更新通知", "#0044BB"));
                    paras.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==14) {//教师上课提醒
                if (wscUser != null) {
                    templateId = "buBlQGiUN6QNC4qxAC_TLfPy-vga4UjGK5xP3b-k4gk";
                    String url = "";
                    Pxkechengtable pxkechengtable = iPxkechengtableService.getById(pxtuisongtable.getKechengID());
                    List<TemplateParam> paras = new ArrayList<>();
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！你有新的上课提醒信息！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", pxkechengtable.getKechengName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword2", DateUtil.formatDate2(pxtuisongtable.getHaveclassDate()) + " " + pxtuisongtable.getStartLessonDateTime().toString().substring(0, 5) + "-" + pxtuisongtable.getEndLessonDateTime().toString().substring(0, 5), "#0044BB"));
                    paras.add(new TemplateParam("keyword3", pxtuisongtable.getNote(), "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==15) {//学员约课申请微信消息（员工）
                if (wscUser != null) {
                    templateId = "328FdUcGVoVK87QsjKvniS19GmrkEJjeuULoblKWX8g";
                    String url = "";
                    Pxkechengtable pxkechengtable = iPxkechengtableService.getById(pxtuisongtable.getKechengID());
                    Pxstutable pxstutable = iPxstutableService.getById(pxtuisongtable.getStuID());
                    List<TemplateParam> paras = new ArrayList<>();
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！你有新的学生约课信息！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", pxstutable.getStuName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword2", pxkechengtable.getKechengName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword3", pxtuisongtable.getNote(), "#0044BB"));
                    paras.add(new TemplateParam("keyword4", "1", "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==16) {//学员请假申请微信消息
                if (wscUser != null) {
                    templateId = "v0BQgZVt7v33IakXt-t_WWnAQS-f1yiifKJa4HzcOpw";
                    String url = "";
                    Pxkechengtable pxkechengtable = iPxkechengtableService.getById(pxtuisongtable.getKechengID());
                    Pxstutable pxstutable = iPxstutableService.getById(pxtuisongtable.getStuID());
                    List<TemplateParam> paras = new ArrayList<>();
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！你有一个学员请假通知！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", pxstutable.getStuName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword2", DateUtil.formatDate2(pxtuisongtable.getHaveclassDate()) + " " + pxtuisongtable.getStartLessonDateTime().toString().substring(0, 5) + "-" + pxtuisongtable.getEndLessonDateTime().toString().substring(0, 5), "#0044BB"));
                    paras.add(new TemplateParam("keyword3", pxkechengtable.getKechengName(), "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==17) {//学员作业上交消息
                if (wscUser != null) {
                    templateId = "Mhl-xu2Nz6kSiopBnq23SBjeX-wMqHKm39L4e8wXlA8";
                    String url = "";
                    List<TemplateParam> paras = new ArrayList<>();
                    Pxstutable pxstutable = iPxstutableService.getById(pxtuisongtable.getStuID());
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！你有一个学员提交了作业！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", pxstutable.getStuName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                    paras.add(new TemplateParam("keyword3", DateUtil.formatDate1(pxtuisongtable.getAddTime()), "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==18) {//意向学员跟进微信提示
                if (wscUser != null) {
                    templateId = "zjwPziMSarwaRbxL_iFuWqAliTHdKjFwHgosn4QtxHg";
                    String url = "";
                    List<TemplateParam> paras = new ArrayList<>();
                    Pxstutable pxstutable = iPxstutableService.getById(pxtuisongtable.getStuID());
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！你有意向学员需要跟进！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", pxstutable.getStuName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword2", pxstutable.getParentTel(), "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==19) {//学员评教的消息
                if (wscUser != null) {
                    templateId = "Xn9aPtjvUn_GG6CLBq34ReHwwv7YUR7q7EV-9oRZIWY";
                    String url = "";
                    List<TemplateParam> paras = new ArrayList<>();
                    Pxstutable pxstutable = iPxstutableService.getById(pxtuisongtable.getStuID());
                    Pxkechengtable pxkechengtable = iPxkechengtableService.getById(pxtuisongtable.getKechengID());
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！有同学对你的课程进行了评价！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", pxstutable.getStuName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword2", pxkechengtable.getKechengName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword3", pxstafftable.getStaffName(), "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            } else if (pxtuisongtable.getTuisongTypeName()==20) {//学员取消了请假
                if (wscUser != null) {
                    templateId = "Ehb5EMO-kj3ZVRsQ-toqUdHObtFwgYCEsVegMAJKY6Y";
                    String url = "";
                    Pxstutable pxstutable = iPxstutableService.getById(pxtuisongtable.getStuID());
                    Pxkechengtable pxkechengtable = iPxkechengtableService.getById(pxtuisongtable.getKechengID());
                    List<TemplateParam> paras = new ArrayList<>();
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！有学生取消了请假！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", pxstutable.getStuName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword2", pxkechengtable.getKechengName(), "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            }else if (pxtuisongtable.getTuisongTypeName()==21){//学员取消约课
                if(wscUser!=null){
                    templateId="X-0-z5PcdmgFbaAdIu8nvjGmIjv992DnWdmEFCmwGlo";
                    String url="";
                    List<TemplateParam> paras = new ArrayList<>();
                    Pxkechengtable pxkechengtable = iPxkechengtableService.getById(pxtuisongtable.getKechengID());
                    Pxstutable pxstutable = iPxstutableService.getById(pxtuisongtable.getStuID());
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！有学生取消了约课！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", DateUtil.formatDate2(pxtuisongtable.getHaveclassDate()) + " " + pxtuisongtable.getStartLessonDateTime().toString().substring(0, 5) + "-" + pxtuisongtable.getEndLessonDateTime().toString().substring(0, 5), "#0044BB"));
                    paras.add(new TemplateParam("keyword2", pxkechengtable.getKechengName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword3",pxstafftable.getStaffName(), "#0044BB"));
                    paras.add(new TemplateParam("keyword4",pxstutable.getStuName(), "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            }
            else {//其他通知
                if (wscUser != null) {
                    templateId = "wnStU0KbsNW6adcYSCdLEyY6Vs3bE1x71BRwtKO3PK4";
                    String url = "";
                    List<TemplateParam> paras = new ArrayList<>();
                    paras.add(new TemplateParam("first", pxstafftable.getStaffName() + "老师,你好！你有新的通知信息！", "#FF3333"));
                    paras.add(new TemplateParam("keyword1", "重要通知", "#0044BB"));
                    paras.add(new TemplateParam("keyword2", pxtuisongtable.getNote(), "#0044BB"));
                    paras.add(new TemplateParam("remark", pxcampustable.getCampusName(), "#0044BB"));
                    if (wscUser.getUnionid() != "" && wscUser.getUnionid() != null) {
                        QueryWrapper quer = new QueryWrapper<>();
                        quer.eq("unionid", wscUser.getUnionid());
                        GzhAlluser gzhAlluser = iGzhAlluserService.getOne(quer);
                        WXTmplMsgUtils.sendWXTmplMsg(templateId, gzhAlluser.getOpenid(), url, paras);
                    }
                }
            }
            return true;
        }
    }
}
