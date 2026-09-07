package com.assistancesystem.registros.interfaceadapters.controller;

import com.assistancesystem.registros.application.RegistrarAsistenciaUseCase;
import com.assistancesystem.registros.application.exception.IpOrigenInvalidaException;
import com.assistancesystem.registros.domain.entities.Registro;
import com.assistancesystem.registros.interfaceadapters.presenters.RegistroRequestDTO;
import com.assistancesystem.registros.interfaceadapters.presenters.RegistroResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Adaptador de entrada (Controller REST).
 * Expone los endpoints HTTP para la gestión de registros de asistencia.
 */
@RestController
@RequestMapping("/api/registros")
@RequiredArgsConstructor
public class RegistroController {

    private final RegistrarAsistenciaUseCase registrarAsistenciaUseCase;

    @PostMapping
    public ResponseEntity<RegistroResponseDTO> registrarAsistencia(@Valid @RequestBody RegistroRequestDTO request) {
        // Si usaste Java Records en RegistroRequestDTO: request.usuarioId(), request.ipOrigen()
        // Si mantuviste Lombok en RegistroRequestDTO: request.getUsuarioId(), request.getIpOrigen()
        Registro registro = registrarAsistenciaUseCase.registrar(request.usuarioId(), request.ipOrigen());
        return ResponseEntity.status(HttpStatus.CREATED).body(RegistroResponseDTO.desde(registro));
    }

    @ExceptionHandler(IpOrigenInvalidaException.class)
    public ResponseEntity<Map<String, String>> manejarIpInvalida(IpOrigenInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }
}