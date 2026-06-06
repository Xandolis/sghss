package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.ConsultaRequest;
import com.vidaplus.sghss.dto.ConsultaResponse;
import com.vidaplus.sghss.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaResponse> agendar(@RequestBody ConsultaRequest request) {
        return ResponseEntity.ok(consultaService.agendar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponse> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.obterPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaResponse>> listarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaService.listarPorPaciente(pacienteId));
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<ConsultaResponse>> listarPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(consultaService.listarPorProfissional(profissionalId));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaResponse> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.cancelar(id));
    }
}
