package com.jochman.clients.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blogger {

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Long bloggerId;

    @JsonBackReference
    @OneToMany(
            mappedBy = "blogger",
            cascade = CascadeType.ALL
    )

    private Set<Blog> blogSet;

    private String nickName;
    private String email;

    public void addBlog(Blog blog){
        blogSet.add(blog);
    }
}
