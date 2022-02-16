package com.jochman.notification.notificationTypes;

import com.jochman.notification.notificationTypes.BloggerNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerNotificationRepository extends JpaRepository<BloggerNotification, Long> {
}
