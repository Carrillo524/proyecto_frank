package com.assistancesystem.registros.domain.usecase;

import com.assistancesystem.registros.domain.exception.IpOrigenInvalidaException;
import com.assistancesystem.registros.domain.model.Registro;
import com.assistancesystem.registros.domain.model.gateway.RedGateway;
import com.assistancesystem.registros.domain.model.gateway.RegistroGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrarAsistenciaInteractorTest {

    private RegistroGateway registroGateway;
    private RedGateway redGateway;
    private RegistrarAsistenciaInteractor interactor;

    @BeforeEach
    void setUp() {
        registroGateway = mock(RegistroGateway.class);
        redGateway = mock(RedGateway.class);
        interactor = new RegistrarAsistenciaInteractor(registroGateway, redGateway);
    }

    @Test
    @DisplayName("Éxito: Al recibir una IP válida, se guarda el registro correctamente")
    void testRegistrarAsistenciaExitoso() {
        UUID usuarioId = UUID.randomUUID();
        String ipValida = "192.168.1.10";

        when(redGateway.esIpValida(ipValida)).thenReturn(true);
        when(registroGateway.guardar(any(Registro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Registro resultado = interactor.registrar(usuarioId, ipValida);

        assertNotNull(resultado);
        assertEquals(usuarioId, resultado.getUsuarioId());
        assertEquals(ipValida, resultado.getIpOrigen());
        assertNotNull(resultado.getFechaHora());

        verify(redGateway, times(1)).esIpValida(ipValida);
        verify(registroGateway, times(1)).guardar(any(Registro.class));
    }

    @Test
    @DisplayName("Excepción: Al recibir una IP inválida, se lanza IpOrigenInvalidaException y no se guarda")
    void testRegistrarAsistenciaIpInvalida() {
        UUID usuarioId = UUID.randomUUID();
        String ipInvalida = "999.999.999.999";

        when(redGateway.esIpValida(ipInvalida)).thenReturn(false);

        assertThrows(IpOrigenInvalidaException.class, () -> {
            interactor.registrar(usuarioId, ipInvalida);
        });

        verify(redGateway, times(1)).esIpValida(ipInvalida);
        verify(registroGateway, never()).guardar(any(Registro.class));
    }
}
