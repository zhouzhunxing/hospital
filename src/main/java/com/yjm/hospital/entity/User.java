package com.yjm.hospital.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Package: com.yjm.hospital.entity
 * @Description: <用户实体>
 * @Author: zzx
 * @CreateDate: user
 */
@DynamicInsert(true)
@DynamicUpdate(true)
@Entity
@Data
@Table(name = "user")
@ApiModel(value = "用户实体")
public class User implements Serializable {

    /**
     * 操作员主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "操作员主键", notes = "")
    private Long userid;

    /**
     * 操作员编码
     */
    @ApiModelProperty(value = "操作员编码", notes = "")
    private String usercode;

    /**
     * 操作员名称
     */
    @ApiModelProperty(value = "操作员名称", notes = "")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", notes = "")
    private String password;

    /**
     * 加密加盐
     */
    @ApiModelProperty(value = "加密加盐", notes = "")
    private String salt;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", notes = "")
    private String sex;

    /**
     * 作废标识
     */
    @ApiModelProperty(value = "作废标识", notes = "")
    private Long blnisvoid;

    /**
     * 停启用
     */
    @ApiModelProperty(value = "停启用", notes = "")
    private Long blnisactive;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", notes = "")
    private Long createby;


}
