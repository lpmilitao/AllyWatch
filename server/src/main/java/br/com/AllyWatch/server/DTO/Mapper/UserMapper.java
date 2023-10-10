package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.Domain.User;

import static br.com.AllyWatch.server.Domain.Enum.Icon.NEUTRAL;

public class UserMapper {
    public static User toEntity() {
        return User.builder()
                .active(true)
                .icon(NEUTRAL)
                .build();
    }
}
