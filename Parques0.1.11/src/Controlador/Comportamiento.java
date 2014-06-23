/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Ficha;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Usuario
 */
public class Comportamiento extends CyclicBehaviour {
    
    private Ficha[][] fichas = null;
    
    @Override
    public void action() {
        ACLMessage recibirM = this.myAgent.receive();//el agente mira la cola de mensajes para procesar
        if (recibirM != null) {
            
            if (this.myAgent instanceof Agente) {
                Agente a1 = (Agente) this.myAgent;
                System.out.println(" estoy en comportamiento");
                String[] s = recibirM.getContent().split(";");
                fichas = new CtrlJSON().generarFicha(s[0]);
                a1.setFichas(getFichas());
                a1.getFi().getFt().setFichas(a1.getFichas());
                int indiceTurno = -1;
                switch (s[1]) {
                    case "rojo":
                        indiceTurno = 0;
                        break;
                    case "verde":
                        indiceTurno = 1;
                        break;
                    case "azul":
                        indiceTurno = 2;
                        break;
                    case "amarillo":
                        indiceTurno = 3;
                        break;
                }
                a1.getFi().getFt().getCtrlReglas().setIndiceTurno(indiceTurno);
                a1.getFi().getFt().getCtrlReglas().getjLabel().setText(s[1]);
                a1.getFi().getFt().accionarMovimientoAutom();
            }
        }
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
    
}
