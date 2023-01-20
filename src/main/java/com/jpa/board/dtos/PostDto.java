package com.jpa.board.dtos;

import com.jpa.board.domain.entities.Post;

public record PostDto (
        long id,
        String title,
        String content
){
    public static PostDto from (Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent());
    }
}
