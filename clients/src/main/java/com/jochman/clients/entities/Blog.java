package com.jochman.clients.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {

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

//    @JsonBackReference
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "blogger_fk", referencedColumnName = "bloggerId")
    private Blogger blogger;

    @JsonBackReference
    @OneToMany(
            mappedBy = "blog",
            cascade = CascadeType.ALL
    )
    private Set<Post> postSet;

    private String blogName;
    private String blogDescription;
}
