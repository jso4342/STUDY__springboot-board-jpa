package com.jpa.board.domain.post;

import com.jpa.board.domain.entities.User;
import com.jpa.board.domain.user.UserRepository;
import com.jpa.board.domain.user.UserService;
import com.jpa.board.dtos.PostDto.*;

import com.jpa.board.domain.entities.Post;
import com.jpa.board.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // 게시글 조회 (페이징)
    @Transactional(readOnly = true)
    public GetPostsResponse getPosts(Pageable pageable) {
        Page<Post> postPage = postRepository.findAll(pageable);

        return new GetPostsResponse(
                postPage.stream()
                        .map(GetPostDetailsResponse::from)
                        .toList()
        );
    }

    // 게시글 조회 (단건)
    @Transactional(readOnly = true)
    public GetPostDetailsResponse getPostDetail(long postId) {
        Post savedPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("Cannot find the relevant Post. Please check post id. [Post ID]: {0}", String.valueOf(postId))));

        return GetPostDetailsResponse.from(savedPost);
    }

    // 게시글 작성
    public long createPost(CreatePostRequest createPostRequest) {
        User user = userRepository.findById(createPostRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("Can not find User. Please check user id. [User ID]: {0}", createPostRequest.id())));

        Post post = postRepository.save(createPostRequest.toEntity(user));
        return post.getId();
    }
}
