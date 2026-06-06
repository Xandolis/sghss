package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.model.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
    List<Bloqueio> findByProfissionalId(Long profissionalId);
    List<Bloqueio> findByProfissionalIdAndDataHoraInicioBetween(Long profissionalId, LocalDateTime inicio, LocalDateTime fim);
}
