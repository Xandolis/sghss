package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.*;
import com.vidaplus.sghss.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // ======== QUARTOS ========

    @PostMapping("/quartos")
    public ResponseEntity<QuartoResponse> criarQuarto(@RequestBody QuartoRequest request) {
        return ResponseEntity.ok(hospitalService.criarQuarto(request));
    }

    @GetMapping("/quartos/{id}")
    public ResponseEntity<QuartoResponse> obterQuarto(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.obterQuartoPorId(id));
    }

    @GetMapping("/quartos")
    public ResponseEntity<List<QuartoResponse>> listarQuartos() {
        return ResponseEntity.ok(hospitalService.listarQuartos());
    }

    @PutMapping("/quartos/{id}")
    public ResponseEntity<QuartoResponse> atualizarQuarto(@PathVariable Long id, @RequestBody QuartoRequest request) {
        return ResponseEntity.ok(hospitalService.atualizarQuarto(id, request));
    }

    @DeleteMapping("/quartos/{id}")
    public ResponseEntity<String> deletarQuarto(@PathVariable Long id) {
        hospitalService.deletarQuarto(id);
        return ResponseEntity.ok("Quarto deletado com sucesso");
    }

    // ======== LEITOS ========

    @PostMapping("/leitos")
    public ResponseEntity<LeitoResponse> criarLeito(@RequestBody LeitoRequest request) {
        return ResponseEntity.ok(hospitalService.criarLeito(request));
    }

    @GetMapping("/leitos/quarto/{quartoId}")
    public ResponseEntity<List<LeitoResponse>> listarLeitosPorQuarto(@PathVariable Long quartoId) {
        return ResponseEntity.ok(hospitalService.listarLeitosPorQuarto(quartoId));
    }

    @GetMapping("/leitos/disponiveis")
    public ResponseEntity<List<LeitoResponse>> listarLeitosDisponiveis() {
        return ResponseEntity.ok(hospitalService.listarLeitosDisponiveis());
    }

    @DeleteMapping("/leitos/{id}")
    public ResponseEntity<String> deletarLeito(@PathVariable Long id) {
        hospitalService.deletarLeito(id);
        return ResponseEntity.ok("Leito deletado com sucesso");
    }

    // ======== INTERNAÇÕES ========

    @PostMapping("/internacoes/admitir")
    public ResponseEntity<InternacaoResponse> admitir(@RequestBody InternacaoRequest request) {
        return ResponseEntity.ok(hospitalService.admitir(request));
    }

    @PutMapping("/internacoes/{id}/alta")
    public ResponseEntity<InternacaoResponse> alta(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.alta(id));
    }

    @GetMapping("/internacoes/paciente/{pacienteId}")
    public ResponseEntity<List<InternacaoResponse>> listarInternacoesPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(hospitalService.listarInternacoesPorPaciente(pacienteId));
    }
}
