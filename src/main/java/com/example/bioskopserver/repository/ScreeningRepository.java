package com.example.bioskopserver.repository;

import com.example.bioskopserver.model.Screening;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    
    List<Screening> findByCustomer_CustomerID(Long adminId);
    
    List<Screening> findByMovie_IdAndHall_Id(Long movieId, Long hallId);
    
    List<Screening> findByMovie_IdAndHall_IdAndCustomer_CustomerID(
        Long movieId,
        Long hallId,
        Long adminId
    );
}
