package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.Consulta;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.model.Profissional;
import com.vidaplus.sghss.repository.ConsultaRepository;
import com.vidaplus.sghss.repository.PacienteRepository;
import com.vidaplus.sghss.repository.ProfissionalRepository;
import com.vidaplus.sghss.dto.ConsultaRequest;
import com.vidaplus.sghss.dto.ConsultaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public ConsultaResponse agendar(ConsultaRequest request) {
        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Profissional profissional = profissionalRepository.findById(request.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setProfissional(profissional);
        consulta.setDataHoraInicio(request.getDataHoraInicio());
        consulta.setDataHoraFim(request.getDataHoraFim());
        consulta.setStatus("AGENDADA");
        consulta.setObservacoes(request.getObservacoes());

        Consulta salva = consultaRepository.save(consulta);
        return toResponse(salva);
    }

    public ConsultaResponse obterPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        return toResponse(consulta);
    }

    public List<ConsultaResponse> listarPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<ConsultaResponse> listarPorProfissional(Long profissionalId) {
        return consultaRepository.findByProfissionalId(profissionalId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ConsultaResponse cancelar(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        if ("CANCELADA".equals(consulta.getStatus()) || "REALIZADA".equals(consulta.getStatus())) {
            throw new RuntimeException("Não é possível cancelar uma consulta " + consulta.getStatus().toLowerCase());
        }

        consulta.setStatus("CANCELADA");
        Consulta atualizada = consultaRepository.save(consulta);
        return toResponse(atualizada);
    }

    private ConsultaResponse toResponse(Consulta consulta) {
        ConsultaResponse response = new ConsultaResponse();
        response.setId(consulta.getId());
        response.setPacienteId(consulta.getPaciente().getId());
        response.setProfissionalId(consulta.getProfissional().getId());
        response.setDataHoraInicio(consulta.getDataHoraInicio());
        response.setDataHoraFim(consulta.getDataHoraFim());
        response.setStatus(consulta.getStatus());
        response.setObservacoes(consulta.getObservacoes());
        return response;
    }
}
