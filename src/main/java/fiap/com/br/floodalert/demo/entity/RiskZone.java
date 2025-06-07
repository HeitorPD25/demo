package fiap.com.br.floodalert.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RISK_ZONE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riskZoneId;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Nome da zona é obrigatório")
    @Size(max = 50, message = "Nome da zona deve ter no máximo 50 caracteres")
    private String zoneName;

    @Column(length = 200)
    @Size(max = 200, message = "Descrição da zona deve ter no máximo 200 caracteres")
    private String zoneDescription;

    private Integer defaultCriticLevelCm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

}
