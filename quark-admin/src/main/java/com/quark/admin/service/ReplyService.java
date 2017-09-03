package com.quark.admin.service;

import com.quark.common.base.BaseService;
import com.quark.common.entity.Reply;
import org.springframework.data.domain.Page;

/**
 * @Author LHR
 * Create By 2017/9/3
 */
public interface ReplyService extends BaseService<Reply>{

    /**
     * 翻页条件查询回复
     * @param reply
     * @param pageNo
     * @param length
     * @return
     */
   Page<Reply> findByPage(Reply reply, int pageNo, int length);

    
}
