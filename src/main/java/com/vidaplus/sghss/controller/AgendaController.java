package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.GradeTrabalhoRequest;
import com.vidaplus.sghss.dto.GradeTrabalhoResponse;
import com.vidaplus.sghss.dto.BloqueioRequest;
import com.vidaplus.sghss.dto.BloqueioResponse;
import com.vidaplus.sghss.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    // ======== GRADE DE TRABALHO ========

    @PostMapping("/grade")
    public ResponseEntity<GradeTrabalhoResponse> criarGrade(@RequestBody GradeTrabalhoRequest request) {
        return ResponseEntity.ok(agendaService.criarGrade(request));
    }

    @GetMapping("/grade/profissional/{profissionalId}")
    public ResponseEntity<List<GradeTrabalhoResponse>> listarGradePorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(agendaService.listarGradePorProfissional(profissionalId));
    }

    @DeleteMapping("/grade/{gradeId}")
    public ResponseEntity<String> deletarGrade(@PathVariable Long gradeId) {
        agendaService.deletarGrade(gradeId);
        return ResponseEntity.ok("Grade deletada com sucesso");
    }

    // ======== BLOQUEIOS ========

    @PostMapping("/bloqueio")
    public ResponseEntity<BloqueioResponse> criarBloqueio(@RequestBody BloqueioRequest request) {
        return ResponseEntity.ok(agendaService.criarBloqueio(request));
    }

    @GetMapping("/bloqueio/profissional/{profissionalId}")
    public ResponseEntity<List<BloqueioResponse>> listarBloqueiosPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(agendaService.listarBloqueiosPorProfissional(profissionalId));
    }

    @DeleteMapping("/bloqueio/{bloqueioId}")
    public ResponseEntity<String> deletarBloqueio(@PathVariable Long bloqueioId) {
        agendaService.deletarBloqueio(bloqueioId);
        return ResponseEntity.ok("Bloqueio deletado com sucesso");
    }

    // ======== DISPONIBILIDADE ========

    @GetMapping("/disponibilidade/{profissionalId}/{data}")
    public ResponseEntity<List<LocalDateTime>> calcularDisponibilidade(
            @PathVariable Long profissionalId,
            @PathVariable String data) {
        LocalDate localDate = LocalDate.parse(data);
        List<LocalDateTime> horarios = agendaService.calcularDisponibilidade(profissionalId, localDate);
        return ResponseEntity.ok(horarios);
    }
}
