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
public class Generador extends javax.swing.JFrame {

    public static void main(String args[]) throws InterruptedException {
        Random random = new Random();
        Generador g = new Generador();
        g.setSize(800, 600);
        CanvasGenerador cg = new CanvasGenerador(800, 600);
        g.add(cg);
        g.setVisible(true);

        Random rnd = new Random(System.currentTimeMillis());

        Thread[] paciente = new Thread[10];
        Centro c = new Centro();
        rnd.setSeed(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {
            if (rnd.nextInt(100) < 40) {
                paciente[i] = new Thread(new Rehabilita(c, cg));
            } else {
                paciente[i] = new Masaje(c, cg);
            }
            paciente[i].start();

            Thread.sleep(random.nextInt(1000 + 1) + 1000);
        }
        for (int i = 0; i < 10; i++) {
            paciente[i].join();
        }

        Thread.sleep(2000);
        System.exit(0);
    }
}
