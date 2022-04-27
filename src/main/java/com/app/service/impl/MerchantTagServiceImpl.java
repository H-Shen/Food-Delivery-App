package com.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.app.entity.MerchantTag;
import com.app.mapper.MerchantTagMapper;
import com.app.service.MerchantTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("merchantTagService")
public class MerchantTagServiceImpl extends ServiceImpl<MerchantTagMapper, MerchantTag> implements MerchantTagService {
    @Resource
    private MerchantTagMapper merchantTagMapper;

    @Override
    public List<Map<String, Object>> listByTagName(String tagName) {
        return merchantTagMapper.listByTagName(tagName);
    }
}
