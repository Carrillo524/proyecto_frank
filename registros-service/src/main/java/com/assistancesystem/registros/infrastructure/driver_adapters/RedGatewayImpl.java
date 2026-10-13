package com.assistancesystem.registros.infrastructure.driver_adapters;

import com.assistancesystem.registros.domain.model.gateway.RedGateway;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * Adaptador de salida que implementa el puerto {@link RedGateway}.
 * Valida la sintaxis de la IP de origen (IPv4 e IPv6 completas o abreviadas)
 * utilizando el analizador sintáctico de la red de Java.
 * Maneja la verificación de encabezados HTTP (X-Forwarded-For, X-Real-IP) y fallback a IP remota.
 */
@Component
@RequiredArgsConstructor
public class RedGatewayImpl implements RedGateway {

    private final HttpServletRequest request;

    @Override
    public boolean esIpValida(String ipOrigen) {
        String ipAValidar = ipOrigen;
        
        if (request != null) {
            String forwardedFor = request.getHeader("X-Forwarded-For");
            if (forwardedFor != null && !forwardedFor.isBlank() && !"unknown".equalsIgnoreCase(forwardedFor)) {
                ipAValidar = forwardedFor.split(",")[0].trim();
            } else {
                String realIp = request.getHeader("X-Real-IP");
                if (realIp != null && !realIp.isBlank() && !"unknown".equalsIgnoreCase(realIp)) {
                    ipAValidar = realIp.trim();
                } else {
                    String remoteAddr = request.getRemoteAddr();
                    if (remoteAddr != null && !remoteAddr.isBlank()) {
                        ipAValidar = remoteAddr;
                    }
                }
            }
        }

        if (ipAValidar == null || ipAValidar.isBlank()) {
            ipAValidar = ipOrigen;
        }

        if (ipAValidar == null || ipAValidar.isBlank()) {
            return false;
        }

        try {
            InetAddress address = InetAddress.getByName(ipAValidar);
            return address.getHostAddress().equals(ipAValidar) || ipAValidar.contains(":");
        } catch (Exception e) {
            return false;
        }
    }
}
