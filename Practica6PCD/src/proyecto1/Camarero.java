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
class Camarero extends Thread {

    private CanvasCongreso canvas;
    private Semaphore maquinaCafe;
    private Semaphore maquinaLeche;
    private boolean finalizar;

    public Camarero(CanvasCongreso canvas, Semaphore maquinaCafe, Semaphore maquinaLeche, boolean finalizar) {
        this.canvas = canvas;
        this.maquinaCafe = maquinaCafe;
        this.maquinaLeche = maquinaLeche;
        this.finalizar = finalizar;
    }

    @Override
    public void run() {
        try {
            while (!finalizar) {
                // Tiempo de espera para recargar
                Thread.sleep(6000);

                canvas.camarero('C'); // Indica que el camarero está recargando la máquina de café
                Thread.sleep(1000); // Tiempo de recarga
                canvas.fincamarero();
                maquinaCafe.release(5);
                System.out.println("                                        El camarero recarga la maquina de café");

                canvas.camarero('L'); // Indica que el camarero está recargando la máquina de leche
                Thread.sleep(1000); // Tiempo de recarga
                canvas.fincamarero();
                maquinaLeche.release(5);
                System.out.println("                                        El camarero recarga la maquina de leche");
            }
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}
