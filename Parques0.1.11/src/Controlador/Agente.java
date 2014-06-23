/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Celda;
import Modelo.DireccionAgente;
import Modelo.Ficha;
import Modelo.Nodo;
import Vista.FormInicial;
import jade.core.AID;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import java.util.LinkedList;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
public class Agente extends GuiAgent {

    LinkedList<Nodo> nodos = new LinkedList<>();
    LinkedList<Ficha> fichasCome = new LinkedList<>();
    private FormInicial fi;
    private String colorJugador;
    private String tipoJuego;
    LinkedList<DireccionAgente> direcciones;
    private Celda[][] celdas;
    int indice;
    private Ficha[][] fichas;

    @Override
    protected void setup() {
        setFi(new FormInicial(this));
        getFi().setVisible(true);
        Comportamiento com = new Comportamiento();
        com.setFichas(fichas);
        this.addBehaviour(com);
    }

    @Override
    protected void onGuiEvent(GuiEvent evento) {
        for (int i = 0; i < direcciones.size(); i++) {
            System.out.println(direcciones.get(0).direccionAgente);
            AID inte = new AID();
            inte.setName(direcciones.get(i).nombreAgente);
            inte.addAddresses(direcciones.get(i).direccionAgente);
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(inte);
            String turno = obtenerColorSiguiente();
            msg.setContent(evento.getParameter(0).toString() + ";" + turno);
            send(msg);
        }
    }

    public void recibirDatos(LinkedList<DireccionAgente> direcciones, String color, String tipoJuego) {
        this.direcciones = direcciones;
        this.setColorJugador(color);
        this.setTipoJuego(tipoJuego);

    }

    public void mostrarMensaje(String msj) {
        System.out.println(msj);

    }

    /**
     * @return the colorJugador
     */
    public String getColorJugador() {
        return colorJugador;
    }

    /**
     * @param colorJugador the colorJugador to set
     */
    public void setColorJugador(String colorJugador) {
        this.colorJugador = colorJugador;
    }

    /**
     * @return the tipoJuego
     */
    public String getTipoJuego() {
        return tipoJuego;
    }

    /**
     * @param tipoJuego the tipoJuego to set
     */
    public void setTipoJuego(String tipoJuego) {
        this.tipoJuego = tipoJuego;
    }

    public void moverseHasta(int posicion) {
        int j = posicion / 17;
        int i = (posicion % 17) - 1;

    }

    public void validarMto(int[] dados, JLabel notificaciones) {
        nodos = new LinkedList<>();
        boolean puedeComer0 = true;
        boolean puedeComer2 = true;
        if (colorJugador.equals("rojo")) {
            indice = 0;
        }
        if (colorJugador.equals("verde")) {
            indice = 1;
        }
        if (colorJugador.equals("azul")) {
            indice = 2;
        }
        if (colorJugador.equals("amarillo")) {
            indice = 3;
        }

        
        for (int z = 0; z < 4; z++) {

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if ((fichas[indice][z].x != fichas[indice][z].getxInicial()
                            && fichas[indice][z].y != fichas[indice][z].getyInicial())
                            || (fichas[indice][z].y != -100 && fichas[indice][z].x != -100)) {
                        //comer
                        int k = ((fichas[indice][z].getPosicionActual() + dados[0]) % 17) - 1;
                        if (k == -1) {
                            k = 16;
                        }
                        if (fichas[indice][z].getPosicionActual() + dados[0] == fichas[i][j].getPosicionActual() && fichas[i][j].getIndiceColor() != indice && !esSeguroOSalida(k)) {
                            if (puedeComer0) {
                                nodos.add(new Nodo("comer", "dados0", fichas[indice][z].getPosicionActual() + dados[0], fichas[indice][z]));
                                puedeComer0 = false;
                            }
                        }
                        k = ((fichas[indice][z].getPosicionActual() + dados[1]) % 17) - 1;
                        if (k == -1) {
                            k = 16;
                        }
                        if (fichas[indice][z].getPosicionActual() + dados[1] == fichas[i][j].getPosicionActual() && fichas[i][j].getIndiceColor() != indice && !esSeguroOSalida(k)) {
                            if (puedeComer0) {
                                nodos.add(new Nodo("comer", "dados1", fichas[indice][z].getPosicionActual() + dados[1], fichas[indice][z]));
                                puedeComer0 = false;
                            }
                        }
                        k = ((fichas[indice][z].getPosicionActual() + dados[0] + dados[1]) % 17) - 1;
                        if (k == -1) {
                            k = 16;
                        }
                        if (fichas[indice][z].getPosicionActual() + (dados[0] + dados[1]) == fichas[i][j].getPosicionActual() && fichas[i][j].getIndiceColor() != indice && !esSeguroOSalida(k)) {
                            if (puedeComer2) {
                                nodos.add(new Nodo("comer", "dados+", fichas[indice][z].getPosicionActual() + (dados[0] + dados[1]), fichas[indice][z]));
                                puedeComer2 = false;
                            }
                        }
                    }//fin if puede mover
                }//fin for j
            }//fin for i
        }
        System.out.println("");
        if (dados[0] == dados[1]) {
            for (int j = 0; j < 4; j++) {
                if (fichas[indice][j].x == fichas[indice][j].getxInicial()
                        && fichas[indice][j].y == fichas[indice][j].getyInicial()) {

                    nodos.add(new Nodo("sacarCarcel", "dados+", 0, fichas[indice][j]));
                }

            }
        }

        boolean asegurar = true;
        for (int i = 0; i < 4; i++) {

            int k = ((fichas[indice][i].getPosicionActual() + dados[0]) % 17) - 1;
            if (k == -1) {
                k = 16;
            }
            if (esSeguroOSalida(k)) {
                if (asegurar) {
                     if (fichas[indice][i].x != fichas[indice][i].getxInicial() && fichas[indice][i].y != fichas[indice][i].getyInicial()) {
           
                    nodos.add(new Nodo("asegurar", "dados0", fichas[indice][i].getPosicionActual() + (dados[0]), fichas[indice][i]));
                    asegurar = false;
                }}
            }

            k = ((fichas[indice][i].getPosicionActual() + dados[1]) % 17) - 1;
            if (k == -1) {
                k = 16;
            }
            if (esSeguroOSalida(k)) {
                if (asegurar) {
                     if (fichas[indice][i].x != fichas[indice][i].getxInicial() && fichas[indice][i].y != fichas[indice][i].getyInicial()) {
           
                    nodos.add(new Nodo("asegurar", "dados1", fichas[indice][i].getPosicionActual() + (dados[1]), fichas[indice][i]));
                }}
                asegurar = false;
            }
            k = ((fichas[indice][i].getPosicionActual() + dados[0] + dados[1]) % 17) - 1;
            if (k == -1) {
                k = 16;
            }
            if (esSeguroOSalida(k)) {
                 if (fichas[indice][i].x != fichas[indice][i].getxInicial() && fichas[indice][i].y != fichas[indice][i].getyInicial()) {
           
                nodos.add(new Nodo("asegurar", "dados+", fichas[indice][i].getPosicionActual() + (dados[0] + dados[1]), fichas[indice][i]));
            }}
        }
        int max = 0;
        int indiceMax = 0;
        for (int j = 0; j < 4; j++) {
            if (fichas[indice][j].getPosicionActual() > max) {
                max = fichas[indice][j].getPosicionActual();
                indiceMax = j;
            }
        }

        nodos.add(new Nodo("avanzar", "dados+", max + (dados[0] + dados[1]), fichas[indice][indiceMax]));
