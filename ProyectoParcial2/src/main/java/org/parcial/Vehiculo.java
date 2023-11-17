package org.parcial;

public class Vehiculo {
    private String marca;
    private String modelo;
    private String placa;
    int horaDeSalida = -1;
    int horaDeEntrada, ganancias;
    public void setHoraDeSalida(int horaDeSalida) {
        this.horaDeSalida = horaDeSalida;
    }
    public void setHoraDeEntrada(int horaDeEntrada){
        this.horaDeEntrada = horaDeEntrada;
    }

    public void uptDineroGenerado() {
        if(horaDeSalida > horaDeEntrada)
            ganancias = (horaDeSalida - horaDeEntrada) * 15000;
        else
            ganancias = ((24 - horaDeEntrada) + horaDeSalida) * 15000;
    }

    public Vehiculo(String marca, String modelo, String placa, int horaEntrada) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.horaDeEntrada = horaEntrada;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
