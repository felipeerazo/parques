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
public class Ficha extends Rectangle implements Serializable{

    private int indiceColor;//0 rojo, 1 verde, 2 azul, 3 amarillo
    private boolean seleccionado;//para remarcar la ficha con un recuadro naranja
    private int posicionActual = 0;//dice en que celda está del tablero
    private int yEnCelda;//para pintar la ficha a una altura 'y' que no queden encimas unas de otras
    private int contadorDeCeldas = 0;//dice cuántas celdas se ha movido
    private int xInicial=0;
    private int yInicial=0;

    public Ficha(int x, int y, int width, int height, int indiceColor) {
        super(x, y, width, height);
        this.indiceColor = indiceColor;
        //posicionActual = indiceColor * (-22);
    }

    public Ficha() {
    }

    /**
     * @return the seleccionado
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    /**
     * @return the posicionActual
     */
    public int getPosicionActual() {
        return posicionActual;
    }

    /**
     * @param posicionActual the posicionActual to set
     */
    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

    /**
     * @return the yEnCelda
     */
    public int getyEnCelda() {
        return yEnCelda;
    }

    /**
     * @param yEnCelda the yEnCelda to set
     */
    public void setyEnCelda(int yEnCelda) {
        this.yEnCelda = yEnCelda;
    }

    /**
     * se mueve en el tablero hasta pararse en la celdaDestino
     *
     * @param celdaDestino hasta donde se va a mover la ficha
     */
    public void moverseHasta(Celda celdaDestino) {
        x = celdaDestino.x;
        y = celdaDestino.y + getyEnCelda();
        if (celdaDestino.getPosicion() < posicionActual) {
            setContadorDeCeldas(getContadorDeCeldas() + celdaDestino.getPosicion() + 68 - posicionActual);
        } else {
            setContadorDeCeldas(getContadorDeCeldas() + celdaDestino.getPosicion() - posicionActual);
        }
        setPosicionActual(celdaDestino.getPosicion());
    }
    
    

    /**
     * @return the contadorDeCeldas
     */
    public int getContadorDeCeldas() {
        return contadorDeCeldas;
    }

    /**
     * @param contadorDeCeldas the contadorDeCeldas to set
     */
    public void setContadorDeCeldas(int contadorDeCeldas) {
        this.contadorDeCeldas = contadorDeCeldas;
    }

    /**
     * @return the indiceColor
     */
    public int getIndiceColor() {
        return indiceColor;
    }

    /**
     * @param indiceColor the indiceColor to set
     */
    public void setIndiceColor(int indiceColor) {
        this.indiceColor = indiceColor;
    }

    public void moverseEnCieloHasta(Celda celdaDestino) {
        x = celdaDestino.x;
        y = celdaDestino.y + getyEnCelda();
        System.out.println("QQ " + celdaDestino.getPosicion());
        contadorDeCeldas = celdaDestino.getPosicion();
    }

    public void mandarACarcel() {
        x = xInicial;
        y = yInicial;
        contadorDeCeldas = 0;
        posicionActual = 0;
    }

    /**
     * @return the xInicial
     */
    public int getxInicial() {
        return xInicial;
    }

    /**
     * @param xInicial the xInicial to set
     */
    public void setxInicial(int xInicial) {
        this.xInicial = xInicial;
    }

    /**
     * @return the yInicial
     */
    public int getyInicial() {
        return yInicial;
    }

    /**
     * @param yInicial the yInicial to set
     */
    public void setyInicial(int yInicial) {
        this.yInicial = yInicial;
    }
}
