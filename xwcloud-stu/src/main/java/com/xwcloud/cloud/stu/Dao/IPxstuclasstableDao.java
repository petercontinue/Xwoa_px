package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.AsClassTOStuVo;
import com.xwcloud.cloud.model.Vo.exportclassstuVo;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Repository
public interface IPxstuclasstableDao extends BaseMapper<Pxstuclasstable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buxiID", property = "buxiID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstuclasstable"
            + "</script>")
    List<Pxstuclasstable> getAllList();

    @Select("<script>" +
            "SELECT * from  Pxstuclasstable " +
            " WHERE 1=1  " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxstuclasstable> selectstuclass(@Param("ew") QueryWrapper queryWrapper);


    @Select("<script>" + "select * from pxstuclasstable where buxiID=#{buxiID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxstuclasstable> getBybxID(Long buxiID, Long qiyeID);

    @Select("<script>" + "select * from pxstuclasstable  where classID=#{classID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxstuclasstable> getstuclass(Long classID, Long qiyeID);

    @Select("<script>" + "select * from pxstuclasstable  where  buxiID=#{buxiID} and  classID=#{classID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxstuclasstable> getBybxAndclassID(Long buxiID,Long classID, Long qiyeID);

    /**
     * 按照班级查学员（分页）
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "select stucl.id as id,pxbuxikechengtable.stuID as stuID,pxstutable.stuName as stuName,pxstutable.parentTel as parentTel,pxkechengtable.kechengName as kechengName,pxstugradetable.stuGradeName as stuGradeName,pxstutable.zidingyiStuID zidingyiStuID  " +
            "from pxstuclasstable as stucl " +
            "LEFT JOIN pxclasstable on stucl.classID=pxclasstable.id " +
            "LEFT JOIN pxbuxikechengtable on stucl.buxiID=pxbuxikechengtable.id " +
            "LEFT JOIN pxkechengtable on pxbuxikechengtable.kechengID=pxkechengtable.id " +
            "LEFT JOIN pxstutable on pxbuxikechengtable.stuID=pxstutable.id " +
            "LEFT JOIN pxstugradetable on pxstutable.stuGradeID=pxstugradetable.id " +
            "LEFT JOIN pxcampustable on pxstutable.campusID = pxcampustable.id  " +
            " WHERE pxcampustable.isOpen=1 and (pxstutable.buxiStateID =2 or pxstutable.buxiStateID =3 or pxstutable.buxiStateID =6)" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<AsClassTOStuVo> AsClassTOStuPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT e.campusName campusName,(case WHEN d.zidingyiClassID is null THEN d.id ELSE d.zidingyiClassID END) classID,d.className className,\n" +
            "f.kechengName kechengName,(case WHEN c.zidingyiStuID is null THEN b.stuID ELSE c.zidingyiStuID END) stuID,c.stuName stuName,\n" +
            "c.parentTel parentTel,c.remainXuefei remainXuefei,b.remainkeshi remainkeshi,\n" +
            "(SELECT (case WHEN COUNT(id) > 0 THEN SUM(HetongMoney) ELSE 0 END) from pxqiandaninfotable where id =ANY(SELECT qianDanInfoID from pxqiandansubjecttable where stuID=c.id and kechengID=b.kechengID) ) yingshou ,\n" +
            "(SELECT (case WHEN COUNT(id) > 0 THEN SUM(shishouTotalMoney) ELSE 0 END) from pxqiandaninfotable where id =ANY(SELECT qianDanInfoID from pxqiandansubjecttable where stuID=c.id and kechengID=b.kechengID) ) shishou ,\n" +
            "(SELECT  (case WHEN GROUP_CONCAT(DISTINCT l.staffName) is not null THEN GROUP_CONCAT(DISTINCT l.staffName) ELSE '-' END) from pxqiandaninfotable j LEFT JOIN pxqiandanstafftable k on j.id=k.qiandanID LEFT JOIN pxstafftable l on k.staffID=l.id WHERE j.stuID=b.stuID) yejiren,\n" +
            "(SELECT (case WHEN COUNT(g.id) > 0 THEN SUM(g.zongjia) ELSE 0 END) from pxqiandansubjecttable g where g.stuID=c.id and g.kechengID=f.id) jiaofeiNum\n" +
            "from pxstuclasstable a \n" +
            "LEFT JOIN pxbuxikechengtable b on a.buxiID=b.id\n" +
            "LEFT JOIN pxstutable c on b.stuID=c.id\n" +
            "LEFT JOIN pxclasstable d on a.classID=d.id\n" +
            "LEFT JOIN pxcampustable e on d.campusID = e.id\n" +
            "LEFT JOIN pxkechengtable f on b.kechengID=f.id\n" +
            "where e.isOpen!=2 and d.isdelete !=true \n "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<exportclassstuVo> getClassStu(@Param("ew") QueryWrapper queryWrapper);
}