package com.wjk.wxappletweb.service.impl;

import com.wjk.wxapplet.dao.entity.ConfigInfo;
import com.wjk.wxapplet.dao.mapper.ConfigInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjk.wxappletweb.service.IConfigInfoService;
import org.springframework.stereotype.Service;

/**
 * 配置信息 服务实现类
 * @author wangjunkai
 * @date 2023-05-18
 * @version 1.0.0
 */
@Service
public class ConfigInfoServiceImpl extends ServiceImpl<ConfigInfoMapper, ConfigInfo> implements IConfigInfoService {

}
