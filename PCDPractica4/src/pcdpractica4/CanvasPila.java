/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcdpractica4;

import java.awt.*;

/**
 *
 * @author jarro
 */
public class CanvasPila extends Canvas {

    private int cima;
    private final int capacidad;
    private int numelementos;
    private Object[] datos;
    private String mensaje;

    public CanvasPila(int capacidad) {
        this.cima = 0;
        this.mensaje = "PILA VACIA";
        this.numelementos = 0;
        this.datos = new Object[capacidad];
        this.capacidad = capacidad;
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void avisa(String mensaje) {
        this.mensaje = mensaje;
        repaint();
    }

    public void representa(Object[] buf, int cima, int numelementos) {
        this.cima = cima;
        this.numelementos = numelementos;
        this.datos = buf;
        mensaje = "";
        
        repaint();
    }

    @Override
    public void paint(Graphics g) {
    // Creamos una imagen y obtenemos sus graphics
    Image offscreen = createImage(getWidth(), getHeight());
    Graphics og = offscreen.getGraphics();

    // Ponemos la fuente
    Font f1 = new Font("Arial", Font.BOLD | Font.ITALIC, 20);
    og.setFont(f1);
    og.setColor(Color.cyan);

    // Calculamos altura de cada casilla
    int casilla = (getHeight() - 100) / (capacidad);

    // Espacio dejado en la parte superior
    int espacio = 20;

    // Dibujamos los cuadrados
    for (int i = 0; i < capacidad; i++) {
        og.drawRect(400, espacio + casilla * capacidad - casilla * (i + 1), 80, casilla);
    }

    // Dibujamos contenido de la pila si no esta vacía
    if (datos != null) {
        for (int i = 0; i < numelementos; i++) {
            // Dibujamos numero en casilla
            og.drawString(datos[i].toString(), 430, espacio + casilla * capacidad - casilla * i);
        }
    }

    // Dibujamos numelementos y capacidad
    og.setFont(new Font("COURIER", Font.BOLD | Font.ITALIC, 20));
    og.drawString("Numero de elementos: " + String.valueOf(numelementos), 20, 310);
    og.drawString("Capacidad: " + String.valueOf(capacidad - numelementos), 20, 330);

    // Dibujamos mensaje
    og.setFont(f1);
    og.drawString(mensaje, 20, 360);

    
    g.drawImage(offscreen, 0, 0, null);
}



}
