package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xwcloud.cloud.model.Vo.menubuttonslistVO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author yinqi
 * @since 2020-10-20
 */
@Data
@Accessors(chain = true)
public class Pxmenutable extends Model<Pxmenutable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("text")
	private String text;
	@TableField("fid")
	private Long fid;
	@TableField("url")
	private String url;
	@TableField("iconCls")
	private String iconCls;
	@TableField("paixu")
	private Integer paixu;
    /**
     * 菜单级别，1，2，3分别表示一级，二级，三级菜单
     */
	@TableField("levelID")
	private Integer levelID;
    /**
     * 是否显示，1显示，0不显示，默认值1
     */
	@TableField("isShow")
	private Integer isShow;

	private List<menubuttonslistVO> menubtns =new ArrayList<>();

	private String datafanwei;
	private Boolean checkmenu;
	private long staffpostID;
	private String dataFanweicheck;

	public List<Pxmenutable> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Pxmenutable> treeList) {
		this.treeList = treeList;
	}


	private List<Pxmenutable> treeList =new ArrayList<>();


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxmenutable{" +
			", id=" + id +
			", text=" + text +
			", fid=" + fid +
			", url=" + url +
			", iconCls=" + iconCls +
			", paixu=" + paixu +
			", levelID=" + levelID +
			", isShow=" + isShow +
				//",menulist="+menulist+
			"}";
	}
}
