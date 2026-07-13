package servicios;

import java.util.List;

import entidades.Empleado;
import entidades.Estado;
import entidades.Proyecto;
import entidades.Tarea;

public class ProyectoService {

    //TODO: evaluar parametros, placeholder: sin parámetros
    public void registrarProyecto() {
        //TODO
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


}
