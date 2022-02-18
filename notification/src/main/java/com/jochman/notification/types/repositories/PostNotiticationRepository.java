package com.jochman.notification.types.repositories;

import com.jochman.notification.types.PostNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostNotiticationRepository extends JpaRepository<PostNotification, Long> {
}
