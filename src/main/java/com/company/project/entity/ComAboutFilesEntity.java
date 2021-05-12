package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-12 09:19:52
 */
@Data
@TableName("com_about_files")
public class ComAboutFilesEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId("id")
	private String id;

	/**
	 * 项目id
	 */
	@TableField("about_id")
	private String aboutId;

	/**
	 * 图片id
	 */
	@TableField("file_id")
	private String fileId;

	/**
	 * 排序
	 */
	@TableField("sort")
	private Integer sort;

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


}
