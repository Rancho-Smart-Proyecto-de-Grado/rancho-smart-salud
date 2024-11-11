package com.rancho_smart.salud_composer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.rancho_smart.salud_composer.dto.TratamientoCompletoDTO;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class TratamientoService {

    private final WebClient.Builder webClientBuilder;

    @Value("${TRATAMIENTOS_URL}")
    private String tratamientosUrl;

    @Value("${CONSULTAS_URL}")
    private String consultasUrl;

    @Value("${PROCEDIMIENTOS_MEDICOS_URL}")
    private String procedimientosUrl;

    @Value("${VACUNAS_URL}")
    private String vacunasUrl;

    public TratamientoService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    // Método para crear un WebClient con base URL específica
    private WebClient createWebClient(String baseUrl) {
        return webClientBuilder.baseUrl(baseUrl).build();
    }

    public Flux<TratamientoCompletoDTO> getTratamientoCompletoByIdHistorialMedico(String token, Long idHistorialMedico) {
        return createWebClient(tratamientosUrl).get()
                .uri("/tratamientos/historial/{idHistorialMedico}", idHistorialMedico)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToFlux(TratamientoCompletoDTO.class)
                .doOnNext(tratamiento -> System.out.println("Received tratamiento: " + tratamiento.toString()))
                .flatMap(tratamiento -> {
                    System.out.println("COMPOSING FROM CONSULTA");
                    Mono<List<Object>> consultasMono = createWebClient(consultasUrl).get()
                            .uri("/consultas/tratamiento/{idTratamiento}", tratamiento.getIdTratamiento())
                            .header("Authorization", "Bearer " + token)
                            .retrieve()
                            .bodyToFlux(Object.class)
                            .collectList()
                            .onErrorResume(e -> {
                                System.out.println("Error fetching consultas: " + e.getMessage());
                                return Mono.empty();
                            });

                    System.out.println("COMPOSING FROM PROCEDIMIENTOS");
                    Mono<List<Object>> procedimientosMono = createWebClient(procedimientosUrl).get()
                            .uri("/procedimientos-medicos/tratamiento/{idTratamiento}", tratamiento.getIdTratamiento())
                            .header("Authorization", "Bearer " + token)
                            .retrieve()
                            .bodyToFlux(Object.class)
                            .collectList()
                            .onErrorResume(e -> {
                                System.out.println("Error fetching procedimientos: " + e.getMessage());
                                return Mono.empty();
                            });

                    System.out.println("COMPOSING FROM VACUNAS");
                    Mono<List<Object>> vacunasMono = createWebClient(vacunasUrl).get()
                            .uri("/vacunas/tratamiento/{idTratamiento}", tratamiento.getIdTratamiento())
                            .header("Authorization", "Bearer " + token)
                            .retrieve()
                            .bodyToFlux(Object.class)
                            .collectList()
                            .onErrorResume(e -> {
                                System.out.println("Error fetching vacunas: " + e.getMessage());
                                return Mono.empty();
                            });

                    return Mono.zip(consultasMono, procedimientosMono, vacunasMono)
                            .map(tuple -> {
                                tratamiento.setConsultasTratamiento(tuple.getT1());
                                tratamiento.setProcedimientosMedicosTratamiento(tuple.getT2());
                                tratamiento.setVacunasTratamiento(tuple.getT3());
                                System.out.println("Completed tratamiento with details: " + tratamiento);
                                return tratamiento;
                            });
                })
                .doOnTerminate(() -> System.out.println("Completed all processing"))
                .doOnError(error -> System.out.println("Error occurred: " + error.getMessage()));
    }
}
