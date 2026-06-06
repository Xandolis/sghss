package com.vidaplus.sghss.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    String path = request.getServletPath();

    // libera login, register e debug
    if (path.startsWith("/auth") || path.startsWith("/debug")) {
        filterChain.doFilter(request, response);
        return;
    }

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return;
    }

    String token = authHeader.substring(7);

    if (!jwtUtil.validateToken(token)) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return;
    }

    String email = jwtUtil.getEmailFromToken(token);
    String role = jwtUtil.getRoleFromToken(token);

    // Validar acesso a rotas protegidas
    if (path.startsWith("/admin") && !role.equals("ADMIN")) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return;
    }

    // Armazenar informações na requisição para acesso posterior
    request.setAttribute("email", email);
    request.setAttribute("role", role);

    filterChain.doFilter(request, response);
}
}