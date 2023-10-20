package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Response.SpecialistResponse;
import br.com.AllyWatch.server.Domain.Lawyer;
import br.com.AllyWatch.server.Domain.Psychologist;

import static br.com.AllyWatch.server.Security.Cryptography.decrypt;

public class SpecialistMapper {
    public static SpecialistResponse toResponse(Lawyer entity) {
        return SpecialistResponse.builder()
                .id(entity.getId())
                .state(entity.getSeccional())
                .fullname(
                        decrypt(entity.getFullname(), entity.getKeys().getPrivateKey())
                )
                .email(
                        decrypt(entity.getEmail(), entity.getKeys().getPrivateKey())
                )
                .phone(
                        decrypt(entity.getPhone(), entity.getKeys().getPrivateKey())
                )
                .city(
                        decrypt(entity.getCity(), entity.getKeys().getPrivateKey())
                )
                .build();
    }

    public static SpecialistResponse toResponse(Psychologist entity) {
        return SpecialistResponse.builder()
                .id(entity.getId())
                .state(entity.getState())
                .fullname(
                        decrypt(entity.getFullname(), entity.getKeys().getPrivateKey())
                )
                .email(
                        decrypt(entity.getEmail(), entity.getKeys().getPrivateKey())
                )
                .phone(
                        decrypt(entity.getPhone(), entity.getKeys().getPrivateKey())
                )
                .city(
                        decrypt(entity.getCity(), entity.getKeys().getPrivateKey())
                )
                .build();
    }
}
