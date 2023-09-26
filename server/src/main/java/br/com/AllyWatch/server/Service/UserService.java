package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Mapper.UserMapper;
import br.com.AllyWatch.server.DTO.Request.AddUserRequest;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Repository.UserRepository;
import br.com.AllyWatch.server.Validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private PasswordEncoder passwordEncoder;

    @Transactional
    public void add(AddUserRequest request) {

        PasswordValidator.validate(request.getPassword());

        User newUser = UserMapper.toEntity(request);
//        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
//        newUser.setCpf(passwordEncoder.encode(request.getCpf()));
        newUser.setPassword(request.getPassword());
        newUser.setCpf(request.getCpf());

        userRepository.save(newUser);
    }
}