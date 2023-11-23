package ibmec.ap1.ap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ibmec.ap1.ap1.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    int countByNomeMarca(String nomeMarca);
    
}
