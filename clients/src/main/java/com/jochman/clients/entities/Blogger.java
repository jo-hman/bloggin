package com.jochman.clients.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;


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

//    @JsonManagedReference
    @JsonBackReference
    @OneToMany(
            mappedBy = "blogger",
            cascade = CascadeType.ALL
    )
//    @JoinColumn(name = "blog_fk")
    private Set<Blog> blogSet;

    private String nickName;
    private String email;
}
