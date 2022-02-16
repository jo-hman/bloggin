package com.jochman.notification.notificationTypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostNotiticationRepository extends JpaRepository<PostNotification, Long> {
}
