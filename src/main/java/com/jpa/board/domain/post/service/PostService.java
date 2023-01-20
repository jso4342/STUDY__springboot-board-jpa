package com.jpa.board.domain.post.service;

import com.jpa.board.domain.entities.Post;
import com.jpa.board.domain.post.dto.PostDto;
import com.jpa.board.domain.post.dto.PostsDto;
import com.jpa.board.domain.post.repository.PostRepository;
import com.jpa.board.domain.user.service.UserService;
import com.jpa.board.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public PostsDto getPosts(Pageable pageable) {
        Page<Post> postPage = postRepository.findAll(pageable);

        return new PostsDto(
                postPage.stream()
                        .map(PostDto::from)
                        .toList()
        );
    }

    public PostDto getPostDetail(long postId) {
        Post savedPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("Cannot find the relevant Post. Please check post id. [Post ID]: {0}", String.valueOf(postId))));

        return PostDto.from(savedPost);
    }
}
