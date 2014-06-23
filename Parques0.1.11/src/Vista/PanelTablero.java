/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Felipe
 */
public class PanelTablero extends javax.swing.JPanel {

    Celda celdas[][] = new Celda[17][5];//celdas[i][j], i: columnas(17), j: filas(4)
    Ficha fichas[][] = new Ficha[4][4];//fichas[i][j], i:color (0 rojo, 1 verde, 2 azul, 3 amarillo)

    /**
     * Creates new form PanelTablero
     */
    public PanelTablero() {
        initComponents();
        int aux = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fichas[i][j] = new Ficha(10, aux, 35, 35, i);
                aux = aux + 26;
                fichas[i][j].setyEnCelda(26 * j - 10);
                fichas[i][j].setxInicial(fichas[i][j].x);
                fichas[i][j].setyInicial(fichas[i][j].y);
            }
            aux = aux + 12;
        }
        int x = 55;
        int y = 10;
        int posicion = 1;
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 5; j++) {
                celdas[i][j] = new Celda(x, y, 35, 105);
                celdas[i][j].setPosicion(posicion);
                y = y + 115;
                posicion = posicion + 17;
            }
            posicion = i + 2;
            x = x + 35;
            y = 10;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.        
        g.setColor(Color.BLACK);
        //pintar las celdas
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 4; j++) {
                g.drawRect(celdas[i][j].x, celdas[i][j].y, celdas[i][j].width, celdas[i][j].height);
                g.drawString(String.valueOf(celdas[i][j].getPosicion()), celdas[i][j].x, celdas[i][j].y);
            }
        }

        //pintar salidas
        g.drawImage(new ImageIcon(getClass().getResource("../img/salidarojo.png")).getImage(), celdas[4][0].x, celdas[4][0].y, this);
        g.drawImage(new ImageIcon(getClass().getResource("../img/salidaverde.png")).getImage(), celdas[4][1].x, celdas[4][1].y, this);
        g.drawImage(new ImageIcon(getClass().getResource("../img/salidaazul.png")).getImage(), celdas[4][2].x, celdas[4][2].y, this);
        g.drawImage(new ImageIcon(getClass().getResource("../img/salidaamarillo.png")).getImage(), celdas[4][3].x, celdas[4][3].y, this);
        //pintar seguros
        g.drawImage(new ImageIcon(getClass().getResource("../img/segurorojo.png")).getImage(), celdas[11][0].x, celdas[11][0].y, this);
        g.drawImage(new ImageIcon(getClass().getResource("../img/seguroverde.png")).getImage(), celdas[11][1].x, celdas[11][1].y, this);
        g.drawImage(new ImageIcon(getClass().getResource("../img/seguroazul.png")).getImage(), celdas[11][2].x, celdas[11][2].y, this);
        g.drawImage(new ImageIcon(getClass().getResource("../img/seguroamarillo.png")).getImage(), celdas[11][3].x, celdas[11][3].y, this);
        g.drawImage(new ImageIcon(getClass().getResource("../img/segurorojo.png")).getImage(), celdas[16][3].x, celdas[16][3].y, this);
        g.drawImage(new ImageIcon(getClass().getResource("../img/seguroverde.png")).getImage(), celdas[16][0].x, celdas[16][0].y, this);
        g.drawImage(new ImageIcon(getClass().getResource("../img/seguroazul.png")).getImage(), celdas[16][1].x, celdas[16][1].y, this);
        g.drawImage(new ImageIcon(getClass().getResource("../img/seguroamarillo.png")).getImage(), celdas[16][2].x, celdas[16][2].y, this);

        //pintar fichas
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (fichas[i][j] != null) {
                    if (fichas[i][j].isSeleccionado()) {
                        g.drawImage(new ImageIcon(getClass().getResource("../img/seleccionado.png")).getImage(), fichas[i][j].x, fichas[i][j].y, this);
                    }
                    g.drawImage(new ImageIcon(getClass().getResource("../img/ficha" + i + ".png")).getImage(), fichas[i][j].x, fichas[i][j].y, this);
                }
            }
            //pintar las carceles
            g.drawImage(new ImageIcon(getClass().getResource("../img/carcel.png")).getImage(), celdas[0][0].x - 45, celdas[0][i].y, this);
        }
        
        //Dibujar las celdas que son parte del camino al cielo
        for (int i = 0; i < 7; i++) {
            g.drawRect(celdas[i][4].x, celdas[i][4].y, celdas[i][4].width, celdas[i][4].height);
            g.drawString(String.valueOf(celdas[i][4].getPosicion()), celdas[i][4].x, celdas[i][4].y);
        }
        //pintar imagen de cielo
        g.drawImage(new ImageIcon(getClass().getResource("../img/cielo.jpg")).getImage(), 302, 470, this);
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
