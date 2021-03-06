package lv.autoservice.businesslogic.database;

import lv.autoservice.businesslogic.builder.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    Optional<Service> findByEmail(String email);
    Optional<Service> findByDateTime(String dateTime);
    List<Service> findAllByEmail(String email);
}



