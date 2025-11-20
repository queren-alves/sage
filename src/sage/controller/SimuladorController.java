package sage.controller;

import sage.simulation.MenuSimulation;

public class SimuladorController {

    private static MenuSimulation simulador;
    private static Thread t;

    public static void iniciar() {
        simulador = new MenuSimulation();
        t = new Thread(simulador);
        t.start();
    }

    public static void parar() {
        if (simulador != null) simulador.parar();
        if (t != null && t.isAlive()) {
            t.interrupt();
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

