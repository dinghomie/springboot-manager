package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 联系我们
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-11 17:18:11
 */
@Data
@TableName("com_contrct")
public class ComContrctEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId("id")
	private String id;

	/**
	 * 公司名称
	 */
	@TableField("company_name")
	private String companyName;

	/**
	 * 公司简称
	 */
	@TableField("company_short_name")
	private String companyShortName;

	/**
	 * 手机
	 */
	@TableField("tel")
	private String tel;

	/**
	 * 二维码图片id
	 */
	@TableField("file_id")
	private String fileId;

	/**
	 * 邮箱
	 */
	@TableField("email")
	private String email;

	/**
	 * 城市
	 */
	@TableField("city")
	private String city;

	/**
	 * 详细地址
	 */
	@TableField("address")
	private String address;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;


}
