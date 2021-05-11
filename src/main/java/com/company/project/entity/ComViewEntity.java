package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 品牌观
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-11 17:18:10
 */
@Data
@TableName("com_view")
public class ComViewEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId("id")
	private String id;

	/**
	 * 标题
	 */
	@TableField("name")
	private String name;

	/**
	 * 文本
	 */
	@TableField("text_value")
	private String textValue;

	/**
	 * 图片url
	 */
	@TableField("img_id")
	private String imgId;

	/**
	 * 图片是否启用0：不启用；1：启用
	 */
	@TableField("img_status")
	private String imgStatus;

	/**
	 * 视频url
	 */
	@TableField("video_id")
	private String videoId;

	/**
	 * 视频是否启用0：不启用；1：启用
	 */
	@TableField("video_status")
	private String videoStatus;

	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 排序
	 */
	@TableField("sort")
	private Integer sort;


}
