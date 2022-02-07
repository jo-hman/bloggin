package com.jochman.clients.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @SequenceGenerator(
            name = "post_id_sequence",
            sequenceName = "post_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_id_sequence"
    )
    private Long postId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "blog_fk", referencedColumnName = "blogId")
    private Blog blog;

    private String postTitle;
    private String postContent;
    //todo: consider how post content should be stored and how to add any media like images, videos, etc.
}
