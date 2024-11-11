package com.rancho_smart.salud_composer.dto;

import java.util.List;

public class HistorialMedicoCompletoDTO {

    private Long idHistorialMedico;
    private String descripcion;
    private Long idAnimal;
    private String diagnostico;
    private boolean enfermedadesCronicas;
    private List<String> observaciones;
    private List<Object> vacunasHistorial;
    private List<Object> procedimientosMedicosHistorial;
    private List<Object> consultasHistorial;
    private List<TratamientoCompletoDTO> tratamientos;

    public HistorialMedicoCompletoDTO() {
    }

    public HistorialMedicoCompletoDTO(String descripcion, Long idAnimal, String diagnostico,
            boolean enfermedadesCronicas, List<String> observaciones, List<Object> vacunasHistorial,
            List<Object> procedimientosMedicosHistorial, List<Object> consultasHistorial,
            List<TratamientoCompletoDTO> tratamientos) {
        this.descripcion = descripcion;
        this.idAnimal = idAnimal;
        this.diagnostico = diagnostico;
        this.enfermedadesCronicas = enfermedadesCronicas;
        this.observaciones = observaciones;
        this.vacunasHistorial = vacunasHistorial;
        this.procedimientosMedicosHistorial = procedimientosMedicosHistorial;
        this.consultasHistorial = consultasHistorial;
        this.tratamientos = tratamientos;
    }

    public Long getIdHistorialMedico() {
        return idHistorialMedico;
    }

    public void setIdHistorialMedico(Long idHistorialMedico) {
        this.idHistorialMedico = idHistorialMedico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public boolean isEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    public void setEnfermedadesCronicas(boolean enfermedadesCronicas) {
        this.enfermedadesCronicas = enfermedadesCronicas;
    }

    public List<String> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<String> observaciones) {
        this.observaciones = observaciones;
    }

    public List<Object> getVacunasHistorial() {
        return vacunasHistorial;
    }

    public void setVacunasHistorial(List<Object> vacunasHistorial) {
        this.vacunasHistorial = vacunasHistorial;
    }

    public List<Object> getProcedimientosMedicosHistorial() {
        return procedimientosMedicosHistorial;
    }

    public void setProcedimientosMedicosHistorial(List<Object> procedimientosMedicosHistorial) {
        this.procedimientosMedicosHistorial = procedimientosMedicosHistorial;
    }

    public List<Object> getConsultasHistorial() {
        return consultasHistorial;
    }

    public void setConsultasHistorial(List<Object> consultasHistorial) {
        this.consultasHistorial = consultasHistorial;
    }

    public List<TratamientoCompletoDTO> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<TratamientoCompletoDTO> tratamientos) {
        this.tratamientos = tratamientos;
    }
}
