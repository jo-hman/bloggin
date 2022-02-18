package com.jochman.notification.types.repositories;

import com.jochman.notification.types.BlogNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogNotificationRepository extends JpaRepository<BlogNotification, Long> {
}
