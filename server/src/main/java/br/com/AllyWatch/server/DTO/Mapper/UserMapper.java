package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Request.AddUserRequest;
import br.com.AllyWatch.server.Domain.User;

import static br.com.AllyWatch.server.Domain.Enum.Icon.NEUTRAL;

public class UserMapper {
    public static User toEntity(AddUserRequest request) {
        return User.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .icon(NEUTRAL)
                .build();
    }
}
