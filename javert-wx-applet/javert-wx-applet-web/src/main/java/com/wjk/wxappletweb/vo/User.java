package com.wjk.wxappletweb.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/5/18 14:03
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String account;
    private String password;
    private String key;
}
