package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Optional<Profissional> findByEmail(String email);
    Optional<Profissional> findByCrm(String crm);
    List<Profissional> findByEspecialidade(String especialidade);
}
