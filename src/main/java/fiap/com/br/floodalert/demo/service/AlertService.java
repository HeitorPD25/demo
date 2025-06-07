package fiap.com.br.floodalert.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fiap.com.br.floodalert.demo.entity.Alert;
import fiap.com.br.floodalert.demo.entity.User;
import fiap.com.br.floodalert.demo.repository.AlertRepository;
import fiap.com.br.floodalert.demo.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AlertService {
    
    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Alert createAlert(Alert alert) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        alert.setUser(user);

        return alertRepository.save(alert);
    }
}
