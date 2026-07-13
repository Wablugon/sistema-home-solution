package entidades;

import excepciones.DatosInvalidosException;

public enum Categoria {
    INICIAL,
    TECNICO,
    EXPERTO;

    public static boolean esCategoriaValida(String categoria) {
        try {
            Categoria.valueOf(categoria);
            return true;
        } catch (DatosInvalidosException e) {
            return false;
        }
    }
}
