package com.rancho_smart.salud_composer.controller;

import com.rancho_smart.salud_composer.service.HistorialMedicoService;
import com.rancho_smart.salud_composer.service.TratamientoService;
import com.rancho_smart.salud_composer.dto.HistorialMedicoCompletoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/composer")
public class SaludRESTController {

    @Autowired
    private HistorialMedicoService historialMedicoService;

    @Autowired
    private TratamientoService tratamientoService;

    @GetMapping("/historial-medico/animal/{idAnimal}")
    public Mono<ResponseEntity<HistorialMedicoCompletoDTO>> getHistorialMedicoCompletoByIdAnimal(
            @RequestHeader("Authorization") String token,
            @PathVariable Long idAnimal) {

        return historialMedicoService.getHistorialMedicoCompletoByIdAnimal(token, idAnimal)
                .flatMap(historialMedico -> tratamientoService
                        .getTratamientoCompletoByIdHistorialMedico(token, historialMedico.getIdHistorialMedico())
                        .collectList() // Recoge los tratamientos en una lista
                        .map(tratamientos -> {
                            historialMedico.setTratamientos(tratamientos);
                            return historialMedico;
                        }))
                .map(historialMedico -> ResponseEntity.ok(historialMedico))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)));
    }
}
