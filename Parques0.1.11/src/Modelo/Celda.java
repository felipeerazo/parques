/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Felipe
 */
public class Celda extends Rectangle implements Serializable{

    private int posicion;
    
    public Celda(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * @return the posicion
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
}
