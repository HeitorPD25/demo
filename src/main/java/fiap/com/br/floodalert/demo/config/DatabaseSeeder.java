package fiap.com.br.floodalert.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fiap.com.br.floodalert.demo.entity.Alert;
import fiap.com.br.floodalert.demo.entity.User;
import fiap.com.br.floodalert.demo.repository.AlertRepository;
import fiap.com.br.floodalert.demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;

    public DatabaseSeeder(UserRepository userRepository, AlertRepository alertRepository) {
        this.userRepository = userRepository;
        this.alertRepository = alertRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            // Cria dois usuários
            User user1 = new User();
            user1.setUsername("heitor");
            user1.setEmail("heitor@example.com");
            user1.setPasswordHash("hashedPassword1"); // senha hash
            user1.setUserType("USER");
            user1.setPhone("11999999999");
            user1.setRegistrationDate(LocalDateTime.now());

            User user2 = new User();
            user2.setUsername("brenda");
            user2.setEmail("brenda@example.com");
            user2.setPasswordHash("hashedPassword2"); // senha hash
            user2.setUserType("USER");
            user2.setPhone("11988888888");
            user2.setRegistrationDate(LocalDateTime.now());

            userRepository.saveAll(List.of(user1, user2));
            System.out.println("Usuários Cadatrados");

            List<Alert> alerts = new ArrayList<>();

            for (int i = 1; i <= 15; i++) {
                Alert alert = new Alert();
                alert.setUser(user1);
                alert.setWaterLevelCm(50 + i * 5);
                alert.setMessage("Alerta do Heitor número " + i);
                alert.setDateTime(LocalDateTime.now().minusDays(15 - i));
                alert.setAlertStatus(i % 2 == 0 ? "ACTIVE" : "INACTIVE");
                alerts.add(alert);
            }

            for (int i = 1; i <= 15; i++) {
                Alert alert = new Alert();
                alert.setUser(user2);
                alert.setWaterLevelCm(40 + i * 4);
                alert.setMessage("Alerta da Brenda número " + i);
                alert.setDateTime(LocalDateTime.now().minusDays(15 - i));
                alert.setAlertStatus(i % 2 == 0 ? "ACTIVE" : "INACTIVE");
                alerts.add(alert);
            }

            alertRepository.saveAll(alerts);
            System.out.println("Alerts Cadastrados");
        }
    }
}