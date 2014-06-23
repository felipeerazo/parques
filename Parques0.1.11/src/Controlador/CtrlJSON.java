/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Ficha;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Felipe
 */
public class CtrlJSON {

//     private int indiceColor;//0 rojo, 1 verde, 2 azul, 3 amarillo
//    private boolean seleccionado;//para remarcar la ficha con un recuadro naranja
//    private int posicionActual = 0;//dice en que celda está del tablero
//    private int yEnCelda;//para pintar la ficha a una altura 'y' que no queden encimas unas de otras
//    private int contadorDeCeldas = 0;//dice cuántas celdas se ha movido
//    private int xInicial=0;
//    private int yInicial=0;
    public CtrlJSON() {
    }

    public String generarJSON(Ficha[][] fichas) {
        JSONObject object = new JSONObject();
        JSONObject objectFila;
        JSONObject objectColumna;
        for (int i = 0; i < 4; i++) {
            objectFila = new JSONObject();
            for (int j = 0; j < 4; j++) {
                objectColumna = new JSONObject();
                objectColumna.put("x", fichas[i][j].x);
                objectColumna.put("y", fichas[i][j].y);
                objectColumna.put("width", fichas[i][j].width);
                objectColumna.put("height", fichas[i][j].height);
                objectColumna.put("indiceColor", fichas[i][j].getIndiceColor());
                objectColumna.put("seleccionado", fichas[i][j].isSeleccionado());
                objectColumna.put("posicionActual", fichas[i][j].getPosicionActual());
                objectColumna.put("yEnCelda", fichas[i][j].getyEnCelda());
                objectColumna.put("contadorDeCeldas", fichas[i][j].getContadorDeCeldas());
                objectColumna.put("xInicial", fichas[i][j].getxInicial());
                objectColumna.put("yInicial", fichas[i][j].getyInicial());
                objectFila.put(String.valueOf(j), objectColumna);
            }

            object.put(String.valueOf(i), objectFila);
        }

        return object.toJSONString();
    }

    public Ficha [][] generarFicha(String s) {
        try {
            Ficha[][] fichas= new Ficha[4][4];
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(s);
            for (int i = 0; i < 4; i++) {

                JSONObject objectFila = (JSONObject) object.get(String.valueOf(i));
                for (int j = 0; j < 4; j++) {
                    
                    fichas[i][j]= new Ficha();

                    JSONObject objectColumna = (JSONObject) objectFila.get(String.valueOf(j));
                    
                    fichas[i][j].x = Integer.parseInt(objectColumna.get("x").toString());
                    fichas[i][j].y = Integer.parseInt(objectColumna.get("y").toString());
                    fichas[i][j].width = Integer.parseInt(objectColumna.get("width").toString());
                    fichas[i][j].height = Integer.parseInt(objectColumna.get("height").toString());
                    fichas[i][j].setIndiceColor(Integer.parseInt(objectColumna.get("indiceColor").toString()));
                    if (objectColumna.get("seleccionado").toString().equals("true")) {
                        fichas[i][j].setSeleccionado(true);
                    } else {
                        fichas[i][j].setSeleccionado(false);
                    }

                    fichas[i][j].setPosicionActual(Integer.parseInt(objectColumna.get("posicionActual").toString()));
                    fichas[i][j].setyEnCelda(Integer.parseInt(objectColumna.get("yEnCelda").toString()));
                    fichas[i][j].setContadorDeCeldas(Integer.parseInt(objectColumna.get("contadorDeCeldas").toString()));
                    fichas[i][j].setxInicial(Integer.parseInt(objectColumna.get("xInicial").toString()));
                    fichas[i][j].setyInicial(Integer.parseInt(objectColumna.get("yInicial").toString()));
                    
                }
            }
            return fichas;
        } catch (Exception e) {
            System.out.println("Error método generarFicha");

        }
        return null;
    }

}
