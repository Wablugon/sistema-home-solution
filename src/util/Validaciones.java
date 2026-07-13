package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.List;

import excepciones.DatosInvalidosException;
import excepciones.FormatoFechaInvalidoException;

public class Validaciones {

    //Strings
    public static boolean validarNoNulo(String valor) {
        if(valor == null) {
            throw new DatosInvalidosException("El valor no puede ser nulo");
        }
        return true;
    }
    
    public static boolean validarNoVacio(String valor) {
        if(valor.trim().isEmpty()) {
            throw new DatosInvalidosException("El valor no puede ser vacío");
        }
        return true;
    }

    public static boolean validarLongitudMinima(String valor, int minimo) {
        if(valor.length() < minimo) {
            throw new DatosInvalidosException("El valor debe tener al menos " + minimo + " caracteres");
        }
        return true;
    }

    public static boolean validarLongitudMaxima(String valor, int maximo) {
        if(valor.length() > maximo) {
            throw new DatosInvalidosException("El valor no puede tener más de " + maximo + " caracteres");
        }
        return true;
    }

    //Numeros
    public static boolean validarPositivo(double valor) {
        if(valor <= 0) {
            throw new DatosInvalidosException("El valor debe ser positivo");
        }
        return true;
    }

    public static boolean validarNoNegativo(double valor) {
        if(valor < 0) {
            throw new DatosInvalidosException("El valor no puede ser negativo");
        }
        return true;
    }

    public static boolean validarRango(double valor, double minimo, double maximo) {
        if(valor < minimo || valor > maximo) {
            throw new DatosInvalidosException("El valor debe estar entre " + minimo + " y " + maximo);
        }
        return true;
    }

    //Fechas
    public static LocalDate parsearFecha(String fecha) {
        try {
            return LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            throw new FormatoFechaInvalidoException("La fecha debe tener el formato yyyy-MM-dd");
        }
    }

    public static void validarFechaFutura(LocalDate fecha) {
        if(fecha.isBefore(LocalDate.now())) {
            throw new FormatoFechaInvalidoException("La fecha debe ser futura");
        }
    }

    public static void validarOrdenFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        if(fechaFin.isBefore(fechaInicio)) {
            throw new FormatoFechaInvalidoException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
    }

    //Colecciones
    public static boolean validarNoVacia(List<?> lista) {
        if(lista == null) {
            throw new DatosInvalidosException("La lista no puede ser nula");
        }
        if(lista.isEmpty()) {
            throw new DatosInvalidosException("La lista no puede estar vacía");
        }
        return true;
    }

    public static boolean validarNoNulo(Collection<?> coleccion) {
        if(coleccion == null) {
            throw new DatosInvalidosException("La colección no puede ser nula");
        }
        return true;
    }

    //Arrays
    public static boolean validarMismaLongitud(Object[] array1, Object[] array2) {
        if(array1 == null || array2 == null) {
            throw new DatosInvalidosException("Los arrays no pueden ser nulos");
        }

        if(array1.length != array2.length) {
            throw new DatosInvalidosException("Los arrays deben tener la misma longitud");
        }
        return true;
    }

    //Objetos
    public static boolean validarNoNulo(Object objeto) {
        if(objeto == null) {
            throw new DatosInvalidosException("El objeto no puede ser nulo");
        }
        return true;
    }
}
