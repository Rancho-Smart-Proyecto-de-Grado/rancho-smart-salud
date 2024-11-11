package com.rancho_smart.salud_composer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.rancho_smart.salud_composer.dto.HistorialMedicoCompletoDTO;

import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class HistorialMedicoService {

    @Value("${HISTORIAL_MEDICO_URL}")
    private String historialesMedicosUrl;

    @Value("${CONSULTAS_URL}")
    private String consultasUrl;

    @Value("${PROCEDIMIENTOS_MEDICOS_URL}")
    private String procedimientosUrl;

    @Value("${VACUNAS_URL}")
    private String vacunasUrl;

    private final WebClient.Builder webClientBuilder;

    public HistorialMedicoService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    // Método para crear un WebClient con base URL específica
    private WebClient createWebClient(String baseUrl) {
        return webClientBuilder.baseUrl(baseUrl).build();
    }

    public Mono<HistorialMedicoCompletoDTO> getHistorialMedicoCompletoByIdAnimal(String token, Long idAnimal) {
        return createWebClient(historialesMedicosUrl).get()
                .uri("/historiales-medicos/animal/{idAnimal}", idAnimal)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(HistorialMedicoCompletoDTO.class)
                .doOnNext(historialMedico -> System.out
                        .println("Received historialMedico: " + historialMedico.toString()))
                .flatMap(historialMedico -> {
                    System.out.println("COMPOSING FROM CONSULTA");

                    Mono<List<Object>> consultasMono = createWebClient(consultasUrl).get()
                            .uri("/consultas/historial/{idHistorialMedico}", historialMedico.getIdHistorialMedico())
                            .header("Authorization", "Bearer " + token)
                            .retrieve()
                            .bodyToFlux(Object.class)
                            .collectList()
                            .onErrorResume(WebClientResponseException.NotFound.class, e -> {
                                System.out.println("Consultas not found for idHistorialMedico: " + historialMedico.getIdHistorialMedico());
                                return Mono.just(List.of());
                            });

                    System.out.println("COMPOSING FROM PROCEDIMIENTOS");

                    Mono<List<Object>> procedimientosMono = createWebClient(procedimientosUrl).get()
                            .uri("/procedimientos-medicos/historial/{idHistorialMedico}", historialMedico.getIdHistorialMedico())
                            .header("Authorization", "Bearer " + token)
                            .retrieve()
                            .bodyToFlux(Object.class)
                            .collectList()
                            .onErrorResume(WebClientResponseException.NotFound.class, e -> {
                                System.out.println("Procedimientos not found for idHistorialMedico: " + historialMedico.getIdHistorialMedico());
                                return Mono.just(List.of());
                            });

                    System.out.println("COMPOSING FROM VACUNAS");

                    Mono<List<Object>> vacunasMono = createWebClient(vacunasUrl).get()
                            .uri("/vacunas/historial/{idHistorialMedico}", historialMedico.getIdHistorialMedico())
                            .header("Authorization", "Bearer " + token)
                            .retrieve()
                            .bodyToFlux(Object.class)
                            .collectList()
                            .onErrorResume(WebClientResponseException.NotFound.class, e -> {
                                System.out.println("Vacunas not found for idHistorialMedico: " + historialMedico.getIdHistorialMedico());
                                return Mono.just(List.of());
                            });

                    return Mono.zip(consultasMono, procedimientosMono, vacunasMono)
                            .map(tuple -> {
                                historialMedico.setConsultasHistorial(tuple.getT1());
                                historialMedico.setProcedimientosMedicosHistorial(tuple.getT2());
                                historialMedico.setVacunasHistorial(tuple.getT3());
                                System.out.println("Completed historialMedico with details: " + historialMedico);
                                return historialMedico;
                            });
                })
                .doOnTerminate(() -> System.out.println("Completed all processing"))
                .doOnError(error -> System.out.println("Error occurred: " + error.getMessage()));
    }
}
