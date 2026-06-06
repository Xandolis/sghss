package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.ProfissionalRequest;
import com.vidaplus.sghss.dto.ProfissionalResponse;
import com.vidaplus.sghss.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @PostMapping
    public ResponseEntity<ProfissionalResponse> criar(@RequestBody ProfissionalRequest request) {
        return ResponseEntity.ok(profissionalService.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalResponse> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalService.obterPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalResponse>> listarTodos() {
        return ResponseEntity.ok(profissionalService.listarTodos());
    }

    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<ProfissionalResponse>> listarPorEspecialidade(@PathVariable String especialidade) {
        return ResponseEntity.ok(profissionalService.listarPorEspecialidade(especialidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalResponse> atualizar(@PathVariable Long id, @RequestBody ProfissionalRequest request) {
        return ResponseEntity.ok(profissionalService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        profissionalService.deletar(id);
        return ResponseEntity.ok("Profissional deletado com sucesso");
    }
}
