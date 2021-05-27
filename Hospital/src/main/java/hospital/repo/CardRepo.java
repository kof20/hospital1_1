package hospital.repo;

import hospital.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card, Long> {
    Card findCardById(Long id);
}
