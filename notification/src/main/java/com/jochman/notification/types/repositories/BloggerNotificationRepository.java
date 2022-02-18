package com.jochman.notification.types.repositories;

import com.jochman.notification.types.BloggerNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerNotificationRepository extends JpaRepository<BloggerNotification, Long> {
}
