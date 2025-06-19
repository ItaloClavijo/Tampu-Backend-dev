package pe.edu.upc.tampubackend.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultadoPredictDTO {

    @JsonProperty("prediccion")  // <- mapea a "nivel"
    private int nivel;

    @JsonProperty("interpretacion")  // <- mapea a "descripcion"
    private String descripcion;

    public ResultadoPredictDTO() {}

    public ResultadoPredictDTO(int nivel, String descripcion) {
        this.nivel = nivel;
        this.descripcion = descripcion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ResultadoPredictDTO{nivel=" + nivel + ", descripcion='" + descripcion + "'}";
    }
}
