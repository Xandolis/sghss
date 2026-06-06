package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.*;
import com.vidaplus.sghss.repository.*;
import com.vidaplus.sghss.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private LeitoRepository leitoRepository;

    @Autowired
    private InternacaoRepository internacaoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    // ======== QUARTOS ========

    public QuartoResponse criarQuarto(QuartoRequest request) {
        Quarto quarto = new Quarto();
        quarto.setNumero(request.getNumero());
        quarto.setTipo(request.getTipo());
        quarto.setTotalLeitos(request.getTotalLeitos());
        quarto.setLeitosDisponiveis(request.getTotalLeitos());

        Quarto salvo = quartoRepository.save(quarto);
        return toQuartoResponse(salvo);
    }

    public QuartoResponse obterQuartoPorId(Long id) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
        return toQuartoResponse(quarto);
    }

    public List<QuartoResponse> listarQuartos() {
        return quartoRepository.findAll()
                .stream()
                .map(this::toQuartoResponse)
                .collect(Collectors.toList());
    }

    public QuartoResponse atualizarQuarto(Long id, QuartoRequest request) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));

        quarto.setNumero(request.getNumero());
        quarto.setTipo(request.getTipo());
        quarto.setTotalLeitos(request.getTotalLeitos());

        Quarto atualizado = quartoRepository.save(quarto);
        return toQuartoResponse(atualizado);
    }

    public void deletarQuarto(Long id) {
        quartoRepository.deleteById(id);
    }

    // ======== LEITOS ========

    public LeitoResponse criarLeito(LeitoRequest request) {
        Quarto quarto = quartoRepository.findById(request.getQuartoId())
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));

        Leito leito = new Leito();
        leito.setQuarto(quarto);
        leito.setNumero(request.getNumero());
        leito.setStatus("DISPONÍVEL");

        Leito salvo = leitoRepository.save(leito);
        return toLeitoResponse(salvo);
    }

    public List<LeitoResponse> listarLeitosPorQuarto(Long quartoId) {
        return leitoRepository.findByQuartoId(quartoId)
                .stream()
                .map(this::toLeitoResponse)
                .collect(Collectors.toList());
    }

    public List<LeitoResponse> listarLeitosDisponiveis() {
        return leitoRepository.findByStatus("DISPONÍVEL")
                .stream()
                .map(this::toLeitoResponse)
                .collect(Collectors.toList());
    }

    public void deletarLeito(Long id) {
        leitoRepository.deleteById(id);
    }

    // ======== INTERNAÇÕES ========

    public InternacaoResponse admitir(InternacaoRequest request) {
        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Leito leito = leitoRepository.findById(request.getLeitoId())
                .orElseThrow(() -> new RuntimeException("Leito não encontrado"));

        if (!"DISPONÍVEL".equals(leito.getStatus())) {
            throw new RuntimeException("Leito não disponível");
        }

        Profissional profissional = null;
        if (request.getProfissionalResponsavelId() != null) {
            profissional = profissionalRepository.findById(request.getProfissionalResponsavelId())
                    .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        }

        Internacao internacao = new Internacao();
        internacao.setPaciente(paciente);
        internacao.setLeito(leito);
        internacao.setProfissionalResponsavel(profissional);
        internacao.setDataAdmissao(LocalDateTime.now());
        internacao.setMotivo(request.getMotivo());
        internacao.setObservacoes(request.getObservacoes());

        // Atualizar status do leito
        leito.setStatus("OCUPADO");
        leitoRepository.save(leito);

        // Atualizar leitos disponíveis do quarto
        Quarto quarto = leito.getQuarto();
        quarto.setLeitosDisponiveis(quarto.getLeitosDisponiveis() - 1);
        quartoRepository.save(quarto);

        Internacao salva = internacaoRepository.save(internacao);
        return toInternacaoResponse(salva);
    }

    public InternacaoResponse alta(Long internacaoId) {
        Internacao internacao = internacaoRepository.findById(internacaoId)
                .orElseThrow(() -> new RuntimeException("Internação não encontrada"));

        if (internacao.getDataAlta() != null) {
            throw new RuntimeException("Paciente já teve alta nesta internação");
        }

        internacao.setDataAlta(LocalDateTime.now());

        // Liberar leito
        Leito leito = internacao.getLeito();
        leito.setStatus("DISPONÍVEL");
        leitoRepository.save(leito);

        // Atualizar leitos disponíveis do quarto
        Quarto quarto = leito.getQuarto();
        quarto.setLeitosDisponiveis(quarto.getLeitosDisponiveis() + 1);
        quartoRepository.save(quarto);

        Internacao atualizada = internacaoRepository.save(internacao);
        return toInternacaoResponse(atualizada);
    }

    public List<InternacaoResponse> listarInternacoesPorPaciente(Long pacienteId) {
        return internacaoRepository.findByPacienteId(pacienteId)
                .stream()
                .map(this::toInternacaoResponse)
                .collect(Collectors.toList());
    }

    // ======== MAPPERS ========

    private QuartoResponse toQuartoResponse(Quarto quarto) {
        QuartoResponse response = new QuartoResponse();
        response.setId(quarto.getId());
        response.setNumero(quarto.getNumero());
        response.setTipo(quarto.getTipo());
        response.setTotalLeitos(quarto.getTotalLeitos());
        response.setLeitosDisponiveis(quarto.getLeitosDisponiveis());
        return response;
    }

    private LeitoResponse toLeitoResponse(Leito leito) {
        LeitoResponse response = new LeitoResponse();
        response.setId(leito.getId());
        response.setQuartoId(leito.getQuarto().getId());
        response.setNumero(leito.getNumero());
        response.setStatus(leito.getStatus());
        return response;
    }

    private InternacaoResponse toInternacaoResponse(Internacao internacao) {
        InternacaoResponse response = new InternacaoResponse();
        response.setId(internacao.getId());
        response.setPacienteId(internacao.getPaciente().getId());
        response.setLeitoId(internacao.getLeito().getId());
        response.setProfissionalResponsavelId(internacao.getProfissionalResponsavel() != null ? internacao.getProfissionalResponsavel().getId() : null);
        response.setDataAdmissao(internacao.getDataAdmissao());
        response.setDataAlta(internacao.getDataAlta());
        response.setMotivo(internacao.getMotivo());
        response.setObservacoes(internacao.getObservacoes());
        return response;
    }
}
