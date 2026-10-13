package com.assistancesystem.registros.application.config;

import com.assistancesystem.registros.domain.usecase.ConsultarHistorialInteractor;
import com.assistancesystem.registros.domain.usecase.ConsultarHistorialUseCase;
import com.assistancesystem.registros.domain.usecase.RegistrarAsistenciaInteractor;
import com.assistancesystem.registros.domain.usecase.RegistrarAsistenciaUseCase;
import com.assistancesystem.registros.domain.model.gateway.RedGateway;
import com.assistancesystem.registros.domain.model.gateway.RegistroGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de infraestructura que cablea (wiring) los casos de uso
 * de la capa application con sus implementaciones concretas de
 * interfaceadapters. Es el único punto donde Spring "conoce" a los
 * interactores, manteniendo la capa application libre de anotaciones
 * de framework.
 */
@Configuration
public class UseCaseConfig {

    @Bean
    public RegistrarAsistenciaUseCase registrarAsistenciaUseCase(RegistroGateway registroGateway,
                                                                   RedGateway redGateway) {
        return new RegistrarAsistenciaInteractor(registroGateway, redGateway);
    }

    @Bean
    public ConsultarHistorialUseCase consultarHistorialUseCase(RegistroGateway registroGateway) {
        return new ConsultarHistorialInteractor(registroGateway);
    }
}

