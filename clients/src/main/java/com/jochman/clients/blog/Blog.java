package com.jochman.clients.blog;

import com.jochman.clients.blogger.Blogger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    @ManyToOne(
            fetch = FetchType.LAZY
            )
    @JoinColumn(name = "blogger_fk")
    private Blogger blogger;
    private String blogName;
    private String blogDescription;

    //todo: Blog has posts (make a relation)
}
