package entidades;

import excepciones.DatosInvalidosException;

public enum Categoria {
    INICIAL,
    TECNICO,
    EXPERTO;

    public static Categoria esCategoriaValida(String categoria) {
        try {
            return Categoria.valueOf(categoria.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DatosInvalidosException("Categoría inválida: " + categoria);
        }
    }
}
