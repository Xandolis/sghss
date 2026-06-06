package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.PacienteRequest;
import com.vidaplus.sghss.dto.PacienteResponse;
import com.vidaplus.sghss.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteResponse> criar(@RequestBody PacienteRequest request) {
        return ResponseEntity.ok(pacienteService.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obterPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(@PathVariable Long id, @RequestBody PacienteRequest request) {
        return ResponseEntity.ok(pacienteService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
        return ResponseEntity.ok("Paciente deletado com sucesso");
    }
}
