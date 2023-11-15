package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Mapper.SpecialistMapper;
import br.com.AllyWatch.server.DTO.Request.PsychologistRequest;
import br.com.AllyWatch.server.DTO.Request.VerifySpecialistRequest;
import br.com.AllyWatch.server.DTO.Response.SpecialistResponse;
import br.com.AllyWatch.server.Domain.Enum.Status;
import br.com.AllyWatch.server.Domain.KeyCrypt;
import br.com.AllyWatch.server.Domain.Psychologist;
import br.com.AllyWatch.server.Repository.PsychologistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.com.AllyWatch.server.DTO.Mapper.SpecialistMapper.toEntity;
import static br.com.AllyWatch.server.Domain.Enum.Status.APPROVED;
import static br.com.AllyWatch.server.Domain.Enum.Status.DISAPPROVED;
import static br.com.AllyWatch.server.Validator.RegisterNumberValidator.psychologistRegisterNumberValidator;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PsychologistService {

    @Autowired
    private PsychologistRepository psychologistRepository;

    @Autowired
    private KeyService keyService;

    @Transactional
    public void add(PsychologistRequest request) {

        psychologistRegisterNumberValidator(request.getRegisterNumber());

        KeyCrypt key = keyService.findKey();

        Psychologist psychologist = toEntity(request, key);

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

    public Page<SpecialistResponse> listByStatus(Status status, Pageable pageable) {
        return psychologistRepository.findAllByStatusLike(status, pageable)
                .map(SpecialistMapper::toResponse);
    }
}
