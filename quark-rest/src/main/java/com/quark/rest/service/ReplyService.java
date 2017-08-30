package com.quark.rest.service;

import com.quark.common.base.BaseService;
import com.quark.common.entity.Reply;
import com.quark.common.entity.User;
import org.springframework.data.domain.Page;

public interface ReplyService extends BaseService<Reply> {

    /**
     * 翻页获取回复
     *
     * @param postsId
     * @param pageNo
     * @param length
     * @return
     */
    Page<Reply> getReplyByPage(Integer postsId, int pageNo, int length);

    /**
     * 保存回复
     * @param reply
     * @param postsId
     * @param user
     */
    void saveReply(Reply reply,Integer postsId,User user);
}
