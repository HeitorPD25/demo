package fiap.com.br.floodalert.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "SENSOR")
@Data
@AllArgsConstructor
@Builder
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensorId;

    @Column(length = 200)
    @Size(max = 200, message = "Descrição do sensor deve ter no máximo 200 caracteres")
    private String sensorDescription;

    @Column(length = 20)
    @Size(max = 20, message = "Status deve ter no máximo 20 caracteres")
    private String status;

    @Column(length = 20)
    @Size(max = 20, message = "Longitude deve ter no máximo 20 caracteres")
    private String longitude;

    @Column(length = 20)
    @Size(max = 20, message = "Latitude deve ter no máximo 20 caracteres")
    private String latitude;

    @Column(length = 5)
    @Size(max = 5, message = "Status do sensor deve ter no máximo 5 caracteres")
    private String sensorStatus;

    private Integer maxAlertCm;

    private LocalDate installationDate;


    
}
