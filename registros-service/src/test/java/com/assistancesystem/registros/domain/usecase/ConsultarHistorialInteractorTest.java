package com.assistancesystem.registros.domain.usecase;

import com.assistancesystem.registros.domain.model.Registro;
import com.assistancesystem.registros.domain.model.gateway.RegistroGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultarHistorialInteractorTest {

    private RegistroGateway registroGateway;
    private ConsultarHistorialInteractor interactor;

    @BeforeEach
    void setUp() {
        registroGateway = mock(RegistroGateway.class);
        interactor = new ConsultarHistorialInteractor(registroGateway);
    }

    @Test
    @DisplayName("Éxito: Retorna la lista de registros obtenida desde el gateway para un idGestor válido")
    void testConsultarHistorialExitoso() {
        String idGestor = "gestor-001";
        List<Registro> registrosEsperados = List.of(
                new Registro(UUID.randomUUID(), UUID.randomUUID(), "192.168.1.15", LocalDateTime.now()),
                new Registro(UUID.randomUUID(), UUID.randomUUID(), "192.168.1.20", LocalDateTime.now().minusHours(1))
        );

        when(registroGateway.obtenerPorGestor(idGestor)).thenReturn(registrosEsperados);

        List<Registro> resultado = interactor.ejecutar(idGestor);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(registrosEsperados, resultado);

        verify(registroGateway, times(1)).obtenerPorGestor(idGestor);
    }
}
