package com.jochman.notification;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;


    public void send(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .bloggerId(notificationRequest.bloggerId())
                .notificationMessage(notificationRequest.notificationMessage())
                .build();
        notificationRepository.save(notification);
    }
}
