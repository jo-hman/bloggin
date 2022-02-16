package com.jochman.notification;

import com.jochman.components.requestBodies.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
//    private final BloggerNotificationRepository bloggerNotificationRepository;
//    private final BlogNotificationRepository blogNotificationRepository;
//    private final PostNotiticationRepository postNotiticationRepository;


    public void send(NotificationRequest notificationRequest) {
//        switch(notificationRequest.entityType()){
//            case BLOGGER: {
//                BloggerNotification notification = BloggerNotification.builder()
//                        .bloggerId(notificationRequest.entityId())
//                        .notificationMessage(notificationRequest.notificationMessage())
//                        .build();
//                bloggerNotificationRepository.save(notification);
//                break;
//            }
//
//            case BLOG: {
//                BlogNotification notification = BlogNotification.builder()
//                        .blogId(notificationRequest.entityId())
//                        .notificationMessage(notificationRequest.notificationMessage())
//                        .build();
//                blogNotificationRepository.save(notification);
//                break;
//            }
//            case POST: {
//                PostNotification notification = PostNotification.builder()
//                        .postId(notificationRequest.entityId())
//                        .notificationMessage(notificationRequest.notificationMessage())
//                        .build();
//                postNotiticationRepository.save(notification);
//                break;
//            }
//        }
        Notification notification = Notification.builder()
                .bloggerId(notificationRequest.entityId())
                .notificationMessage(notificationRequest.notificationMessage())
                .build();

        notificationRepository.save(notification);
    }
}
