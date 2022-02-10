package com.jochman.feed;

import com.jochman.components.entities.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Feed {

    private List<Post> postsList = new ArrayList<Post>();

    public void addPosts(List<Post> allPosts) {
        postsList.addAll(allPosts);
    }
}
