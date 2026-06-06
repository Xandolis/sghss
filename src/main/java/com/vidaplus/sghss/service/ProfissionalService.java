package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.Profissional;
import com.vidaplus.sghss.repository.ProfissionalRepository;
import com.vidaplus.sghss.dto.ProfissionalRequest;
import com.vidaplus.sghss.dto.ProfissionalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public ProfissionalResponse criar(ProfissionalRequest request) {
        Profissional profissional = new Profissional();
        profissional.setNome(request.getNome());
        profissional.setEmail(request.getEmail());
        profissional.setTelefone(request.getTelefone());
        profissional.setEspecialidade(request.getEspecialidade());
        profissional.setCrm(request.getCrm());

        Profissional salvo = profissionalRepository.save(profissional);
        return toResponse(salvo);
    }

    public ProfissionalResponse obterPorId(Long id) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        return toResponse(profissional);
    }

    public List<ProfissionalResponse> listarTodos() {
        return profissionalRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProfissionalResponse> listarPorEspecialidade(String especialidade) {
        return profissionalRepository.findByEspecialidade(especialidade)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProfissionalResponse atualizar(Long id, ProfissionalRequest request) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        profissional.setNome(request.getNome());
        profissional.setEmail(request.getEmail());
        profissional.setTelefone(request.getTelefone());
        profissional.setEspecialidade(request.getEspecialidade());
        profissional.setCrm(request.getCrm());

        Profissional atualizado = profissionalRepository.save(profissional);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        if (!profissionalRepository.existsById(id)) {
            throw new RuntimeException("Profissional não encontrado");
        }
        profissionalRepository.deleteById(id);
    }

    private ProfissionalResponse toResponse(Profissional profissional) {
        ProfissionalResponse response = new ProfissionalResponse();
        response.setId(profissional.getId());
        response.setNome(profissional.getNome());
        response.setEmail(profissional.getEmail());
        response.setTelefone(profissional.getTelefone());
        response.setEspecialidade(profissional.getEspecialidade());
        response.setCrm(profissional.getCrm());
        return response;
    }
}
