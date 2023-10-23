package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Request.LawyerRequest;
import br.com.AllyWatch.server.DTO.Request.PsychologistRequest;
import br.com.AllyWatch.server.DTO.Response.SpecialistResponse;
import br.com.AllyWatch.server.Domain.KeyCrypt;
import br.com.AllyWatch.server.Domain.Lawyer;
import br.com.AllyWatch.server.Domain.Psychologist;

import static br.com.AllyWatch.server.Domain.Enum.Status.UNDER_REVIEW;
import static br.com.AllyWatch.server.Security.Cryptography.decrypt;
import static br.com.AllyWatch.server.Security.Cryptography.encrypt;

public class SpecialistMapper {

    public static Lawyer toEntity(LawyerRequest request, KeyCrypt key){
        Lawyer lawyer = Lawyer.builder()
                .oabRegisterNumber(
                        encrypt(request.getOabRegisterNumber(), key.getPublicKey())
                )
                .build();

        lawyer.setFullname(
                encrypt(request.getFullname(), key.getPublicKey())
        );
        lawyer.setEmail(
                encrypt(request.getEmail(), key.getPublicKey())
        );
        lawyer.setPhone(
                encrypt(request.getPhone(), key.getPublicKey())
        );
        lawyer.setCity(
                encrypt(request.getCity(), key.getPublicKey())
        );
        lawyer.setState(request.getState());
        lawyer.setStatus(UNDER_REVIEW);
        lawyer.setKeys(key);

        return lawyer;
    }

    public static Psychologist toEntity(PsychologistRequest request, KeyCrypt key){
        Psychologist psychologist = Psychologist.builder()
                .cpfOrCpnj(
                        encrypt(request.getCpfOrCpnj(), key.getPublicKey())
                )
                .registerNumber(
                        encrypt(request.getRegisterNumber(), key.getPublicKey())
                )
                .type(request.getType())
                .keys(key)
                .build();

        psychologist.setFullname(
                encrypt(request.getFullname(), key.getPublicKey())
        );
        psychologist.setEmail(
                encrypt(request.getEmail(), key.getPublicKey())
        );
        psychologist.setCity(
                encrypt(request.getCity(), key.getPublicKey())
        );
        psychologist.setPhone(
                encrypt(request.getPhone(), key.getPublicKey())
        );
        psychologist.setState(request.getState());
        psychologist.setStatus(UNDER_REVIEW);

        return psychologist;
    }

    public static SpecialistResponse toResponse(Lawyer entity) {
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