//        nodos.add(new Nodo("avanzar", "dados0", max + (dados[0]), fichas[indice][indiceMax]));
//        nodos.add(new Nodo("avanzar", "dados1", max + (dados[1]), fichas[indice][indiceMax]));

        for (int i = 1; i < 2; i++) {
            if (fichas[indice][i].x != fichas[indice][i].getxInicial() && fichas[indice][i].y != fichas[indice][i].getyInicial()) {
                nodos.add(new Nodo("avanzar", "dados+", fichas[indice][i].getPosicionActual() + (dados[0] + dados[1]), fichas[indice][i]));
                nodos.add(new Nodo("avanzar", "dados0", fichas[indice][i].getPosicionActual() + (dados[0]), fichas[indice][i]));
                nodos.add(new Nodo("avanzar", "dados1", fichas[indice][i].getPosicionActual() + (dados[1]), fichas[indice][i]));
            }
        }

        for (int i = 0; i < nodos.size(); i++) {
            System.out.println("ÑÑ " + nodos.get(i).getAccion() + " " + nodos.get(i).getMovimiento());
        }
        boolean bandera = false;
        for (int i = 0; i < nodos.size(); i++) {

            if (nodos.get(i).getAccion().equals("comer")) {

                int k = (nodos.get(i).getPosicion() % 17) - 1;
                int l = (nodos.get(i).getPosicion() / 17);

                if (k == -1) {
                    k = 16;
                    l = l - 1;
                }
                nodos.get(i).getFicha().moverseHasta(celdas[k][l]);

                for (int j = 0; j < 4; j++) {
                    for (int m = 0; m < 4; m++) {
                        if (fichas[j][m].getPosicionActual() == nodos.get(i).getPosicion() && !esSeguroOSalida(k) && fichas[j][m].getIndiceColor() != nodos.get(i).getFicha().getIndiceColor()) {
                            fichas[j][m].mandarACarcel();
                        }
                    }
                }
                if (nodos.get(i).getMovimiento().equals("dados0")) {
                    for (int j = 0; j < nodos.size(); j++) {
                        if (nodos.get(j).getMovimiento().equals("dados+") || nodos.get(j).getMovimiento().equals("dados0")) {
                            nodos.remove(j);
                            j = -1;
                        }
                    }
                    if (!notificaciones.getText().equals("En espera")) {
                        notificaciones.setText("En espera");
                        bandera = true;
                    } else {
                        notificaciones.setText("Acabó");
                        bandera = false;
                    }

                } else if (nodos.get(i).getMovimiento().equals("dados1")) {
                    for (int j = 0; j < nodos.size(); j++) {
                        if (nodos.get(j).getMovimiento().equals("dados+") || nodos.get(j).getMovimiento().equals("dados1")) {
                            nodos.remove(j);
                            j = -1;
                        }
                    }
                    if (!notificaciones.getText().equals("En espera")) {
                        notificaciones.setText("En espera");
                        bandera = true;
                    } else {
                        notificaciones.setText("Acabó");
                        bandera = false;
                    }
                } else if (nodos.get(i).getMovimiento().equals("dados+")) {
                    notificaciones.setText("Acabó");
                    return;
                }

            } else if (nodos.get(i).getAccion().equals("sacarCarcel")) {

                nodos.get(i).getFicha().moverseHasta(celdas[4][nodos.get(i).getFicha().getIndiceColor()]);
                nodos.get(i).getFicha().setContadorDeCeldas(0);
                notificaciones.setText("Acabó");
                return;
            } else if (nodos.get(i).getAccion().equals("asegurar")) {
                int k = (nodos.get(i).getPosicion() % 17) - 1;
                int l = (nodos.get(i).getPosicion() / 17);

                if (k == -1) {
                    k = 16;
                    l = l - 1;
                }

                nodos.get(i).getFicha().moverseHasta(celdas[k][l]);

                if (nodos.get(i).getMovimiento().equals("dados0")) {
                    for (int j = 0; j < nodos.size(); j++) {
                        if (nodos.get(j).getMovimiento().equals("dados+") || nodos.get(j).getMovimiento().equals("dados0")) {
                            nodos.remove(j);
                            j = -1;
                        }
                    }
                    if (!notificaciones.getText().equals("En espera")) {
                        notificaciones.setText("En espera");
                        bandera = true;
                    } else {
                        notificaciones.setText("Acabó");
                        bandera = false;
                    }

                } else if (nodos.get(i).getMovimiento().equals("dados1")) {
                    for (int j = 0; j < nodos.size(); j++) {
                        if (nodos.get(j).getMovimiento().equals("dados+") || nodos.get(j).getMovimiento().equals("dados1")) {
                            nodos.remove(j);
                            j = -1;
                        }
                    }
                    if (!notificaciones.getText().equals("En espera")) {
                        notificaciones.setText("En espera");
                        bandera = true;
                    } else {
                        notificaciones.setText("Acabó");
                        bandera = false;
                    }

                } else if (nodos.get(i).getMovimiento().equals("dados+")) {
                    notificaciones.setText("Acabó");
                    return;
                }
            } else if (nodos.get(i).getAccion().equals("avanzar")) {
                int k = (nodos.get(i).getPosicion() % 17) - 1;
                int l = (nodos.get(i).getPosicion() / 17);
                if (k == -1) {
                    k = 16;
                    l = l - 1;
                }
                nodos.get(i).getFicha().moverseHasta(celdas[k][l]);

                if (nodos.get(i).getMovimiento().equals("dados0")) {
                    for (int j = 0; j < nodos.size(); j++) {
                        if (nodos.get(j).getMovimiento().equals("dados+") || nodos.get(j).getMovimiento().equals("dados0")) {
                            nodos.remove(j);
                            j = -1;
                        }
                    }
                    if (!notificaciones.getText().equals("En espera")) {
                        notificaciones.setText("En espera");
                        bandera = true;
                    } else {
                        notificaciones.setText("Acabó");
                        bandera = false;
                    }

                } else if (nodos.get(i).getMovimiento().equals("dados1")) {
                    for (int j = 0; j < nodos.size(); j++) {
                        if (nodos.get(j).getMovimiento().equals("dados+") || nodos.get(j).getMovimiento().equals("dados1")) {
                            nodos.remove(j);
                            j = -1;
                        }
                    }
                    if (!notificaciones.getText().equals("En espera")) {
                        notificaciones.setText("En espera");
                        bandera = true;
                    } else {
                        notificaciones.setText("Acabó");
                        bandera = false;
                    }

                } else if (nodos.get(i).getMovimiento().equals("dados+")) {
                    notificaciones.setText("Acabó");
                    return;
                }
//                notificaciones.setText("Acabó");
            }
            if (bandera) {
                i = -1;
            }
        }
//        notificaciones.setText("Acabó");
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
     * @return the celdas
     */
    public Celda[][] getCeldas() {
        return celdas;
    }

    /**
     * @param celdas the celdas to set
     */
    public void setCeldas(Celda[][] celdas) {
        this.celdas = celdas;
    }

    private String obtenerColorSiguiente() {

        return direcciones.get(0).color;
    }

    /**
     * @return the fichas
     */
    public Ficha[][] getFichas() {
        return fichas;
    }

    /**
     * @param fichas the fichas to set
     */
    public void setFichas(Ficha[][] fichas) {
        this.fichas = fichas;
    }

    /**
     * @return the fi
     */
    public FormInicial getFi() {
        return fi;
    }

    /**
     * @param fi the fi to set
     */
    public void setFi(FormInicial fi) {
        this.fi = fi;
    }
}
