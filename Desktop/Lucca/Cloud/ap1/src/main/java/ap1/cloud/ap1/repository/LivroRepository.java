package ap1.cloud.ap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ap1.cloud.ap1.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
}