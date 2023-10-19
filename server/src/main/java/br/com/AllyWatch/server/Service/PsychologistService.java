package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.Domain.KeyCrypt;
import br.com.AllyWatch.server.Domain.Psychologist;
import br.com.AllyWatch.server.Repository.PsychologistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.AllyWatch.server.Domain.Enum.Status.UNDER_REVIEW;
import static br.com.AllyWatch.server.Security.Cryptography.encrypt;
import static br.com.AllyWatch.server.Validator.RegisterNumberValidator.psychologistRegisterNumberValidator;

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
                .type(request.getType())
                .state(request.getState())
                .status(UNDER_REVIEW)
                .build();

        psychologistRepository.save(psychologist);
    }
}
