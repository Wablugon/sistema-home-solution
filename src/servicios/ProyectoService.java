package servicios;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Empleado;
import entidades.Estado;
import entidades.Proyecto;
import entidades.Tarea;
import excepciones.DatosInvalidosException;
import util.Validaciones;

public class ProyectoService {

    private int siguienteID = 1;
    private Map<Integer, Proyecto> proyectos;

    public ProyectoService() {
        proyectos = new HashMap<>();
    }

    public void registrarProyecto(String[] titulos, String[] descripcion, double[] dias, String domicilio,
			String[] cliente, String inicio, String fin) {
        
        if(titulos.length != descripcion.length || titulos.length != dias.length) {
            throw new DatosInvalidosException("Cantidad de titulos, descripciones y dias no coinciden");
        }

        if(titulos.length == 0) {
            throw new DatosInvalidosException("Debe haber al menos una tarea");
        }

        Validaciones.validarNoNulo(cliente);

        if(cliente.length != 3) {
            throw new DatosInvalidosException("Deben estar presentes los tres datos del cliente: nombre, email, teléfono.");
        }

        String nombreCliente = cliente[0];
	    String emailCliente = cliente[1];
	    int telefonoCliente;
	    String telefonoStr = cliente[2];

        Validaciones.validarNoNulo(nombreCliente);
        Validaciones.validarNoVacio(nombreCliente);
        Validaciones.validarNoNulo(emailCliente);
        Validaciones.validarNoVacio(emailCliente);
        Validaciones.validarNoNulo(telefonoStr);
        Validaciones.validarNoVacio(telefonoStr);

        telefonoCliente = convertirTelefono(telefonoStr);

        LocalDate fechaInicio = Validaciones.parsearFecha(inicio);
        LocalDate fechaFin = Validaciones.parsearFecha(fin);

        Validaciones.validarFechaFutura(fechaFin);
        Validaciones.validarOrdenFechas(fechaInicio, fechaFin);

        Proyecto nuevoProyecto = new Proyecto(generarID(), domicilio, fechaInicio, fechaFin, nombreCliente, telefonoCliente, emailCliente);
        for(int i = 0; i < titulos.length; i++) {
            Validaciones.validarNoNulo(titulos[i]);
            Validaciones.validarNoVacio(titulos[i]);
            Validaciones.validarNoNulo(descripcion[i]);
            Validaciones.validarNoVacio(descripcion[i]);
            Validaciones.validarPositivo(dias[i]);
            boolean operacion = nuevoProyecto.agregarTarea(titulos[i], descripcion[i], dias[i]);
            if(!operacion) {
	    		throw new DatosInvalidosException("No se pudo agregar la tarea: " + titulos[i]);
	    	}
        }        
        proyectos.put(nuevoProyecto.getID(), nuevoProyecto);
    }

    //TODO: evaluar parametros, placeholder: sin parámetros
    public void agregarTareaEnProyecto() {
        //TODO
    }

    //TODO: evaluar parametros, placeholder: sin parámetros
    public void registrarRetrasoEnTarea() {
        //TODO
    }

    //TODO: evaluar parametros, placeholder: sin parámetros
    public void finalizarTarea(Integer numero, String titulo) {
        //TODO
    }
    
    public void finalizarProyecto(Integer numero, String fin) {
        //TODDO
    }

    public double costoProyecto(Integer numero) {
        //TODO
        return -1;
    }

    //TODO: evaluar parametros, retornar id o objeto proyecto, placeholder: ID
    public List<Integer> proyectosPendientes(Integer numero) {
        //TODO
        return null;
    }

        //TODO: evaluar parametros, retornar id o objeto proyecto, placeholder: ID
    public List<Integer> proyectosActivos(Integer numero) {
        //TODO
        return null;
    }

        //TODO: evaluar parametros, retornar id o objeto proyecto, placeholder: ID
    public List<Integer> proyectosFinalizados(Integer numero) {
        //TODO
        return null;
    }

    //Utilizar excepcion correspondiente "ProyectoFinalizadoException" || "ProyectoNoEncontradoException"
    public boolean verificarProyecto(Integer ID) {
        //TODO
        return false;
    }

    public boolean estaFinalizado(Integer numero) {
        //TODO
        return false;
    }

    public List<Empleado> empleadosAsignadosAProyecto(Integer numero) {
        //TODO
        return null;
    }

    public List<Tarea> tareasProyectoNoAsignadas(Integer numero) {
        //TODO
        return null;
    }

    public List<Tarea> tareasDeUnProyecto(Integer numero) {
        //TODO
        return null;
    }

    public String consultarDomicilioProyecto(Integer numero) {
        //TODO
        return null;
    }

    public boolean verificarTarea(Proyecto proyecto, String titulo) {
        //TODO
        return false;
    }

    private Estado consultarProyecto(Integer numero) {
        //TODO
        return null;
    }

    private boolean verificarProyectoGeneral(Integer ID) {
        //TODO
        return false;
    }

    private boolean verificarFinalizacion(Integer ID) {
        //TODO
        return false;
    }

    private int convertirTelefono(String telefonoStr) {
        int telefonoCliente;
        try {
	        telefonoCliente = Integer.parseInt(telefonoStr);
	    } catch (DatosInvalidosException e) {
	        throw new IllegalArgumentException("El teléfono del cliente no es un número válido: " + telefonoStr);
	    }

        return telefonoCliente;
    }

    private int generarID() {
        return siguienteID++;
    }


}
