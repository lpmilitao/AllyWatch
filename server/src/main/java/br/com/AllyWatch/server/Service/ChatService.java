package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Request.MessageRequest;
import br.com.AllyWatch.server.DTO.Response.ChatDetailedResponse;
import br.com.AllyWatch.server.DTO.Response.ChatResponse;
import br.com.AllyWatch.server.DTO.Response.SolicitationResponse;
import br.com.AllyWatch.server.Domain.Chat;
import br.com.AllyWatch.server.Domain.Message;
import br.com.AllyWatch.server.Domain.Solicitation;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Repository.ChatRepository;
import br.com.AllyWatch.server.Repository.MessageRepository;
import br.com.AllyWatch.server.Repository.SolicitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static br.com.AllyWatch.server.DTO.Mapper.ChatMapper.toDetailedResponse;
import static br.com.AllyWatch.server.DTO.Mapper.ChatMapper.toResponse;
import static br.com.AllyWatch.server.DTO.Mapper.MessageMapper.toEntity;
import static br.com.AllyWatch.server.Domain.Enum.Status.*;
import static br.com.AllyWatch.server.Security.Cryptography.decrypt;
import static org.springframework.http.HttpStatus.*;

@Service
public class ChatService {

    @Autowired
    private SolicitationRepository solicitationRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    public List<SolicitationResponse> listSolicitations(String authorization) {
        User user = userService.getAuthenticatedUser(authorization);

        List<Solicitation> userSolicitations = solicitationRepository
                .findAllByUser_IdAndStatusEquals(user.getId(), UNDER_REVIEW);

        List<Solicitation> allSolicitations = solicitationRepository.findAll();

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

    public void verifySolicitation(String authorization, long solicitationId, boolean accepted) {
        User user = userService.getAuthenticatedUser(authorization);

        Solicitation solicitation = solicitationRepository.findById(solicitationId).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Solicitation not found.")
        );

        if (solicitation.getUser().getId() != user.getId()) {
            throw new ResponseStatusException(FORBIDDEN,
                    "You can not accept a solicitation that is not requested for you.");
        }

        if (accepted) {
            solicitation.setStatus(APPROVED);
        } else {
            solicitation.setStatus(DISAPPROVED);
        }

        solicitationRepository.save(solicitation);
        createChat(solicitation.getChat().getId());
    }

    public void createChat(long chatId) {
        List<Solicitation> solicitations = solicitationRepository.findAllByChat_Id(chatId);

        if (solicitations.size() != 2) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR,
                    "It should not exists more than 2 solicitations for the same chat.");
        }

        boolean allAccepted = solicitations.stream().allMatch(solicitation ->
                solicitation.getStatus() == APPROVED);

        if (!allAccepted) {
            return;
        }

        Chat chat = solicitations.stream().findFirst().get().getChat();
        chat.setOpen(true);

        chatRepository.save(chat);
        solicitationRepository.deleteAll(solicitations);
    }

    public List<ChatResponse> listChats(String authorization) {
        User user = userService.getAuthenticatedUser(authorization);

        List<Chat> chats = chatRepository.findAllByOpenLike(true);

        return chats.stream()
                .filter(chat -> chat.getUsers().contains(user))
                .map(chat -> {
                            User ally = chat.getUsers().stream()
                                    .filter(u -> u.getId() != user.getId())
                                    .findFirst().get();

                            return toResponse(chat, ally);
                        }
                )
                .toList();
    }

    public ChatDetailedResponse detailChat(String authorization, long chatId){
        User user = userService.getAuthenticatedUser(authorization);

        Chat chat = chatRepository.findById(chatId).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "Chat not found.")
        );

        if (!chat.getUsers().contains(user)){
            throw new ResponseStatusException(FORBIDDEN, "You can not access this chat.");
        }

        User ally = chat.getUsers().stream()
                .filter(u -> u.getId() != user.getId())
                .findFirst().get();

        return toDetailedResponse(chat, user, ally);
    }

    public void sendMessage(String authorization, long chatId, MessageRequest request) {
        User user = userService.getAuthenticatedUser(authorization);

        Chat chat = chatRepository.findById(chatId).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Chat not found.")
        );

        if (!chat.getUsers().contains(user)){
            throw new ResponseStatusException(FORBIDDEN, "You can not access this chat.");
        }

        Message newMessage = toEntity(request);
        user.addMessage(newMessage);
        chat.addMessage(newMessage);

        messageRepository.save(newMessage);
    }
}
