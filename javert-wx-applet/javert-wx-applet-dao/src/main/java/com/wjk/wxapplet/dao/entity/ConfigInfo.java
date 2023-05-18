package com.wjk.wxapplet.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配置信息 实体类
 * @author wangjunkai
 * @date 2023-05-18
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ac_config_info")
public class ConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("CREATOR")
    private String creator;

    @TableField("GMT_CREATE")
    private Date gmtCreate;

    @TableField("MODIFIER")
    private String modifier;

    @TableField("GMT_MODIFIED")
    private Date gmtModified;

    /**
     * 组编码
     */
    @TableField("BATCH_CODE")
    private String batchCode;

    /**
     * 组编码名称
     */
    @TableField("BATCH_NAME")
    private String batchName;

    /**
     * 编码
     */
    @TableField("CODE")
    private String code;

    /**
     * 值
     */
    @TableField("VALUE")
    private String value;

    @TableField("DELETE_PK")
    private Long deletePk;


}
