package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 项目
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-11 17:18:10
 */
@Data
@TableName("com_works")
public class ComWorksEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId("id")
	private String id;

	/**
	 * 项目名称
	 */
	@TableField("work_name")
	private String workName;

	/**
	 * 文本
	 */
	@TableField("text_value")
	private String textValue;

	/**
	 * 项目时间
	 */
	@TableField("work_time")
	private Date workTime;

	/**
	 * 服务客户
	 */
	@TableField("custom")
	private String custom;

	/**
	 * 类型0:logo;1:buis;2:package;3:space;4:sign
	 */
	@TableField("status")
	private String status;

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
