/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author jarro
 */
public class Generador extends javax.swing.JFrame {

    public static void main(String args[]) throws InterruptedException {
        boolean finalizar=false;
        Semaphore maquinaCafe = new Semaphore(10);
        Semaphore maquinaLeche = new Semaphore(10);        
        Semaphore salaCafe = new Semaphore(3);
        Semaphore salaLeche = new Semaphore(3);
        Semaphore papelera = new Semaphore(1);
        Random random = new Random();
        Generador g = new Generador();
        g.setSize(800, 600);
        CanvasCongreso cc = new CanvasCongreso(800, 600);
        g.add(cc);
        g.setVisible(true);

        Random rnd = new Random(System.currentTimeMillis());
        Thread camarero = new Camarero(cc, maquinaCafe, maquinaLeche,finalizar);
        camarero.start();
        Thread[] asistentes = new Thread[30];
        rnd.setSeed(System.currentTimeMillis());

        for (int i = 0; i < 30; i++) {
            if (rnd.nextInt(100) < 50) {
                asistentes[i] = new Thread(new Manchado(cc,maquinaCafe,maquinaLeche,salaCafe,salaLeche,papelera));
            } else {
                asistentes[i] = new Cortado(cc,maquinaCafe,maquinaLeche,salaCafe,salaLeche,papelera);
            }
            asistentes[i].start();

            Thread.sleep(random.nextInt(500));
        }
        for (int i = 0; i < 30; i++) {
            asistentes[i].join();
        }
        finalizar=true;
        
        Thread.sleep(2000);
        System.exit(0);
    }
}
