/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica5pcd;

import java.util.Random;

/**
 *
 * @author jarro
 */
class Rehabilita implements Runnable {

    private Centro centro;
    private CanvasGenerador cg;
    private Random random = new Random();

    public Rehabilita(Centro centro, CanvasGenerador cg) {
        this.centro = centro;
        this.cg = cg;
    }

    @Override
    public void run() {
        int id = (int) Thread.currentThread().getId();
        try {
            // Entre 2 y 4 segundos
            int tiempoMasaje = 2000 + random.nextInt(2000);
            System.out.println("Soy la rehabilitacion " + Thread.currentThread().getId());
            cg.enconlarehabilitacion(id);
            //DESCOMENTAR PARA QUE SE VEA COMO LLEGA A LA FILA
            //Thread.sleep(tiempoMasaje);
            centro.EntraRehabilitacion();
            cg.fincolarehabilitacion(id);
            cg.enrehabilitacion(id, 'r');
            Thread.sleep(tiempoMasaje);
            centro.SaleRehabilitacion();
            cg.finrehabilitacion();
            cg.envestuario(id, 'r');
            Thread.sleep(2000);
            cg.finvestuario();
            centro.Termina();

        } catch (InterruptedException e) {

        }
        System.out.println("Termina el hilo " + Thread.currentThread().getId());
    }
}
