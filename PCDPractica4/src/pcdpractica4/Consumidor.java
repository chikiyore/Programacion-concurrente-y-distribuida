/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcdpractica4;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jarro
 */
class Consumidor implements Runnable {

    private PilaLenta pila;

    public Consumidor(PilaLenta pila) {
        this.pila = pila;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            for (int i = 0; i < 15; i++) {

                sleep(rand.nextInt(3000 - 1000 + 1) + 1000);

                Object elemento = pila.Desapila();
                // System.out.println("Hilo " + Thread.currentThread().getId() + " desapila "+ elemento);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("                       Hilo " + Thread.currentThread().getId() + " Finaliza");
        System.out.println("-------------> FINALIZA CONSUMIDOR");
    }

}
