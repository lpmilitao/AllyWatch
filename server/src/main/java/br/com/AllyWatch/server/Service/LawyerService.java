package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Mapper.SpecialistMapper;
import br.com.AllyWatch.server.DTO.Request.LawyerRequest;
import br.com.AllyWatch.server.DTO.Request.VerifySpecialistRequest;
import br.com.AllyWatch.server.DTO.Response.SpecialistResponse;
import br.com.AllyWatch.server.Domain.Enum.Status;
import br.com.AllyWatch.server.Domain.KeyCrypt;
import br.com.AllyWatch.server.Domain.Lawyer;
import br.com.AllyWatch.server.Repository.LawyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Status.*;
import static br.com.AllyWatch.server.Security.Cryptography.encrypt;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
                .seccional(request.getState())
                .status(UNDER_REVIEW)
                .keys(key)
                .build();

        lawyerRepository.save(lawyer);
    }

    public Lawyer findById(long id){
        return lawyerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Lawyer not found."));
    }

    public void verify(VerifySpecialistRequest request, long specialistId) {

        Lawyer lawyer = findById(specialistId);

        if (request.isAccepted()){
            lawyer.setStatus(APPROVED);
        } else {
            lawyer.setStatus(DISAPPROVED);
        }

        lawyerRepository.save(lawyer);
    }

    public List<SpecialistResponse> listByStatus(Status status) {
        return lawyerRepository.findAllByStatusLike(status)
                .stream().map(SpecialistMapper::toResponse).toList();
    }
}
