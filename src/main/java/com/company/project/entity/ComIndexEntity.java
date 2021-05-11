package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 首页
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-11 17:18:10
 */
@Data
@TableName("com_index")
public class ComIndexEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId("id")
	private String id;

	/**
	 * 首页标题

	 */
	@TableField("name")
	private String name;

	/**
	 * 首页文本
	 */
	@TableField("text_value")
	private String textValue;

	/**
	 * 首页图片url
	 */
	@TableField("img_url")
	private String imgUrl;

	/**
	 * 图片是否启用0：不启用；1：启用
	 */
	@TableField("img_status")
	private String imgStatus;

	/**
	 * 首页视频url
	 */
	@TableField("video_url")
	private String videoUrl;

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
