/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.util.ArrayList;

/**
 *
 * @author Grupo 7 (Pedro Conforme,David Llerena,Ariel Velez)
 */
public class Validaciones {

    public static boolean validarNombre(String nombre) {
        if (!isString(nombre)) {
            return true;
        }
        return false;
    }

    public static boolean validarContrasena(String contrasena) {
        if (contrasena.length() < 5 || contrasena.length() > 12 || !isAlphanumeric(contrasena)) {
            return true;
        }
        return false;
    }

    public static boolean validarOpcion(String opcion, int numeroOpciones) {
        if (!isNumeric(opcion) || Integer.parseInt(opcion) > numeroOpciones || Integer.parseInt(opcion) < 1) {
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isCorrectYear(String cadena) {
        try {
            Integer.parseInt(cadena);
            if (cadena.toCharArray().length == 4) {
                return true;
            }
            return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isString(Object str) {
        if (str.equals(str.toString())) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isAlphanumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

}
