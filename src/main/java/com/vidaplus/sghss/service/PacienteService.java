package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.repository.PacienteRepository;
import com.vidaplus.sghss.dto.PacienteRequest;
import com.vidaplus.sghss.dto.PacienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteResponse criar(PacienteRequest request) {
        Paciente paciente = new Paciente();
        paciente.setNome(request.getNome());
        paciente.setEmail(request.getEmail());
        paciente.setTelefone(request.getTelefone());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setEndereco(request.getEndereco());
        paciente.setNumeroUtente(request.getNumeroUtente());

        Paciente salvo = pacienteRepository.save(paciente);
        return toResponse(salvo);
    }

    public PacienteResponse obterPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        return toResponse(paciente);
    }

    public List<PacienteResponse> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PacienteResponse atualizar(Long id, PacienteRequest request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setNome(request.getNome());
        paciente.setEmail(request.getEmail());
        paciente.setTelefone(request.getTelefone());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setEndereco(request.getEndereco());
        paciente.setNumeroUtente(request.getNumeroUtente());

        Paciente atualizado = pacienteRepository.save(paciente);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado");
        }
        pacienteRepository.deleteById(id);
    }

    private PacienteResponse toResponse(Paciente paciente) {
        PacienteResponse response = new PacienteResponse();
        response.setId(paciente.getId());
        response.setNome(paciente.getNome());
        response.setEmail(paciente.getEmail());
        response.setTelefone(paciente.getTelefone());
        response.setDataNascimento(paciente.getDataNascimento());
        response.setEndereco(paciente.getEndereco());
        response.setNumeroUtente(paciente.getNumeroUtente());
        return response;
    }
}
