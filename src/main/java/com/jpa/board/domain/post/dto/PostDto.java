package com.jpa.board.domain.post.dto;

import com.jpa.board.domain.entities.Post;
import com.jpa.board.domain.user.dto.UserDto;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

public record PostDto (
        long id,
        String title,
        String content
){
    public static PostDto from (Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent());
    }
}
