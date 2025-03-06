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
class Manchado implements Runnable {

    private CanvasCongreso cc;
    private Random random = new Random();
    private Semaphore maquinaCafe;
    private Semaphore maquinaLeche;
    private Semaphore salaCafe;
    private Semaphore salaLeche;
    private Semaphore papelera;

    public Manchado(CanvasCongreso cc, Semaphore maquinaCafe, Semaphore maquinaLeche, Semaphore salaCafe, Semaphore salaLeche, Semaphore papelera) {
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
            cc.ensalon(id, 'M', 0, 0);
            cc.finsalon(id, 'M', 0, 0);
            cc.encolacafe(id, 'M', 0, 0);
            salaCafe.acquire();
            cc.fincolacafe(id, 'M', 0, 0);
            cc.ensalacafe(id, 'M', 0, 0);
            maquinaCafe.acquire(1);
            System.out.println("El asistente " + id + " se sirve cafe");
            cc.finsalacafe(id, 'M', 0, 1);
            salaCafe.release();
            cc.encolaleche(id, 'M', 0, 1);
            salaLeche.acquire();
            cc.fincolaleche(id, 'M', 0, 1);
            cc.ensalaleche(id, 'M', 0, 1);
            maquinaLeche.acquire(2);
            System.out.println("El asistente " + id + " se sirve leche");
            cc.finsalaleche(id, 'M', 2, 1);
            salaLeche.release();
            cc.ensalon(id, 'M', 2, 1);            
            System.out.println("El asistente " + id + " está bebiendo su café");
            Thread.sleep(tiempoSala);
            cc.finsalon(id, 'M', 2, 1);
            cc.encolapapelera(id, 'M', 2, 1);
            papelera.acquire();
            cc.fincolapapelera(id, 'M', 2, 1);
            cc.enpapelera(id, 'M', 2, 1);
            System.out.println("El asistente " + id + " tira la taza");
            Thread.sleep(1000);
            cc.finpapelera(id, 'M', 2, 1);
            papelera.release();

        } catch (InterruptedException e) {

        }
        System.out.println("Termina el hilo asistente " + Thread.currentThread().getId());
    }
}
