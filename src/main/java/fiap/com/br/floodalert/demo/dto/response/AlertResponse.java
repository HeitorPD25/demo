package fiap.com.br.floodalert.demo.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlertResponse {
    
    private Long id;
    private Integer waterLevelCm;
    private String message;
    private LocalDateTime dateTime;
    private String alertStatus;


}
