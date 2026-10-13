package com.assistancesystem.registros.infrastructure.entry_points;

import com.assistancesystem.registros.domain.model.Registro;
import com.assistancesystem.registros.domain.usecase.ConsultarHistorialUseCase;
import com.assistancesystem.registros.domain.usecase.RegistrarAsistenciaUseCase;
import com.assistancesystem.registros.infrastructure.entry_points.dto.RegistroRequestDTO;
import com.assistancesystem.registros.infrastructure.entry_points.dto.RegistroResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador de entrada (Controller REST).
 * Expone los endpoints HTTP para la gestión de registros de asistencia.
 */
@RestController
@RequestMapping("/api/registros")
@RequiredArgsConstructor
public class RegistroController {

    private final RegistrarAsistenciaUseCase registrarAsistenciaUseCase;
    private final ConsultarHistorialUseCase consultarHistorialUseCase;

    @PostMapping
    public ResponseEntity<RegistroResponseDTO> registrarAsistencia(@Valid @RequestBody RegistroRequestDTO request, HttpServletRequest servletRequest) {
        String ip = resolveIp(servletRequest);
        Registro registro = registrarAsistenciaUseCase.registrar(request.usuarioId(), ip != null && !ip.isBlank() ? ip : request.ipOrigen());
        return ResponseEntity.status(HttpStatus.CREATED).body(RegistroResponseDTO.desde(registro));
    }

    @GetMapping("/historial/{idGestor}")
    public ResponseEntity<List<RegistroResponseDTO>> consultarHistorial(@PathVariable String idGestor) {
        List<Registro> registros = consultarHistorialUseCase.ejecutar(idGestor);
        List<RegistroResponseDTO> response = registros.stream()
                .map(RegistroResponseDTO::desde)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private String resolveIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isBlank() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}

