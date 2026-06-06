package com.vidaplus.sghss.service;

import com.vidaplus.sghss.model.Usuario;
import com.vidaplus.sghss.repository.UsuarioRepository;
import com.vidaplus.sghss.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
private JwtUtil jwtUtil;

public void register(String nome, String email, String senha, String tipo) {
    if (repository.findByEmail(email).isPresent()) {
        throw new RuntimeException("Email já registrado");
    }

    Usuario usuario = new Usuario();
    usuario.setNome(nome);
    usuario.setEmail(email);
    usuario.setSenha(encoder.encode(senha));
    usuario.setTipo(tipo);

    repository.save(usuario);
}

public String login(String email, String senha) {
    Usuario usuario = repository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    if (!encoder.matches(senha, usuario.getSenha())) {
        throw new RuntimeException("Senha inválida");
    }

    return jwtUtil.generateToken(email, usuario.getTipo());
}
}