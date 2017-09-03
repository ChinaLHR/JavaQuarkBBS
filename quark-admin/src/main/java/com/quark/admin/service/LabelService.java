package com.quark.admin.service;

import com.quark.common.base.BaseService;
import com.quark.common.entity.Label;
import org.springframework.data.domain.Page;

public interface LabelService extends BaseService<Label> {

    /**
     * 翻页查询
     * @param pageNo
     * @param length
     * @return
     */
    Page<Label> findByPage(int pageNo, int length);
}
