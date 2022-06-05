package paymentgateway.usermanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paymentgateway.usermanager.model.Marchand;

import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface MarchandRepo extends JpaRepository<Marchand,Long> {
    void deleteMarchandById(Long id);
    Optional<Marchand> findMarchandById(Long id);
}
