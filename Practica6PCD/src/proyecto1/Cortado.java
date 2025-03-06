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
public class Cortado extends Thread {

    private CanvasCongreso cc;
    private Random random = new Random();
    private Semaphore maquinaCafe;
    private Semaphore maquinaLeche;
    private Semaphore salaCafe;
    private Semaphore salaLeche;
    private Semaphore papelera;

    public Cortado(CanvasCongreso cc, Semaphore maquinaCafe, Semaphore maquinaLeche, Semaphore salaCafe, Semaphore salaLeche, Semaphore papelera) {
        this.cc = cc;
        this.maquinaCafe = maquinaCafe;
        this.maquinaLeche = maquinaLeche;
        this.salaCafe = salaCafe;
        this.salaLeche = salaLeche;
        this.papelera = papelera;

    }

    @Override
    public void run() {
        int id = (int) Thread.currentThread().getId();
        try {
            // Entre 2 y 4 segundos
            int tiempoSala = 1000 + random.nextInt(2000);
            System.out.println("Soy el asistente " + id);
            //Lo pone en la cola
            cc.ensalon(id, 'C', 0, 0);
            cc.finsalon(id, 'C', 0, 0);
            cc.encolaleche(id, 'C', 0, 0);
            salaLeche.acquire();
            cc.fincolaleche(id, 'C', 0, 0);
            cc.ensalaleche(id, 'C', 0, 0);
            maquinaLeche.acquire(1);
            System.out.println("El asistente " + id + " se sirve leche");
            cc.finsalaleche(id, 'C', 1, 0);
            salaLeche.release();
            cc.encolacafe(id, 'C', 1, 0);
            salaCafe.acquire();
            cc.fincolacafe(id, 'C', 1, 0);
            cc.ensalacafe(id, 'C', 1, 0);
            maquinaCafe.acquire(2);
            System.out.println("El asistente " + id + " se sirve cafe");
            cc.finsalacafe(id, 'C', 1, 2);
            salaCafe.release();
            cc.ensalon(id, 'C', 1, 2);            
            System.out.println("El asistente " + id + " está bebiendo su café");
            Thread.sleep(tiempoSala);
            cc.finsalon(id, 'C', 1, 2);
            cc.encolapapelera(id, 'C', 1, 2);
            papelera.acquire();
            cc.fincolapapelera(id, 'C', 1, 2);
            cc.enpapelera(id, 'C', 1, 2);
            System.out.println("El asistente " + id + " tira la taza");
            Thread.sleep(1000);
            cc.finpapelera(id, 'C', 1, 2);
            papelera.release();

        } catch (InterruptedException e) {

        }
        System.out.println("Termina el hilo asistente " + Thread.currentThread().getId());
    }

}
