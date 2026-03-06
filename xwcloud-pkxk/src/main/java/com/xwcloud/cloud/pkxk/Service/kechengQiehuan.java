package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.xwcloud.cloud.model.entity.*;
import com.xwcloud.cloud.pkxk.Dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
/**
 * 课程切换
 */
public class kechengQiehuan {
    @Autowired
    IPxbuxikechengtableDao iPxbuxikechengtableDao;

    @Autowired
    IPxkechengtableDao iPxkechengtableDao;

    @Autowired
    IPxpaiketableDao iPxpaiketableDao;

    @Autowired
    IPxxuanketableDao iPxxuanketableDao;

    @Autowired
    IPxstuclasstableDao iPxstuclasstableDao;

    @Autowired
    IPxstutableDao iPxstutableDao;

    @Autowired
    IPxclasstableDao iPxclasstableDao;

    @Autowired
    IPxkeshistutableDao iPxkeshistutableDao;

    @Autowired
    IPxsubjecttableDao iPxsubjecttableDao;


    public String kechengQiehuan(Pxbuxikechengtable buxikecheng, Pxbuxikechengtable buxikecheng2, String buxiStyleName, Long classID, Long staffID, long qiyeID) {



        String xiugaiInfo = "班级ID" + classID.toString() + ",补习方式：" + buxiStyleName;

        //改成扣新课程的课时；把老课程隐藏，新课程显示出来；
        buxikecheng.setIsShow(0);
        buxikecheng2.setIsShow(1);
        String kechengName = iPxkechengtableDao.selectById(buxikecheng.getKechengID()).getKechengName();
        xiugaiInfo += "，原补习课程：" + kechengName + "，原价：" + buxikecheng.getOriginalprice().toString() + "，现价：" + buxikecheng.getKechengprice().toString() + "，开始日期：" + buxikecheng.getEndDate() + "，结束日期：" + buxikecheng.getEndDate();
        xiugaiInfo += "，原补习课程ID" + buxikecheng.getId() + "隐藏。现补习课程ID" + buxikecheng2.getId() + "显示";

        //方法1
        //HashMap<String , Object> map=new HashMap<>();
        //map.put("classId",classID);
        //List<Pxpaiketable> claPaikeAll = iPxpaiketableDao.selectByMap(map);

        QueryWrapper<Pxpaiketable> wrapper = new QueryWrapper<>();
        wrapper.eq("classID", classID);
        List<Pxpaiketable> claPaikeAll = iPxpaiketableDao.selectList(wrapper);


        if (claPaikeAll.size() > 0) {

            for (Pxpaiketable oneClapk : claPaikeAll) {

                //已经打过的考勤选课表不动
                QueryWrapper<Pxkeshistutable> kehao = new QueryWrapper<>();
                kehao.eq("kechengID",oneClapk.getKechengID()).eq("classID",oneClapk.getClassID()).eq("stuID",buxikecheng.getStuID()).eq("haveClassDate",oneClapk.getHaveClassDate()).eq("startLessonDateTime",oneClapk.getStartLessonDateTime()).eq("endLessonDateTime",oneClapk.getEndLessonDateTime());
                List<Pxkeshistutable> pdkehao = iPxkeshistutableDao.selectList(kehao);

                if(pdkehao.size()==0){
                    //修改选课表的记录
                    QueryWrapper<Pxxuanketable> xk = new QueryWrapper<>();
                    xk.eq("paikeID",oneClapk.getId()).eq("buxiID",buxikecheng.getId());
                    List<Pxxuanketable> stuXuankeAll = iPxxuanketableDao.selectList(xk);

                    if(stuXuankeAll.size()>0){
                        xiugaiInfo += "，修改选课表里的补习课程ID：";
                        for (Pxxuanketable onestux:stuXuankeAll){
                            xiugaiInfo += "，原补习课程ID" + onestux.getBuxiID();
                            onestux.setBuxiID(buxikecheng2.getId());     //选课表的数据：换课
                            xiugaiInfo += "，修改后的补习课程ID" + buxikecheng2.getId();
                            iPxxuanketableDao.updateById(onestux); //保存
                        }
                    }
                }

                //不管是不是一对一，都要修改插班表里的记录
                QueryWrapper<Pxstuclasstable> stuclq = new QueryWrapper<>();
                stuclq.eq("buxiID",buxikecheng.getId()).eq("classID",classID);
                Pxstuclasstable stucla1 = iPxstuclasstableDao.selectList(stuclq).get(0);

                if(stucla1 !=null){
                    xiugaiInfo += "，原补习课程班级信息不为空";
                    if(buxiStyleName != "一对一"){
                        xiugaiInfo += "，是班课";
                        xiugaiInfo += "，原课程退班";
                        iPxstuclasstableDao.deleteById(stucla1.getId());

                        //buxikecheng2插班
                        QueryWrapper<Pxstuclasstable> stuclq2 = new QueryWrapper<>();
                        stuclq2.eq("buxiID",buxikecheng2.getId()).eq("classID",classID);
                        Pxstuclasstable stucla2 = iPxstuclasstableDao.selectList(stuclq2).get(0);

                        if (stucla2 ==null){
                            Pxstuclasstable one = new Pxstuclasstable();
                            one.setBuxiID(buxikecheng2.getId());
                            one.setClassID(classID);
                            one.setQiyeID(qiyeID);
                            iPxstuclasstableDao.insert(one);
                            xiugaiInfo += "，新课程插班";
                        }

                    }
                    else{
                        xiugaiInfo += "，是一对一";
                        //如果是一对一，交换stuClassTable里面的buxiID

                        QueryWrapper<Pxstuclasstable> stuclq3 = new QueryWrapper<>();
                        stuclq3.eq("buxiID",buxikecheng2.getId()).eq("classID",classID);
                        Pxstuclasstable stucla3 = iPxstuclasstableDao.selectList(stuclq3).get(0);

                        if(stucla3 ==null){
                            //新课程不在本班
                            xiugaiInfo += "，新课程不在本班";

                            QueryWrapper<Pxstuclasstable> stuclq4 = new QueryWrapper<>();
                            stuclq4.eq("buxiID",buxikecheng2.getId());
                            Pxstuclasstable stucla4 = iPxstuclasstableDao.selectList(stuclq4).get(0);

                            if(stuclq4 !=null){
                                xiugaiInfo += "，新课程有班，接下来和老课程在stuClassTable里的buxiID互换！";
                                Long t = stucla1.getBuxiID();
                                stucla4.setBuxiID(stucla1.getBuxiID());
                                stucla1.setBuxiID(t);

                                //String t = stucla3.getBuxiID();
                                //stucla4.setBuxiID(stucla1.getBuxiID());
                                //stucla1.setBuxiID(t);

                                iPxstuclasstableDao.updateById(stucla1);
                                iPxstuclasstableDao.updateById(stucla4);
                                xiugaiInfo += "，原补习课程ID" + stucla1.getBuxiID() + "，现补习课程ID" + stucla4.getBuxiID();

                            }else {
                                xiugaiInfo += "，新课程没有班级；接下来：先把老课程创建一个班级并插班，再将新课程插入到老课程的班级；";
                                //如果新课程没有班级，先把老课程创建一个班级并插班，再将新课程插入到老课程的班级
                                Long thisclassID=0L;
                                Pxstutable stu = iPxstutableDao.selectById(buxikecheng.getStuID());
                                Pxkechengtable kc = iPxkechengtableDao.selectById(buxikecheng.getKechengID());
                                String subjectName = iPxsubjecttableDao.selectById(kc.getSubjectID()).getSubjectName();

                                String myNewClassName="";

                                boolean ii=false;
                                while (ii==false){
                                    Random rd= new Random();
                                    int sjs = rd.nextInt(999);
                                    String thisClassName = stu.getStuName()+"_"+subjectName+"_一对一"+sjs;

                                    QueryWrapper<Pxclasstable> qcl= new QueryWrapper<>();
                                    qcl.eq("className",thisClassName);
                                    List<Pxclasstable> pdclass = iPxclasstableDao.selectList(qcl);
                                    if(pdclass.size()==0){
                                        Pxclasstable cl = new Pxclasstable();
                                        cl.setClassName(thisClassName);
                                        cl.setAddStaffID(staffID);
                                        cl.setQiyeID(qiyeID);
                                        cl.setAddTime(new Date());
                                        cl.setCampusID(stu.getCampusID());
                                        cl.setIs1v1Class(1);
                                        cl.setIsdelete(false);
                                        cl.setIsShow(0);
                                        iPxclasstableDao.insert(cl);

                                        thisclassID = cl.getId();
                                        myNewClassName=thisClassName;
                                        ii=true;
                                    }
                                }

                                xiugaiInfo += "，新建班级完成classID"+thisclassID+ ",班级名称："+ myNewClassName;
                                //老补习课程插到刚刚新建的班级
                                Pxstuclasstable stucl = new Pxstuclasstable();
                                stucl.setBuxiID(buxikecheng.getId());
                                stucl.setQiyeID(qiyeID);
                                stucl.setClassID(thisclassID);
                                iPxstuclasstableDao.insert(stucl);

                                xiugaiInfo += "，老补习课程ID" + buxikecheng.getId() + "插到刚刚新建的班级";
                                //新补习课程插入到老课程的班级
                                stucla1.setBuxiID(buxikecheng2.getId());
                                iPxstuclasstableDao.updateById(stucla1);
                                xiugaiInfo += "，新补习课程ID" + buxikecheng2.getId() + "插入到老课程的班级";
                            }
                        }
                    }
                }
            }
        }

        xiugaiInfo += "课程切换完成。";
        return xiugaiInfo;
    }
}
