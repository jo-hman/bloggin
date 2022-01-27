package com.jochman.clients.blogger;

import com.jochman.clients.blog.Blog;
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

    //todo: create a Feign Client for both blog and blogger
//    @OneToMany(
//            targetEntity = Blog.class,
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(name = "blog_fk", referencedColumnName = "bloggerId")
//    private Set<Blog> blogList;
    private String nickName;
    private String email;
}
