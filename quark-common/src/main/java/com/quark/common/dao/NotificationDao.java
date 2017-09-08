package com.quark.common.dao;

import com.quark.common.entity.Notification;
import com.quark.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author LHR
 * Create By 2017/9/6
 */
@Repository
public interface NotificationDao extends JpaRepository<Notification, Integer> {

    @Query(value = "SELECT count(id) FROM quark_notification n WHERE n.touser_id = ?1 AND n.is_read = 0", nativeQuery = true)
    long getNotificationCount(Integer id);

    List<Notification> getByTouserOrderByInitTimeDesc(User user);

    @Modifying
    @Query("update Notification n set n.isRead = true where n.touser = ?1")
    void updateByIsRead(User user);

}
