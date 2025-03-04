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
public class Masaje extends Thread {

    private Centro centro;
    private CanvasGenerador cg;
    private Random random = new Random();

    public Masaje(Centro centro, CanvasGenerador cg) {
        this.centro = centro;
        this.cg = cg;

    }

    @Override
    public void run() {
        int id = (int) Thread.currentThread().getId();
        try {
            // Entre 2 y 4 segundos
            int tiempoMasaje = 2000 + random.nextInt(2000);
            System.out.println("Soy el masaje " + getId());
            //Lo pone en la cola
            cg.enconlamasajes(id);
            //DESCOMENTAR PARA QUE SE VEA COMO LLEGA A LA FILA
            //Thread.sleep(tiempoMasaje);
            //Saber a que sala entra
            char donde = centro.EntraMasaje();
            //Lo quitamos de la cola de espera
            cg.fincolamasajes(id);

            if (donde == 'r') {
                cg.enrehabilitacion(id, 'm');
            } else {
                cg.enmasaje(id);
            }
            Thread.sleep(tiempoMasaje);
            centro.SaleMasaje(donde);
            if (donde == 'r') {
                cg.finrehabilitacion();
            } else {
                cg.finmasaje();
            }
            cg.envestuario(id, 'm');
            Thread.sleep(2000);
            cg.finvestuario();
            centro.Termina();

        } catch (InterruptedException e) {

        }
        System.out.println("Termina el hilo " + Thread.currentThread().getId());
    }
}
