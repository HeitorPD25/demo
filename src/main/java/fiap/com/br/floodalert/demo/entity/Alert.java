package fiap.com.br.floodalert.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ALERT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @NotNull(message = "Nível da água é obrigatório")
    private Integer waterLevelCm;

    @Column(length = 100)
    @Size(max = 100, message = "Mensagem deve ter no máximo 100 caracteres")
    private String message;

    @NotNull(message = "Data e hora do alerta são obrigatórias")
    private LocalDateTime dateTime;

    @Column(length = 20)
    @Size(max = 20, message = "Status do alerta deve ter no máximo 20 caracteres")
    private String alertStatus;

    // // Relacionamento com sensor
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "sensor_id", nullable = false)
    // @NotNull(message = "Sensor é obrigatório")
    // private Sensor sensor;

    // Relacionamento com Usuário
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
