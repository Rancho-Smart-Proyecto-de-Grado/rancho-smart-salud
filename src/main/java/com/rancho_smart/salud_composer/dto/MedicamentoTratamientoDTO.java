package com.rancho_smart.salud_composer.dto;

public class MedicamentoTratamientoDTO {
    private Long idMedicamentoTratamiento;
    private String nombreMedicamento;
    private double dosisRecomendada;
    private String frecuencia;
    private String observaciones;

    public MedicamentoTratamientoDTO() {
    }

    public MedicamentoTratamientoDTO(String nombreMedicamento, double dosisRecomendada, String frecuencia,
            String observaciones) {
        this.nombreMedicamento = nombreMedicamento;
        this.dosisRecomendada = dosisRecomendada;
        this.frecuencia = frecuencia;
        this.observaciones = observaciones;
    }

    public Long getIdMedicamentoTratamiento() {
        return idMedicamentoTratamiento;
    }

    public void setIdMedicamentoTratamiento(Long idMedicamentoTratamiento) {
        this.idMedicamentoTratamiento = idMedicamentoTratamiento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public double getDosisRecomendada() {
        return dosisRecomendada;
    }

    public void setDosisRecomendada(double dosisRecomendada) {
        this.dosisRecomendada = dosisRecomendada;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
