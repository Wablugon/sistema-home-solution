package servicios;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import entidades.Empleado;
import entidades.Estado;
import entidades.Proyecto;
import entidades.Tarea;
import excepciones.DatosInvalidosException;
import excepciones.ProyectoNoEncontradoException;
import excepciones.TareaNoEncontradaException;
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

    public void registrarRetrasoEnTarea(int IDProyecto, String tituloTarea, double diasRetraso) {
        Validaciones.validarPositivo(IDProyecto);
        Validaciones.validarNoNulo(tituloTarea);
        Validaciones.validarNoVacio(tituloTarea);
        Validaciones.validarPositivo(diasRetraso);

        Proyecto proyecto = obtenerProyecto(IDProyecto).orElseThrow(() -> new ProyectoNoEncontradoException("No se encontró el proyecto con ID: " + IDProyecto));
        proyecto.registrarRetrasoEnTarea(tituloTarea, diasRetraso);
    }

    public void finalizarTarea(Integer IDProyecto, String tituloTarea) {
        Validaciones.validarPositivo(IDProyecto);
        Validaciones.validarNoNulo(tituloTarea);
        Validaciones.validarNoVacio(tituloTarea);
        Proyecto proyecto = obtenerProyecto(IDProyecto).orElseThrow(() -> new ProyectoNoEncontradoException("No se encontró el proyecto con ID: " + IDProyecto));
        proyecto.finalizarTarea(tituloTarea);
    }
    
    public void finalizarProyecto(Integer IDProyecto, String fechaFin) {
        Validaciones.validarPositivo(IDProyecto);
        Validaciones.validarNoNulo(fechaFin);
        Proyecto proyecto = obtenerProyecto(IDProyecto).orElseThrow(() -> new ProyectoNoEncontradoException("No se encontró el proyecto con ID: " + IDProyecto));
        LocalDate fechaFinLocalDate = Validaciones.parsearFecha(fechaFin);
        LocalDate fechaInicio = proyecto.getFechaInicio();
        Validaciones.validarOrdenFechas(fechaInicio, fechaFinLocalDate);
        proyecto.actualizarAFinalizado(fechaFinLocalDate);
    }

    public double costoProyecto(Integer IDProyecto) {
        Validaciones.validarPositivo(IDProyecto);
        Proyecto proyecto = obtenerProyecto(IDProyecto).orElseThrow(() -> new ProyectoNoEncontradoException("No se encontró el proyecto con ID: " + IDProyecto));
        return proyecto.calcularCostoFinal();
    }

    public List<Integer> proyectosPendientes() {
        return proyectos.values()
                .stream()
                .filter(p -> p.consultarEstado() == Estado.PENDIENTE)
                .map(Proyecto::getID)
                .toList();
    }

    public List<Integer> proyectosActivos() {
        return proyectos.values()
                .stream()
                .filter(p -> p.consultarEstado() == Estado.ACTIVO)
                .map(Proyecto::getID)
                .toList();
    }

    public List<Integer> proyectosFinalizados() {
        return proyectos.values()
                .stream()
                .filter(p -> p.consultarEstado() == Estado.FINALIZADO)
                .map(Proyecto::getID)
                .toList();
    }

    //Retorna su estado en valor String, para poder mostrarlo en la vista
    public String consultarEstado(Integer ID) {
        Validaciones.validarPositivo(ID);
        Proyecto proyecto = obtenerProyecto(ID).orElseThrow(() -> new ProyectoNoEncontradoException("No se encontró el proyecto con ID: " + ID));
        Estado estado = proyecto.consultarEstado();
        return estado.toString();
    }

    public boolean estaFinalizado(Integer numero) {
        Validaciones.validarPositivo(numero);
        Proyecto proyecto = obtenerProyecto(numero).orElseThrow(() -> new ProyectoNoEncontradoException("No se encontró el proyecto con ID: " + numero));
        return proyecto.consultarEstado() == Estado.FINALIZADO;
    }

    public List<Empleado> empleadosAsignadosAProyecto(Integer IDProyecto) {
        Validaciones.validarPositivo(IDProyecto);
        Proyecto proyecto = obtenerProyecto(IDProyecto).orElseThrow(() -> new ProyectoNoEncontradoException("No se encontró el proyecto con ID: " + IDProyecto));
        return proyecto.devolverEmpleados();
    }

    public List<Tarea> tareasProyectoNoAsignadas(Integer IDProyecto) {
        Validaciones.validarPositivo(IDProyecto);
        Proyecto proyecto = obtenerProyecto(IDProyecto).orElseThrow(() -> new ProyectoNoEncontradoException("No se encontró el proyecto con ID: " + IDProyecto));
        return proyecto.tareasNoAsignadas();
    }

    public List<Tarea> tareasDeUnProyecto(Integer IDProyecto) {
        Validaciones.validarPositivo(IDProyecto);
        Proyecto proyecto = obtenerProyecto(IDProyecto).orElseThrow(() -> new ProyectoNoEncontradoException("No se encontró el proyecto con ID: " + IDProyecto));
        return proyecto.devolverTareas();
    }

    public String consultarDomicilioProyecto(Integer IDProyecto) {
        Validaciones.validarPositivo(IDProyecto);
        Proyecto proyecto = obtenerProyecto(IDProyecto).orElseThrow(() -> new ProyectoNoEncontradoException("No se encontró el proyecto con ID: " + IDProyecto));
        return proyecto.direccionVivienda();
    }

    public boolean verificarTarea(Proyecto proyecto, String tituloTarea) {
        Validaciones.validarNoNulo(proyecto);
        Validaciones.validarNoNulo(tituloTarea);
        Validaciones.validarNoVacio(tituloTarea);

        Tarea tarea = proyecto.getTarea(tituloTarea);
        if (tarea == null) {
            throw new TareaNoEncontradaException("La tarea con título '" + tituloTarea + "' no existe en el proyecto con ID: " + proyecto.getID());
        }
        return true;
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

    private Optional<Proyecto> obtenerProyecto(Integer ID) {
        return Optional.ofNullable(proyectos.get(ID));
    }


}
