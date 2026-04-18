package com.example.bioskopserver.repository;

import com.example.bioskopserver.model.Screening;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    
    List<Screening> findByCustomer_CustomerID(Long adminId);
    
}
