package com.quark.admin.service.impl;

import com.quark.admin.service.LabelService;
import com.quark.common.base.BaseServiceImpl;
import com.quark.common.dao.LabelDao;
import com.quark.common.entity.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Author LHR
 * Create By 2017/9/3
 */
@Service
public class LabelServiceImpl extends BaseServiceImpl<LabelDao,Label> implements LabelService{


    @Override
    public Page<Label> findByPage(int pageNo, int length) {
        PageRequest pageRequest = new PageRequest(pageNo, length);
        Page<Label> page = repository.findAll(pageRequest);
        return page;
    }
}
