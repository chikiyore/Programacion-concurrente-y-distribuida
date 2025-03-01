/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcdpractica4;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jarro
 */
public class Productor extends Thread {

    private PilaLenta pila;

    public Productor(PilaLenta pila) {
        this.pila = pila;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            for (int i = 0; i < 15; i++) {

                int num = rand.nextInt(100); // Generar número aleatorio entre 0 y 99

                System.out.println("Hilo " + Thread.currentThread().getId() + " genera el número " + num);
                sleep(rand.nextInt(3000 - 1000 + 1) + 1000);

                pila.Apila(num);
                //System.out.println("Hilo " + Thread.currentThread().getId() + " apila "+ num);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
        System.out.println("                       Hilo " + Thread.currentThread().getId() + " Finaliza");
    }
}
