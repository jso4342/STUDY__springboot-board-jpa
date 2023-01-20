package com.jpa.board.domain.post;

import com.jpa.board.domain.post.repository.PostRepository;
import com.jpa.board.domain.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PostServiceTest {
    @Autowired
    private PostRepository postRepository;

    private PostService postService = new PostService(postRepository);


    @DisplayName("페이징을 통한 게시글 조회")
    @Test
    void getPosts() {
    }

    @DisplayName("한 개의 게시글 조회")
    @Test
    void getPostDetail() {
    }
}
