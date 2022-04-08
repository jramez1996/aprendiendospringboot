package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.dev.model.Coche;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface CocheRepository extends JpaRepository<Coche, Long>{

    @Query(value = "{call lista_procedure()}", nativeQuery = true)
    List<Coche> listaProcedure();
}
