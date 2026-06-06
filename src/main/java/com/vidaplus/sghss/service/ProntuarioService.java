package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.Prontuario;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.model.Profissional;
import com.vidaplus.sghss.model.Consulta;
import com.vidaplus.sghss.repository.*;
import com.vidaplus.sghss.dto.ProntuarioRequest;
import com.vidaplus.sghss.dto.ProntuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public ProntuarioResponse criar(ProntuarioRequest request) {
        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Profissional profissional = profissionalRepository.findById(request.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Consulta consulta = null;
        if (request.getConsultaId() != null) {
            consulta = consultaRepository.findById(request.getConsultaId())
                    .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        }

        Prontuario prontuario = new Prontuario();
        prontuario.setPaciente(paciente);
        prontuario.setProfissional(profissional);
        prontuario.setConsulta(consulta);
        prontuario.setDataCriacao(LocalDateTime.now());
        prontuario.setDiagnostico(request.getDiagnostico());
        prontuario.setTratamento(request.getTratamento());
        prontuario.setObservacoes(request.getObservacoes());
        prontuario.setAnexos(request.getAnexos());

        Prontuario salvo = prontuarioRepository.save(prontuario);
        return toResponse(salvo);
    }

    public ProntuarioResponse obterPorId(Long id) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
        return toResponse(prontuario);
    }

    public List<ProntuarioResponse> listarPorPaciente(Long pacienteId) {
        return prontuarioRepository.findByPacienteId(pacienteId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProntuarioResponse> listarPorProfissional(Long profissionalId) {
        return prontuarioRepository.findByProfissionalId(profissionalId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProntuarioResponse> listarPorConsulta(Long consultaId) {
        return prontuarioRepository.findByConsultaId(consultaId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProntuarioResponse atualizar(Long id, ProntuarioRequest request) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));

        prontuario.setDiagnostico(request.getDiagnostico());
        prontuario.setTratamento(request.getTratamento());
        prontuario.setObservacoes(request.getObservacoes());
        prontuario.setAnexos(request.getAnexos());

        Prontuario atualizado = prontuarioRepository.save(prontuario);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        if (!prontuarioRepository.existsById(id)) {
            throw new RuntimeException("Prontuário não encontrado");
        }
        prontuarioRepository.deleteById(id);
    }

    private ProntuarioResponse toResponse(Prontuario prontuario) {
        ProntuarioResponse response = new ProntuarioResponse();
        response.setId(prontuario.getId());
        response.setPacienteId(prontuario.getPaciente().getId());
        response.setProfissionalId(prontuario.getProfissional().getId());
        response.setConsultaId(prontuario.getConsulta() != null ? prontuario.getConsulta().getId() : null);
        response.setDataCriacao(prontuario.getDataCriacao());
        response.setDiagnostico(prontuario.getDiagnostico());
        response.setTratamento(prontuario.getTratamento());
        response.setObservacoes(prontuario.getObservacoes());
        response.setAnexos(prontuario.getAnexos());
        return response;
    }
}
