
package org.parcial;

public class Automovil extends Vehiculo{
      private int numeroPuertas;
    public Automovil(int numeroPuertas, String marca, String modelo, String placa, int horaEntrada) {
        super(marca, modelo, placa, horaEntrada);
        this.numeroPuertas = numeroPuertas;
    }
    
    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }
    
  
}
