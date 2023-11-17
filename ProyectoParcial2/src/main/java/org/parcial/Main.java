package org.parcial;

import static spark.Spark.*;
import com.google.gson.Gson;

import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();

        ArrayList<Vehiculo> coches = new ArrayList<>();
        ArrayList<Vehiculo> motocicletas = new ArrayList<>();

        Automovil auto = new Automovil(4, "Mazda", "3", "ZYX987", 5);
        coches.add(auto);

        Motocicleta moto = new Motocicleta(600, "Honda", "CBR600", "XYZ789", 2);
        motocicletas.add(moto);

        get("/automoviles", (req, res) -> {
            res.type("application/json");
            return gson.toJson(coches);
        });

        get("/motocicleta", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motocicletas);
        });

        get("/agregarMotocicleta/:marca/:modelo/:placa/:cilindrado", (req, res) -> {
            res.type("application/json");
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
            int numeroPuertas = Integer.parseInt(req.params(":cilindrado"));
            Motocicleta nuevaMoto = new Motocicleta(numeroPuertas, marca, modelo,placa, getCurrentHour());
            motocicletas.add(nuevaMoto);
            return gson.toJson(nuevaMoto);
        });

        get("/agregarAutomovil/:marca/:modelo/:placa/:numeroPuertas", (req, res) -> {
            res.type("application/json");
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));
            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo,placa, getCurrentHour());
            coches.add(nuevoAuto);
            return gson.toJson(nuevoAuto);
        });

        get("/setHoraDeSalida/:tipoDeVehiculo/:placa/:horaDeSalida", (req, res) -> {
            res.type("application/json");
            String tipoDeVehiculo = req.params(":tipoDeVehiculo");
            String placa = req.params(":placa");
            int horaDeSalida = Integer.parseInt(req.params(":horaDeSalida"));
            if(horaDeSalida >= 0 && horaDeSalida <= 24){
                if(tipoDeVehiculo.equals("Automovil")){
                    Vehiculo vehiculo = obtenerVehiculoPorPlaca(coches, placa);
                    if(vehiculo != null){
                        vehiculo.setHoraDeSalida(horaDeSalida);
                        vehiculo.uptDineroGenerado();
                        vehiculo.setHoraDeEntrada(-1);
                    }
                    return gson.toJson(coches);
                }else if(tipoDeVehiculo.equals("Motocicleta")){
                    Vehiculo vehiculo = obtenerVehiculoPorPlaca(motocicletas, placa);
                    if(vehiculo != null){
                        vehiculo.setHoraDeSalida(horaDeSalida);
                        vehiculo.uptDineroGenerado();
                        vehiculo.setHoraDeEntrada(-1);
                    }
                    return gson.toJson(motocicletas);
                }
            }
            return null;
        });

        get("/motosActuales", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> motosActuales = new ArrayList<>();
            for (Vehiculo item: motocicletas) {
                if(item.horaDeSalida == -1)
                    motosActuales.add(item);
            }
            return gson.toJson(motosActuales);
        });

        get("/automovilesActuales", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> autosActuales = new ArrayList<>();
            for (Vehiculo item: coches) {
                if(item.horaDeSalida == -1)
                    autosActuales.add(item);
            }
            return gson.toJson(autosActuales);
        });

        get("/motosReporte", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> ganancias = new ArrayList<>();
            for (Vehiculo item: motocicletas) {
                if(item.horaDeSalida != -1)
                    ganancias.add(item);
            }
            return gson.toJson(ganancias);
        });

        get("/automovilesReporte", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> ganancias = new ArrayList<>();
            for (Vehiculo item: coches) {
                if(item.horaDeSalida != -1)
                    ganancias.add(item);
            }
            return gson.toJson(ganancias);
        });
    }

    public static int getCurrentHour() {
        LocalTime now = LocalTime.now();
        return now.getHour();
    }

    public static Vehiculo obtenerVehiculoPorPlaca(ArrayList<Vehiculo> vehiculos, String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                return vehiculo;
            }
        }
        return null;
    }
}
