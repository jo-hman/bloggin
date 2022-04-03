package com.jochman.components.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blogger {

    //todo: add tags field, description etc

    @Id
    @SequenceGenerator(
            name = "blogger_id_sequence",
            sequenceName = "blogger_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "blogger_id_sequence"
    )
    private Long bloggerId;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "blogger",
            cascade = CascadeType.ALL
    )
    private Set<Blog> blogSet;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roleSet;

    private String nickname;
    private String email;

    public void addBlog(Blog blog){
        blogSet.add(blog);
    }
}
