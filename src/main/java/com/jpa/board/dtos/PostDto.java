package com.jpa.board.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jpa.board.domain.entities.Post;
import com.jpa.board.domain.entities.User;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class PostDto {

    public record GetPostsResponse(List<GetPostDetailsResponse>postDetails) { }

    public record GetPostDetailsResponse(long id, String title, String content) {
        public static GetPostDetailsResponse from(Post post) {
            return new GetPostDetailsResponse(post.getId(), post.getTitle(), post.getContent());
        }
    }

    public record CreatePostRequest(@NotBlank String title, @NotBlank String content, long id) {

        public CreatePostRequest(@JsonProperty("title") String title, @JsonProperty("content") String content, @JsonProperty("userId") long id) {
            this.title = title;
            this.content = content;
            this.id = id;
        }

        public Post toEntity(User user) {
            return new Post(this.title, this.content, user);
        }
    }

    public record ModifyPostRequest(@NotBlank String title, @NotBlank String content) {

        public ModifyPostRequest(@JsonProperty("title") String title, @JsonProperty("content") String content) {
            this.title = title;
            this.content = content;
        }
    }
}