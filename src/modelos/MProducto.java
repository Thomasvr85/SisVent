/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import Vista.util.Mensaje;
import javax.swing.JTextField;
import static modelos.E_TipoAccion.*;

/**
 *
 * @author HACK
 */
public class MProducto extends MCategoria {

    private boolean puntoDecimalActivado = false;
    private int inicial = -1;
    
    private int idProducto = inicial;
    private String nombreProducto;
    private double precioProducto = inicial;
    private int stockProducto = inicial;
    private String descripcionProducto;
    private int estadoProducto = 1;

    public MProducto() {

    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(int estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public boolean bloquearNoCaracteresAlpha(java.awt.event.KeyEvent evt) {
        int codigoASCII = (int) (evt.getKeyChar());
        if (!(//
                (codigoASCII >= 65 && 90 >= codigoASCII)//MAYUSCULAS
                || (codigoASCII >= 97 && 122 >= codigoASCII)//minusculas
                || codigoASCII == 193/*Á*/ || codigoASCII == 225/*á*/ || codigoASCII == 196/*Ä*/ || codigoASCII == 228/*ä*/
                || codigoASCII == 201/*É*/ || codigoASCII == 233/*é*/ || codigoASCII == 203/*Ë*/ || codigoASCII == 235/*ë*/
                || codigoASCII == 205/*Í*/ || codigoASCII == 237/*í*/ || codigoASCII == 207/*Ï*/ || codigoASCII == 239/*ï*/
                || codigoASCII == 211/*Ó*/ || codigoASCII == 243/*ó*/ || codigoASCII == 214/*Ö*/ || codigoASCII == 246/*ö*/
                || codigoASCII == 218/*Ú*/ || codigoASCII == 250/*ú*/ || codigoASCII == 220/*Ü*/ || codigoASCII == 252/*ü*/
                || codigoASCII == 241/*Ñ*/ || codigoASCII == 209/*n*/ //
                )//
                ) {
            evt.consume();
            return true;
        }
        return false;
    }

    public void reactivarPuntoDecimal(java.awt.event.KeyEvent evt, JTextField txt) {
        String s = txt.getText();

        if (s.split("\\.").length == 1 && s.length() != 0 && s.charAt(s.length() - 1) != '.') {
            puntoDecimalActivado = false;
        }

        //System.out.println("t: " + s + " - b: " + puntoDecimalActivado + " count: " + s.split("\\.").length);
    }

    public boolean bloquearNoNumeroReal(java.awt.event.KeyEvent evt, JTextField txt) {
        String s = txt.getText();
        char c = evt.getKeyChar();
        int codigoASCII = (int) c;

        if (s.matches("[0-9]{0,}\\.[0-9]{2}")) {
            evt.consume();
        }

        if (codigoASCII == 46 && puntoDecimalActivado) {
            evt.consume();
            return true;
        } else if (codigoASCII == 46) {
            puntoDecimalActivado = true;
            return false;
        }

        if (!Character.isDigit(evt.getKeyChar()) && codigoASCII != 46 && codigoASCII != 8 && codigoASCII != 127) {
            evt.consume();
            return true;
        }
        return false;
    }

    public boolean bloquearNoNumeroEntero(java.awt.event.KeyEvent evt) {
        int codeASCII = (int) evt.getKeyChar();
        if (!Character.isDigit(evt.getKeyChar()) && codeASCII != 8 && codeASCII != 127) {
            evt.consume();
            return true;
        }
        return false;
    }

    public boolean bloquearNumeroMenorACero(java.awt.event.KeyEvent evt, JTextField txt) {
        if (!txt.getText().isEmpty() && Double.parseDouble(txt.getText()) < 0) {
            evt.consume();
            return true;
        }
        return false;
    }

    private boolean noVacio(String campo) {
        if (campo.isEmpty()) {
            Mensaje.ADVERTENCIA("El campo (cadena) : " + campo + " está vacio");
        }
        return !campo.isEmpty();
    }

    private boolean noVacio(int campo) {

        if (campo == inicial) {
            Mensaje.ADVERTENCIA("El campo (entero) : " + campo + " está vacio");
        }
        return campo != inicial;
    }

    private boolean noVacio(double campo) {
        if (campo == inicial) {
            Mensaje.ADVERTENCIA("El campo (real) : " + campo + " está vacio");
        }

        return campo != inicial;
    }

    public boolean noVacio(E_TipoAccion tipo) {
        boolean datos = true;

        switch (tipo) {
            case MODIFICAR -> {
                datos
                        = datos
                        && noVacio(estadoProducto)
                        && estructuraCorrectaNombreCategoria();
                System.out.println("Pasa por: Modificar");
            }

            case CONSULTAR, ELMIMNAR -> {
                datos
                        = datos && noVacio(idProducto);
                System.out.println("Pasa por: Consultar O Eliminar");
            }

            case AGREGAR -> {
                datos = datos
                        && noVacio(nombreProducto)
                        && noVacio(precioProducto)
                        && noVacio(stockProducto)
                        && noVacio(descripcionProducto)
                        && estructuraCorrectaNombreCategoria();
                System.out.println("Pasa por: Agregar");
            }

            case AUMENTAR -> {
                datos = datos
                        && noVacio(idProducto)
                        && noVacio(precioProducto)
                        && noVacio(stockProducto)
                        && noVacio(descripcionProducto)
                        && estructuraCorrectaNombreCategoria();
                System.out.println("Pasa por: Aumentar");
            }

        }

        return datos;
    }

}
