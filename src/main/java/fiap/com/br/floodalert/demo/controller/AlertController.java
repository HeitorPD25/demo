package fiap.com.br.floodalert.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiap.com.br.floodalert.demo.entity.Alert;
import fiap.com.br.floodalert.demo.entity.User;
import fiap.com.br.floodalert.demo.service.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {
    
    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody @Valid Alert alert, @AuthenticationPrincipal User user) {
    alert.setUser(user); // associa o usuário autenticado ao alerta
    Alert savedAlert = alertService.createAlert(alert);
    return ResponseEntity.ok(savedAlert);
}




}
