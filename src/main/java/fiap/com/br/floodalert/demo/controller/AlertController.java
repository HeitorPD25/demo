package fiap.com.br.floodalert.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fiap.com.br.floodalert.demo.dto.response.AlertResponse;
import fiap.com.br.floodalert.demo.entity.Alert;
import fiap.com.br.floodalert.demo.entity.User;
import fiap.com.br.floodalert.demo.repository.AlertRepository;
import fiap.com.br.floodalert.demo.service.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Alertas", description = "Gerenciamento de alertas de alagamento")
@RequiredArgsConstructor
public class AlertController {

    @Autowired
    private AlertRepository alertRepository;
    
    @Autowired
    private AlertService alertService;

    @GetMapping
    @Operation(summary = "Listar alertas", description = "Lista todos os alertas com paginação.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Page<AlertResponse>> listarAlertsPaginados(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateTime").descending());
        Page<Alert> alertPage = alertRepository.findAll(pageable);

        Page<AlertResponse> dtoPage = alertPage.map(alert ->
            new AlertResponse(
                alert.getAlertId(),
                alert.getWaterLevelCm(),
                alert.getMessage(),
                alert.getDateTime(),
                alert.getAlertStatus()
            )
        );
        return ResponseEntity.ok(dtoPage);
    }

    @PostMapping
    @Operation(summary = "Criar alerta", description = "Cadastra um novo alerta relacionado ao usuário autenticado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Alerta criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Alert> createAlert(@RequestBody @Valid Alert alert, @AuthenticationPrincipal User user) {
        alert.setDateTime(LocalDateTime.now());
        alert.setUser(user); // associa o usuário autenticado ao alerta
        Alert savedAlert = alertService.createAlert(alert);
        return ResponseEntity.ok(savedAlert);
    }
}
