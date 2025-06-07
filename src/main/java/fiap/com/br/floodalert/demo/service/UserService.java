package fiap.com.br.floodalert.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fiap.com.br.floodalert.demo.dto.request.UserRegistrationRequest;
import fiap.com.br.floodalert.demo.entity.User;
import fiap.com.br.floodalert.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private BCryptPasswordEncoder passwordEncoder;
    
    public User registerUser(UserRegistrationRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username já cadastrado");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .userType("USER")
                .phone(request.getPhone())
                .registrationDate(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


}
