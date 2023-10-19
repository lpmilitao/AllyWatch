package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Request.LawyerRequest;
import br.com.AllyWatch.server.Domain.KeyCrypt;
import br.com.AllyWatch.server.Domain.Lawyer;
import br.com.AllyWatch.server.Repository.LawyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.AllyWatch.server.Domain.Enum.Status.UNDER_REVIEW;
import static br.com.AllyWatch.server.Security.Cryptography.encrypt;

@Service
public class LawyerService {

    @Autowired
    private LawyerRepository lawyerRepository;

    @Autowired
    private KeyService keyService;

    public void add(LawyerRequest request) {

        KeyCrypt key = keyService.findKey();

        Lawyer lawyer = Lawyer.builder()
                .fullname(
                        encrypt(request.getFullname(), key.getPublicKey())
                )
                .oabRegisterNumber(
                        encrypt(request.getOabRegisterNumber(), key.getPublicKey())
                )
                .email(
                        encrypt(request.getEmail(), key.getPublicKey())
                )
                .phone(
                        encrypt(request.getPhone(), key.getPublicKey())
                )
                .city(
                        encrypt(request.getCity(), key.getPublicKey())
                )
                .seccional(request.getSeccional())
                .status(UNDER_REVIEW)
                .keys(key)
                .build();

        lawyerRepository.save(lawyer);
    }
}
