package fiap.com.br.floodalert.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiap.com.br.floodalert.demo.entity.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    
}
