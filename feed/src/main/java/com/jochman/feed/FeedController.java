package com.jochman.feed;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/feed")
public class FeedController {

    private FeedService feedService;

    @GetMapping
    public ResponseEntity<Feed> getFeed(){
        log.info("feed get request");
        return new ResponseEntity<>(feedService.getFeed(), HttpStatus.OK);
    }
}
