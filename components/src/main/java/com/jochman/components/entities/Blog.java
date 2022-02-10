package com.jochman.components.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {

    //todo:add tags for making recomendations

    @Id
    @SequenceGenerator(
            name = "blog_id_sequence",
            sequenceName = "blog_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "blog_id_sequence"
    )
    private Long blogId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "blogger_fk")
    private Blogger blogger;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "blog",
            cascade = CascadeType.ALL
    )
    private Set<Post> postSet;

    private String blogName;
    private String blogDescription;

    public void addPost(Post post) {
        postSet.add(post);
    }
}
