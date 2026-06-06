package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.model.Leito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeitoRepository extends JpaRepository<Leito, Long> {
    List<Leito> findByQuartoId(Long quartoId);
    Optional<Leito> findByNumeroAndQuartoId(String numero, Long quartoId);
    List<Leito> findByStatus(String status);
}
