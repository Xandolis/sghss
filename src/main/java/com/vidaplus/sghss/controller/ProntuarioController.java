package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.ProntuarioRequest;
import com.vidaplus.sghss.dto.ProntuarioResponse;
import com.vidaplus.sghss.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @PostMapping
    public ResponseEntity<ProntuarioResponse> criar(@RequestBody ProntuarioRequest request) {
        return ResponseEntity.ok(prontuarioService.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioResponse> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prontuarioService.obterPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ProntuarioResponse>> listarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(prontuarioService.listarPorPaciente(pacienteId));
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<ProntuarioResponse>> listarPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(prontuarioService.listarPorProfissional(profissionalId));
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<ProntuarioResponse>> listarPorConsulta(@PathVariable Long consultaId) {
        return ResponseEntity.ok(prontuarioService.listarPorConsulta(consultaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioResponse> atualizar(@PathVariable Long id, @RequestBody ProntuarioRequest request) {
        return ResponseEntity.ok(prontuarioService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        prontuarioService.deletar(id);
        return ResponseEntity.ok("Prontuário deletado com sucesso");
    }
}
