package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Mapper.SpecialistMapper;
import br.com.AllyWatch.server.DTO.Request.VerifySpecialistRequest;
import br.com.AllyWatch.server.DTO.Response.SpecialistResponse;
import br.com.AllyWatch.server.Domain.Enum.Status;
import br.com.AllyWatch.server.Domain.KeyCrypt;
import br.com.AllyWatch.server.Domain.Psychologist;
import br.com.AllyWatch.server.Repository.PsychologistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Status.*;
import static br.com.AllyWatch.server.Security.Cryptography.encrypt;
import static br.com.AllyWatch.server.Validator.RegisterNumberValidator.psychologistRegisterNumberValidator;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PsychologistService {

    @Autowired
    private PsychologistRepository psychologistRepository;

    @Autowired
    private KeyService keyService;

    @Transactional
    public void add(Psychologist request) {

        psychologistRegisterNumberValidator(request.getRegisterNumber());

        KeyCrypt key = keyService.findKey();

        Psychologist psychologist = Psychologist.builder()
                .fullname(
                        encrypt(request.getFullname(), key.getPublicKey())
                )
                .email(
                        encrypt(request.getEmail(), key.getPublicKey())
                )
                .cpfOrCpnj(
                        encrypt(request.getCpfOrCpnj(), key.getPublicKey())
                )
                .registerNumber(
                        encrypt(request.getRegisterNumber(), key.getPublicKey())
                )
                .city(
                        encrypt(request.getCity(), key.getPublicKey())
                )
                .phone(
                        encrypt(request.getPhone(), key.getPublicKey())
                )
                .type(request.getType())
                .state(request.getState())
                .status(UNDER_REVIEW)
                .keys(key)
                .build();

        psychologistRepository.save(psychologist);
    }

    public Psychologist findById(long id){
        return psychologistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Psychologist not found."));
    }

    public void verify(VerifySpecialistRequest request, long specialistId) {

        Psychologist psychologist = findById(specialistId);

        if (request.isAccepted()){
            psychologist.setStatus(APPROVED);
        } else {
            psychologist.setStatus(DISAPPROVED);
        }

        psychologistRepository.save(psychologist);
    }

    public List<SpecialistResponse> listByStatus(Status status) {
        return psychologistRepository.findAllByStatusLike(status)
                .stream().map(SpecialistMapper::toResponse)
                .toList();
    }
}
