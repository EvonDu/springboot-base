package com.self.base.modules.example.service.impl;

import com.self.base.modules.example.entity.Demo;
import com.self.base.modules.example.mapper.DemoMapper;
import com.self.base.modules.example.service.IDemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author base
 * @since 2020-04-03
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements IDemoService {

}
