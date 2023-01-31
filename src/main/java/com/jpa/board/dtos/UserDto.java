package com.jpa.board.dtos;

import com.jpa.board.domain.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDto {
    public record UserRequest(@NotBlank String name, String hobby, int age) {
        public User toEntity() {
            return new User(name, hobby, age);
        }
    }

    public record UserResponse(@NotNull Long userId, @NotBlank String name, String hobby, int age) {
        public static UserResponse from(User user) {
            return new UserResponse(user.getId(), user.getName(), user.getHobby(), user.getAge());
        }
    }
}
