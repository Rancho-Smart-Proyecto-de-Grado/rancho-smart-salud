package com.rancho_smart.salud_composer.dto;

import java.time.LocalDate;
import java.util.List;

public class TratamientoCompletoDTO {
    private Long idTratamiento;
    private Long idHistorialMedico;
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Object> vacunasTratamiento;
    private List<Object> procedimientosMedicosTratamiento;
    private List<Object> consultasTratamiento;

    public TratamientoCompletoDTO() {
    }

    public TratamientoCompletoDTO(Long idHistorialMedico, String titulo, String descripcion, LocalDate fechaInicio,
            LocalDate fechaFin, List<Object> vacunasTratamiento, List<Object> procedimientosMedicosTratamiento,
            List<Object> consultasTratamiento) {
        this.idHistorialMedico = idHistorialMedico;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vacunasTratamiento = vacunasTratamiento;
        this.procedimientosMedicosTratamiento = procedimientosMedicosTratamiento;
        this.consultasTratamiento = consultasTratamiento;
    }

    public Long getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Long idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public Long getIdHistorialMedico() {
        return idHistorialMedico;
    }

    public void setIdHistorialMedico(Long idHistorialMedico) {
        this.idHistorialMedico = idHistorialMedico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Object> getVacunasTratamiento() {
        return vacunasTratamiento;
    }

    public void setVacunasTratamiento(List<Object> vacunasTratamiento) {
        this.vacunasTratamiento = vacunasTratamiento;
    }

    public List<Object> getProcedimientosMedicosTratamiento() {
        return procedimientosMedicosTratamiento;
    }

    public void setProcedimientosMedicosTratamiento(List<Object> procedimientosMedicosTratamiento) {
        this.procedimientosMedicosTratamiento = procedimientosMedicosTratamiento;
    }

    public List<Object> getConsultasTratamiento() {
        return consultasTratamiento;
    }

    public void setConsultasTratamiento(List<Object> consultasTratamiento) {
        this.consultasTratamiento = consultasTratamiento;
    }
}
