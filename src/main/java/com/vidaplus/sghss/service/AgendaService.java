package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.GradeTrabalho;
import com.vidaplus.sghss.model.Bloqueio;
import com.vidaplus.sghss.model.Consulta;
import com.vidaplus.sghss.model.Profissional;
import com.vidaplus.sghss.repository.GradeTrabalhoRepository;
import com.vidaplus.sghss.repository.BloqueioRepository;
import com.vidaplus.sghss.repository.ConsultaRepository;
import com.vidaplus.sghss.repository.ProfissionalRepository;
import com.vidaplus.sghss.dto.GradeTrabalhoRequest;
import com.vidaplus.sghss.dto.GradeTrabalhoResponse;
import com.vidaplus.sghss.dto.BloqueioRequest;
import com.vidaplus.sghss.dto.BloqueioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    private GradeTrabalhoRepository gradeTrabalhoRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    // ======== GRADE DE TRABALHO ========

    public GradeTrabalhoResponse criarGrade(GradeTrabalhoRequest request) {
        Profissional profissional = profissionalRepository.findById(request.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        GradeTrabalho grade = new GradeTrabalho();
        grade.setProfissional(profissional);
        grade.setDiaSemana(request.getDiaSemana());
        grade.setHoraInicio(request.getHoraInicio());
        grade.setHoraFim(request.getHoraFim());
        grade.setDuracaoConsultaMinutos(request.getDuracaoConsultaMinutos());

        GradeTrabalho salva = gradeTrabalhoRepository.save(grade);
        return toGradeResponse(salva);
    }

    public List<GradeTrabalhoResponse> listarGradePorProfissional(Long profissionalId) {
        return gradeTrabalhoRepository.findByProfissionalId(profissionalId)
                .stream()
                .map(this::toGradeResponse)
                .collect(Collectors.toList());
    }

    public void deletarGrade(Long gradeId) {
        gradeTrabalhoRepository.deleteById(gradeId);
    }

    // ======== BLOQUEIOS ========

    public BloqueioResponse criarBloqueio(BloqueioRequest request) {
        Profissional profissional = profissionalRepository.findById(request.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Bloqueio bloqueio = new Bloqueio();
        bloqueio.setProfissional(profissional);
        bloqueio.setDataHoraInicio(request.getDataHoraInicio());
        bloqueio.setDataHoraFim(request.getDataHoraFim());
        bloqueio.setMotivo(request.getMotivo());

        Bloqueio salvo = bloqueioRepository.save(bloqueio);
        return toBloqueioResponse(salvo);
    }

    public List<BloqueioResponse> listarBloqueiosPorProfissional(Long profissionalId) {
        return bloqueioRepository.findByProfissionalId(profissionalId)
                .stream()
                .map(this::toBloqueioResponse)
                .collect(Collectors.toList());
    }

    public void deletarBloqueio(Long bloqueioId) {
        bloqueioRepository.deleteById(bloqueioId);
    }

    // ======== CALCULAR DISPONIBILIDADE ========

    public List<LocalDateTime> calcularDisponibilidade(Long profissionalId, LocalDate data) {
        List<LocalDateTime> disponibilidades = new java.util.ArrayList<>();

        // Obter grade de trabalho do dia
        String diaSemana = data.getDayOfWeek().toString();
        List<GradeTrabalho> grades = gradeTrabalhoRepository.findByProfissionalIdAndDiaSemana(profissionalId, diaSemana);

        if (grades.isEmpty()) {
            return disponibilidades; // Sem grade nesse dia
        }

        GradeTrabalho grade = grades.get(0);
        LocalDateTime inicio = LocalDateTime.of(data, grade.getHoraInicio());
        LocalDateTime fim = LocalDateTime.of(data, grade.getHoraFim());

        // Gerar todos os horários possíveis
        LocalDateTime horarioAtual = inicio;
        while (horarioAtual.plusMinutes(grade.getDuracaoConsultaMinutos()).isBefore(fim) || 
               horarioAtual.plusMinutes(grade.getDuracaoConsultaMinutos()).isEqual(fim)) {
            horarioAtual = horarioAtual.plusMinutes(grade.getDuracaoConsultaMinutos());

            // Verificar se está bloqueado
            boolean estaBloqueado = estaBloqueado(profissionalId, horarioAtual);

            // Verificar se já tem consulta
            boolean temConsulta = temConsulta(profissionalId, horarioAtual, grade.getDuracaoConsultaMinutos());

            if (!estaBloqueado && !temConsulta) {
                disponibilidades.add(horarioAtual);
            }
        }

        return disponibilidades;
    }

    private boolean estaBloqueado(Long profissionalId, LocalDateTime dataHora) {
        LocalDateTime inicio = dataHora;
        LocalDateTime fim = dataHora;

        List<Bloqueio> bloqueios = bloqueioRepository.findByProfissionalIdAndDataHoraInicioBetween(
                profissionalId, inicio.minusDays(1), fim.plusDays(1));

        return bloqueios.stream()
                .anyMatch(b -> !dataHora.isBefore(b.getDataHoraInicio()) && !dataHora.isAfter(b.getDataHoraFim()));
    }

    private boolean temConsulta(Long profissionalId, LocalDateTime dataHora, Integer minutos) {
        LocalDateTime fim = dataHora.plusMinutes(minutos);

        List<Consulta> consultas = consultaRepository.findByProfissionalId(profissionalId);

        return consultas.stream()
                .filter(c -> "AGENDADA".equals(c.getStatus()))
                .anyMatch(c -> !dataHora.isAfter(c.getDataHoraFim()) && !fim.isBefore(c.getDataHoraInicio()));
    }

    // ======== MAPPERS ========

    private GradeTrabalhoResponse toGradeResponse(GradeTrabalho grade) {
        GradeTrabalhoResponse response = new GradeTrabalhoResponse();
        response.setId(grade.getId());
        response.setProfissionalId(grade.getProfissional().getId());
        response.setDiaSemana(grade.getDiaSemana());
        response.setHoraInicio(grade.getHoraInicio());
        response.setHoraFim(grade.getHoraFim());
        response.setDuracaoConsultaMinutos(grade.getDuracaoConsultaMinutos());
        return response;
    }

    private BloqueioResponse toBloqueioResponse(Bloqueio bloqueio) {
        BloqueioResponse response = new BloqueioResponse();
        response.setId(bloqueio.getId());
        response.setProfissionalId(bloqueio.getProfissional().getId());
        response.setDataHoraInicio(bloqueio.getDataHoraInicio());
        response.setDataHoraFim(bloqueio.getDataHoraFim());
        response.setMotivo(bloqueio.getMotivo());
        return response;
    }
}
