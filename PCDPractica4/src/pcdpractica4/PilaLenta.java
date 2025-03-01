/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcdpractica4;

/**
 *
 * @author jarro
 */
public class PilaLenta implements IPila {

    private Object[] datos;
    private int cima;
    private int capacidad;
    private int numelementos;
    private CanvasPila canvas;

    public PilaLenta(int capacidad, CanvasPila elcanvas) {
        this.capacidad = capacidad;
        this.datos = new Object[capacidad];
        this.cima = 0;
        this.numelementos = 0;
        this.canvas = elcanvas;

    }

    @Override
    public int GetNum() {
        return numelementos;
    }

    @Override
    public synchronized void Apila(Object elemento) throws Exception {
        //Thread.sleep(100);
        int contador = 0;
        while (numelementos == capacidad && contador < 3) {
            //this.canvas.avisa("PILA LLENA");
            wait();
            System.out.println("Hilo " + Thread.currentThread().getId() + " intenta apilar " + elemento + ". Intento número " + (contador + 1) + "/3");
            if (numelementos == capacidad && contador == 2) {
                System.out.println("Hilo " + Thread.currentThread().getId() + " no puede apilar " + elemento + " porque la pila esta llena y se han agotado los intentos");
            }
            contador++;

        }
        if (numelementos < capacidad) {
            datos[cima] = elemento;
            cima++;
            numelementos++;
            this.canvas.representa(datos, cima, numelementos);
            System.out.println("Hilo " + Thread.currentThread().getId() + " apila " + elemento);
            notifyAll();
        } else {
            this.canvas.avisa("PILA LLENA");
            throw new Exception("Apilado de " + elemento + " fallido");

        }

    }

    @Override
    public synchronized Object Desapila() throws Exception {
        //Thread.sleep(100);
        int contador = 0;
        while (numelementos == 0 && contador < 3) {
            Object elemento = datos[cima - 1];
            wait();
            System.out.println("Hilo " + Thread.currentThread().getId() + " intenta desapilar " + elemento + ". Intento número " + (contador + 1) + "/3");
            contador++;
        }
        if (numelementos > 0) {
            cima--;
            numelementos--;
            this.canvas.representa(datos, cima, numelementos);
            Object elemento = datos[cima];
            datos[cima] = null; // Liberar la referencia al elemento desapilado
            System.out.println("Hilo " + Thread.currentThread().getId() + " desapila " + elemento);
            notifyAll();
            return elemento;
        } else {
            this.canvas.avisa("PILA VACIA");
            Object elemento = datos[cima];
            throw new Exception("Desapilado de " + elemento + " fallido");
        }
    }

    @Override
    public Object Primero() throws Exception {
        if (numelementos > 0) {
            return datos[cima - 1];
        } else {
            throw new Exception("La pila está vacía.");
        }
    }

    public boolean pilavacia() {
        if (numelementos == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean pilallena() {
        if (numelementos == capacidad) {
            return true;
        } else {
            return false;
        }
    }

}
