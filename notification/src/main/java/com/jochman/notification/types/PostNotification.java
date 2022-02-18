package com.jochman.notification.types;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PostNotification {

    @Id
    @SequenceGenerator(
            name = "post_notification_id_sequence",
            sequenceName = "post_notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_notification_id_sequence"
    )
    private Long notificationId;

    private Long postId;

    private String notificationMessage;

}
