package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.model.GradeTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GradeTrabalhoRepository extends JpaRepository<GradeTrabalho, Long> {
    List<GradeTrabalho> findByProfissionalId(Long profissionalId);
    List<GradeTrabalho> findByProfissionalIdAndDiaSemana(Long profissionalId, String diaSemana);
}
