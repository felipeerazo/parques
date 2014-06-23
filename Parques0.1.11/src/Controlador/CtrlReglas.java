/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.swing.JLabel;

/**
 *
 * @author Felipe sirve para revisar que se cumplan las reglas de juego
 */
public class CtrlReglas {

    private int numJugadores = 4;
    private JLabel jLabel;//muestra que color tiene el turno
    String[] turnos = {"Rojo", "Verde", "Azul", "Amarillo"};
    private int indiceTurno = -1;//para recorrer el vector de turnos
    private int[] contadorDePares = {0, 0, 0, 0};

    public CtrlReglas() {
    }

    /**
     * @param numJugadores the numJugadores to set
     */
    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    /**
     * @return the numJugadores
     */
    public int getNumJugadores() {
        return numJugadores;
    }

    /**
     * @return the jLabel
     */
    public JLabel getjLabel() {
        return jLabel;
    }

    /**
     * @param jLabel the jLabel to set
     */
    public void setjLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }

    /**
     * dice el siguiente color con el turno dependiendo del número de jugadores
     */
    public void getSiguienteTurno() {
        indiceTurno++;
        if (getIndiceTurno() == numJugadores) {
            setIndiceTurno(0);
        }
        jLabel.setText(turnos[getIndiceTurno()]);
    }

    /**
     * de acuerdo al valor que sacó los dados, dice si la ficha se puede mover
     * desde la posicionActual hasta la final
     *
     * @param dados
     * @param posicionActual
     * @param posicionFinal
     * @return
     */
    public boolean movimientoValido(int[] dados, int posicionActual, int posicionFinal) {
//        System.out.println(posicionActual);
//        System.out.println(posicionFinal);
        int res = dados[0] + posicionActual;
        if (res > 68 && posicionFinal <= 68) {
            res = res - 68;
        }
        if (posicionFinal == res) {
            dados[0] = -1;
            return true;
        }
        res = dados[1] + posicionActual;
        if (res > 68 && posicionFinal <= 68) {
            res = res - 68;
        }
        if (posicionFinal == res) {
            dados[1] = -1;
            return true;
        }
        res = dados[0] + dados[1] + posicionActual;
        if (res > 68 && posicionFinal <= 68) {
            res = res - 68;
        }
        if (posicionFinal == res) {
            dados[0] = -1;
            dados[1] = -1;
            return true;
        }
        return false;
    }

    /**
     * consulta si el turno actual tiene 3 pares
     *
     * @return true si no ha sacado los 3 pares para salir
     */
    public boolean estaEnCarcel() {
        if (getContadorDePares()[getIndiceTurno()] == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the contadorDePares
     */
    public int[] getContadorDePares() {
        return contadorDePares;
    }

    /**
     * @param contadorDePares the contadorDePares to set
     */
    public void setContadorDePares(int[] contadorDePares) {
        this.contadorDePares = contadorDePares;
    }

    /**
     * comprueba que haya sacado pares y aumenta el contador de pares
     *
     * @param dados el valor que sacó
     * @return true si los pares =3
     */
//    public boolean saleDeCarcel(int[] dados) {
//        if(dados[0]==dados[1]){
//            contadorDePares[indiceTurno]++;
//            if(contadorDePares[getIndiceTurno()]==3){
//                return true;
//            }
//        }
//        return false;
//    }
    /**
     * @return the indiceTurno
     */
    public int getIndiceTurno() {
        return indiceTurno;
    }

    public boolean movimientoValidoParaCielo(int[] dados, int contadorDeCeldas, int posicionFinal) {
        //contadorDeCeldas=contadorDeCeldas+5;
        if (contadorDeCeldas + dados[0] == posicionFinal) {
            dados[0] = -1;
            return true;
        }
        if (contadorDeCeldas + dados[1] == posicionFinal) {
            dados[1] = -1;
            return true;
        }
        if (contadorDeCeldas + dados[0] + dados[1] == posicionFinal) {
            dados[0] = -1;
            dados[1] = -1;
            return true;
        }
        return false;
    }

    public boolean esSeguroOSalida(int i) {
        if (i == 4) {
            return true;
        }
        if (i == 11) {
            return true;
        }
        if (i == 16) {
            return true;
        }
        return false;
    }

    /**
     * @param indiceTurno the indiceTurno to set
     */
    public void setIndiceTurno(int indiceTurno) {
        this.indiceTurno = indiceTurno;
    }
}
