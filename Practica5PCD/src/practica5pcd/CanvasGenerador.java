/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica5pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jarro
 */
class cliente {

    int id;
    char tipo;

    public cliente(int id, char tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public void setid(int id) {
        this.id = id;

    }

    public void settipo(char tipo) {
        this.tipo = tipo;

    }

    public int getid() {
        return id;
    }

    public char gettipo() {
        return tipo;
    }

}

public class CanvasGenerador extends Canvas {

    private ArrayList<Integer> colamasajes = new ArrayList();
    private ArrayList<Integer> colarehabilitacion = new ArrayList();
    private cliente rehabilitacion;
    private cliente masaje;
    private cliente vestuario;
    private Image masajeimg, rehabilitacionimg;

    public CanvasGenerador(int ancho, int alto) throws InterruptedException {

        super.setSize(ancho, alto);
        super.setBackground(Color.white);
        rehabilitacion = new cliente(-1, 'n');
        masaje = new cliente(-1, 'n');
        vestuario = new cliente(-1, 'n');

        masajeimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("masaje.png"));
        rehabilitacionimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("rehabilitacion.png"));
        //tunelimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/tunel2.png"));

        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(masajeimg, 0);
        dibu.waitForID(0);
        dibu.addImage(rehabilitacionimg, 1);
        dibu.waitForID(1);
        //dibu.addImage(tunelimg, 2);
        // dibu.waitForID(2);
    }

    public synchronized void enconlamasajes(int id) {
        colamasajes.add(id);
        repaint();
    }

    public synchronized void fincolamasajes(int id) {
        colamasajes.remove((Object) id);
        repaint();
    }

    public synchronized void enconlarehabilitacion(int id) {
        colarehabilitacion.add(id);
        repaint();
    }

    public synchronized void fincolarehabilitacion(int id) {
        colarehabilitacion.remove((Object) id);
        repaint();
    }

    public synchronized void enmasaje(int id) {
        masaje.setid(id);
        repaint();
    }

    public synchronized void finmasaje() {
        masaje.setid(-1);
        masaje.settipo('n');
        repaint();
    }

    public synchronized void enrehabilitacion(int id, char tipo) {
        if (rehabilitacion.getid() == -1) {
            rehabilitacion.setid(id);
            rehabilitacion.settipo(tipo);
        } else {
            rehabilitacion.setid(id);
            rehabilitacion.settipo(tipo);
        }
        repaint();
    }

    public synchronized void finrehabilitacion() {

        rehabilitacion.setid(-1);
        rehabilitacion.settipo('n');

        repaint();
    }

    public synchronized void envestuario(int id, char tipo){
        
        if (vestuario.getid() == -1) {
            vestuario.setid(id);
            vestuario.settipo(tipo);
        } else {
            vestuario.setid(id);
            vestuario.settipo(tipo);
        }
        repaint();
    }

    public synchronized void finvestuario() {

        vestuario.setid(-1);
        vestuario.settipo('n');

        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Image imagen = createImage(getWidth(), getHeight());
        Font f1 = new Font("Arial", Font.BOLD, 12);
        Graphics gbuf = imagen.getGraphics();
        gbuf.setFont(f1);

        gbuf.setColor(Color.cyan);
        gbuf.fillRect(280, 60, 600, 80);
        gbuf.setColor(Color.red);
        for (int i = 0; i < colamasajes.size(); i++) {
            gbuf.drawString(" " + colamasajes.get(i), 310 + 80 * i, 80);
            gbuf.drawImage(masajeimg, 300 + 80 * i, 80, null);
        }
        gbuf.setColor(Color.pink);
        gbuf.fillRect(280, 260, 600, 80);
        gbuf.setColor(Color.blue);
        for (int i = 0; i < colarehabilitacion.size(); i++) {
            gbuf.drawString(" " + colarehabilitacion.get(i), 310 + 80 * i, 280);
            gbuf.drawImage(rehabilitacionimg, 300 + 80 * i, 280, null);
        }

        gbuf.setColor(Color.black);
        gbuf.drawString("COLA MASAJES", 310, 40);
        gbuf.drawString("COLA REHABILITACION", 310, 240);
        gbuf.drawString("SALA MASAJES", 30, 40);
        gbuf.drawString("SALA REHABILITACION", 30, 240);
        gbuf.drawString("VESTUARIO", 30, 440);
        gbuf.setColor(Color.GRAY);
        gbuf.fillRect(30, 60, 80, 80);
        gbuf.fillRect(30, 260, 80, 80);
        gbuf.fillRect(30, 460, 80, 80);
        // gbuf.drawImage(tunelimg, 40, 10, null);
        // gbuf.drawImage(tunelimg, 40, 130, null);
        // gbuf.drawImage(tunelimg, 40, 250, null);

        if (masaje.getid() != -1) {
            gbuf.setColor(Color.red);
            gbuf.drawImage(masajeimg, 50, 70, null);
            gbuf.drawString("" + masaje.getid(), 55, 60);

        }
        if (rehabilitacion.getid() != -1) {
            if (rehabilitacion.gettipo() == 'r') {
                gbuf.setColor(Color.blue);
                gbuf.drawImage(rehabilitacionimg, 30, 280, null);
                gbuf.drawString("" + rehabilitacion.getid(), 30, 290);
            } else {
                gbuf.setColor(Color.red);
                gbuf.drawImage(masajeimg, 50, 280, null);
                gbuf.drawString("" + rehabilitacion.getid(), 55, 275);
            }
        }

        if (vestuario.getid() != -1) {
            if (vestuario.gettipo() == 'm') {
                gbuf.setColor(Color.red);
                gbuf.drawImage(masajeimg, 50, 480, null);
                gbuf.drawString("" + vestuario.getid(), 55, 475);

            }
            if (vestuario.gettipo() == 'r') {
                gbuf.setColor(Color.blue);
                gbuf.drawImage(rehabilitacionimg, 30, 480, null);
                gbuf.drawString("" + vestuario.getid(), 30, 490);
            }

        }

        g.drawImage(imagen, 0, 0, this);
    }

}
