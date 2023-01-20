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
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("해당 게시글을 찾을 수 없습니다.", String.valueOf(postId))));

        return GetPostDetailsResponse.from(savedPost);
    }

    // 게시글 작성
    public long createPost(CreatePostRequest createPostRequest) {
        User user = userRepository.findById(createPostRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("해당 유저를 찾을 수 없습니다.", createPostRequest.id())));
        Post post = postRepository.save(createPostRequest.toEntity(user));

        return post.getId();
    }

    // 게시글 수정
    public void modifyPost(long id, ModifyPostRequest modifyPostRequest){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("해당 게시글을 찾을 수 없습니다.", id)));
        post.update(modifyPostRequest.title(), modifyPostRequest.content());
    }
}
