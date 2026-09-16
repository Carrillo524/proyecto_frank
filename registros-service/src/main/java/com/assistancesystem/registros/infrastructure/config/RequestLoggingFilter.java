package com.assistancesystem.registros.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de infraestructura que registra en el log la IP remota y el
 * recurso solicitado en cada petición entrante. Es un detalle técnico
 * transversal, ajeno a las reglas de negocio, por lo que vive en la
 * capa de infraestructura.
 */
@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {
        log.info("Petición entrante: {} {} desde IP remota {}",
                request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        filterChain.doFilter(request, response);
    }
}
