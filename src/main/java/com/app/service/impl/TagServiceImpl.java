package com.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.app.entity.Tag;
import com.app.mapper.TagMapper;
import com.app.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Resource
    private TagMapper tagMapper;
}

