package com.ds.tp.models.dto;

public class RequerimientoContraseniaDTO{

    //a futuro habria que hacer el mapeo a json de los resultados de la consulta GET

    private int cantDigitos;
    private int cantMayusculas;
    private int cantNumeros;

    //getter / setter
    
    public int getCantDigitos() {
        return cantDigitos;
    }
    public void setCantDigitos(int cantDigitos) {
        this.cantDigitos = cantDigitos;
    }
    public int getCantMayusculas() {
        return cantMayusculas;
    }
    public void setCantMayusculas(int cantMayusculas) {
        this.cantMayusculas = cantMayusculas;
    }
    public int getCantNumeros() {
        return cantNumeros;
    }
    public void setCantNumeros(int cantNumeros) {
        this.cantNumeros = cantNumeros;
    }

    //constructores
    public RequerimientoContraseniaDTO(){};

    public RequerimientoContraseniaDTO(int cantDigitos, int cantMayusculas, int cantNumeros) {
        this.cantDigitos = cantDigitos;
        this.cantMayusculas = cantMayusculas;
        this.cantNumeros = cantNumeros;
    }

}
