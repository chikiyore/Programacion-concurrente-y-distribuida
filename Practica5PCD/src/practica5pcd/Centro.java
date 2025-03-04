/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica5pcd;

/**
 *
 * @author jarro
 */
public class Centro {

    private volatile int vestuariolibre = 1, masajistalibre = 1, rehabilitadorlibre = 1, esperarehabilitacion = 0;

    public synchronized char EntraMasaje() throws InterruptedException {
        while (masajistalibre == 0 && (rehabilitadorlibre == 0 || esperarehabilitacion > 0)) {
            System.out.println("El masaje " + Thread.currentThread().getId() + " espera a que haya hueco en sala masaje o sala rehabilitación");
            wait();
        }
        if (masajistalibre == 1) {
            masajistalibre = 0;
            System.out.println("El masaje " + Thread.currentThread().getId() + " ha entrado en sala masajes");
            return 'm';
        } else {
            rehabilitadorlibre=0;
            System.out.println("El masaje " + Thread.currentThread().getId() + " ha entrado en sala de rehabilitación");
            return 'r';
        }

    }

    public synchronized void SaleMasaje(char donde) throws InterruptedException {
        while (vestuariolibre == 0) {
            System.out.println("El masaje " + Thread.currentThread().getId() + " espera a que haya hueco en el vestuario");
            wait();
        }
        if (donde == 'm') {
            masajistalibre=1;
            vestuariolibre = 0;
            System.out.println("El masaje " + Thread.currentThread().getId() + " sale de la sala de masajes y entra al vestuario");
        } else {
            rehabilitadorlibre = 1;
            vestuariolibre = 0;
            System.out.println("El masaje " + Thread.currentThread().getId() + " sale de la sala de rehabilitación y entra al vestuario");
        }
        notifyAll();
    }

    public synchronized void EntraRehabilitacion() throws InterruptedException {
        esperarehabilitacion++;
        while (rehabilitadorlibre == 0) {
            System.out.println("El de rehabilitación " + Thread.currentThread().getId() + " espera a que haya hueco en sala rehabilitación");
            wait();
        }
        esperarehabilitacion--;
        System.out.println("El de rehabilitación " + Thread.currentThread().getId() + " entra a sala rehabilitación");

        rehabilitadorlibre = 0;

    }

    public synchronized void SaleRehabilitacion() throws InterruptedException {
        while (vestuariolibre == 0) {
            System.out.println("El de rehabilitación " + Thread.currentThread().getId() + " espera a que haya hueco en el vestuario");
            wait();
        }
        rehabilitadorlibre = 1;
        vestuariolibre = 0;
        System.out.println("El de rehabilitación " + Thread.currentThread().getId() + " sale de la sala de rehabilitación y entra al vestuario");
        notifyAll();
    }

    public synchronized void Termina() {
        System.out.println("El hilo " + Thread.currentThread().getId() + " sale del vestuario");
        vestuariolibre = 1;
        notifyAll();
    }

}
