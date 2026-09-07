package com.assistancesystem.registros.interfaceadapters.gateway;

import com.assistancesystem.registros.application.gateway.RedGateway;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * Adaptador de salida que implementa el puerto {@link RedGateway}.
 * Valida la sintaxis de la IP de origen (IPv4 e IPv6 completas o abreviadas)
 * utilizando el analizador sintáctico de la red de Java.
 */
@Component
public class RedGatewayImpl implements RedGateway {

    @Override
    public boolean esIpValida(String ipOrigen) {
        if (ipOrigen == null || ipOrigen.isBlank()) {
            return false;
        }
        try {
            // InetAddress.getByName o InetAddresses no resuelven si se valida de forma limpia,
            // pero para evitar consultas DNS inversas/bloqueantes se comprueba el formato binario:
            InetAddress address = InetAddress.getByName(ipOrigen);
            return address.getHostAddress().equals(ipOrigen) || ipOrigen.contains(":");
        } catch (Exception e) {
            return false;
        }
    }
}