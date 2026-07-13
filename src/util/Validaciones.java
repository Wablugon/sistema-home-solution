package util;

import java.util.Collection;
import java.util.List;

public class Validaciones {

    //Strings
    public static boolean validarNoNulo(String valor) {
        return valor != null;
    }
    
    public static boolean validarNoVacio(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    public static boolean validarLongitudMinima(String valor, int minimo) {
        return valor != null && valor.length() >= minimo;
    }

    public static boolean validarLongitudMaxima(String valor, int maximo) {
        return valor != null && valor.length() <= maximo;
    }

    //Numeros
    public static boolean validarPositivo(double valor) {
        return valor > 0;
    }  

    public static boolean validarNoNegativo(double valor) {
        return valor >= 0;
    }

    public static boolean validarRango(double valor, double minimo, double maximo) {
        return valor >= minimo && valor <= maximo;
    }

    //Colecciones
    public static boolean validarNoVacia(List<?> lista) {
        return lista != null && !lista.isEmpty();
    }

    public static boolean validarNoNulo(Collection<?> coleccion) {
        return coleccion != null;
    }

    //Arrays
    public static boolean validarMismaLongitud(Object[] array1, Object[] array2) {
        return array1 != null && array2 != null && array1.length == array2.length;
    }

    //Objetos
    public static boolean validarNoNulo(Object objeto) {
        return objeto != null;
    }
}
