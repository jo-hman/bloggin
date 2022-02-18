package com.jochman.notification.types;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BlogNotification {

    @Id
    @SequenceGenerator(
            name = "blog_notification_id_sequence",
            sequenceName = "blog_notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "blog_notification_id_sequence"
    )
    private Long NotificationId;

    private Long blogId;

    private String notificationMessage;

}
