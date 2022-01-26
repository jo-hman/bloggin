package com.jochman.blogger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.jochman.blog.Blog;


import javax.persistence.*;
import java.util.HashSet;

@Data
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
    private Long id;

    //todo: create a Feign Client for both blog and blogger
    @OneToMany(mappedBy = "blogger")
    private HashSet<Blog> blogsSet;

    private String nickName;
    private String email;
    //todo: create relation between user and blog(user is supposed to have his own blogs)
}
