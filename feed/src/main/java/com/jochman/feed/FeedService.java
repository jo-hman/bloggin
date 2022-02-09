package com.jochman.feed;

import com.jochman.components.clients.post.PostClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class FeedService {

    private PostClient postClient;


    public Feed getFeed() {
        //todo: make a feed based on user's preferences
        Feed feed = new Feed();
        feed.addPosts(postClient.getAllPosts());

        return feed;
    }
}
