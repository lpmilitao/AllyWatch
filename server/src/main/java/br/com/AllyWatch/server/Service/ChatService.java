package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Response.SolicitationResponse;
import br.com.AllyWatch.server.Domain.Solicitation;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Repository.SolicitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Status.UNDER_REVIEW;
import static br.com.AllyWatch.server.Security.Cryptography.decrypt;

@Service
public class ChatService {

    @Autowired
    private SolicitationRepository solicitationRepository;

    @Autowired
    private UserService userService;

    public List<SolicitationResponse> listSolicitations(String authorization) {
        User user = userService.getAuthenticatedUser(authorization);

        List<Solicitation> userSolicitations = solicitationRepository
                .findAllByUser_IdAndStatusEquals(user.getId(), UNDER_REVIEW);

        List<Solicitation> allSolicitations = solicitationRepository
                .findAllByStatusLike(UNDER_REVIEW);

        return userSolicitations.stream().map(
                solicitation -> {
                    Solicitation otherSolicitation = allSolicitations.stream().filter(s ->
                                    s.getChat().getId() == solicitation.getChat().getId()
                                            && s.getUser().getId() != user.getId())
                            .findFirst().get();

                    return SolicitationResponse.builder()
                            .id(solicitation.getId())
                            .status(solicitation.getStatus())
                            .requestedUser(
                                    decrypt(user.getFullname(), user.getKeys().getPrivateKey())
                            )
                            .requestingUser(
                                    decrypt(otherSolicitation.getUser().getFullname(),
                                            otherSolicitation.getUser().getKeys().getPrivateKey())
                            )
                            .build();
                }
        ).toList();
    }
}
