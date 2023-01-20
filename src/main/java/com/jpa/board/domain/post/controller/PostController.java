package com.jpa.board.domain.post.controller;

import com.jpa.board.domain.common.CommonResponse;
import com.jpa.board.domain.post.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

}