package com.jochman.notification.types;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BloggerNotification {

    @Id
    @SequenceGenerator(
            name = "blogger_notification_id_sequence",
            sequenceName = "blogger_notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "blogger_notification_id_sequence"
    )
    private Long NotificationId;

    private Long bloggerId;

    private String notificationMessage;

}
