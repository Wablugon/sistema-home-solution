package sistema;

import entidades.Empleado;
import entidades.Tupla;
import servicios.AsignacionService;
import servicios.EmpleadoService;
import servicios.ProyectoService;

import java.util.ArrayList;
import java.util.List;

public class HomeSolution implements IHomeSolution {

    private final AsignacionService asignacionService;
    private final EmpleadoService empleadoService;
    private final ProyectoService proyectoService;

    public HomeSolution() {
        this.empleadoService = new EmpleadoService();
        this.proyectoService = new ProyectoService();
        this.asignacionService = new AsignacionService(this.empleadoService, this.proyectoService);
    }

    @Override
    public void registrarEmpleado(String nombre, double valor) throws IllegalArgumentException {
        empleadoService.registrarEmpleado(nombre, valor);
    }

    @Override
    public void registrarEmpleado(String nombre, double valor, String categoria) throws IllegalArgumentException {
        empleadoService.registrarEmpleado(nombre, valor, categoria);
    }

    @Override
    public void registrarProyecto(String[] titulos, String[] descripcion, double[] dias, String domicilio,
            String[] cliente, String inicio, String fin) throws IllegalArgumentException {
        proyectoService.registrarProyecto(titulos, descripcion, dias, domicilio, cliente, inicio, fin);
    }

    @Override
    public void asignarResponsableEnTarea(Integer IDProyecto, String tituloTarea) throws Exception {
       asignacionService.asignarResponsableEnTarea(IDProyecto, tituloTarea);
    }

    @Override
    public void asignarResponsableMenosRetraso(Integer IDProyecto, String tituloTarea) throws Exception {
        asignacionService.asignarResponsableMenosRetraso(IDProyecto, tituloTarea);
    }

    @Override
    public void registrarRetrasoEnTarea(Integer IDProyecto, String tituloTarea, double cantidadDias)
            throws IllegalArgumentException {
        proyectoService.registrarRetrasoEnTarea(IDProyecto, tituloTarea, cantidadDias);
    }

    @Override
    public void agregarTareaEnProyecto(Integer IDProyecto, String titulo, String descripcion, double dias)
            throws IllegalArgumentException {
        proyectoService.agregarTarea(IDProyecto, titulo, descripcion, dias);
    }

    @Override
    public void finalizarTarea(Integer IDProyecto, String titulo) throws Exception {
        proyectoService.finalizarTarea(IDProyecto, titulo);
    }

    @Override
    public void finalizarProyecto(Integer IDProyecto, String fin) throws IllegalArgumentException {
        proyectoService.finalizarProyecto(IDProyecto, fin);
    }

    @Override
    public void reasignarEmpleadoEnProyecto(Integer IDProyecto, Integer legajo, String titulo) throws Exception {
        Empleado empleado = empleadoService.obtenerEmpleadoPorLegajo(legajo);
        proyectoService.reasignarEmpleado(empleado, IDProyecto, titulo);
    }

    @Override
    public void reasignarEmpleadoConMenosRetraso(Integer IDProyecto, String titulo) throws Exception {
        Empleado empleado = empleadoService.obtenerEmpleadoPorLegajo(empleadoService.encontrarEmpleadoMenosRetraso());
        proyectoService.reasignarEmpleado(empleado, IDProyecto, titulo);
    }

    @Override
    public double costoProyecto(Integer IDProyecto) {
        return proyectoService.consultarCosto(IDProyecto);
    }

    @Override
    public List<Tupla<Integer, String>> proyectosFinalizados() {
        return proyectoService.proyectosFinalizados()
            .stream()
            .map(p -> new Tupla<>(p.getID(), p.getDomicilio()))
            .toList();
    }

    @Override
    public List<Tupla<Integer, String>> proyectosPendientes() {
        return proyectoService.proyectosPendientes()
            .stream()
            .map(p -> new Tupla<>(p.getID(), p.getDomicilio()))
            .toList();
    }

    @Override
    public List<Tupla<Integer, String>> proyectosActivos() {
        return proyectoService.proyectosActivos()
            .stream()
            .map(p -> new Tupla<>(p.getID(), p.getDomicilio()))
            .toList();
    }

    @Override
    public Object[] empleadosNoAsignados() {
        return empleadoService.empleadosNoAsignados().toArray();
    }

    @Override
    public boolean estaFinalizado(Integer IDProyecto) {
        return proyectoService.estaFinalizado(IDProyecto);
    }

    @Override
    public int consultarCantidadRetrasosEmpleado(Integer legajo) {
        return empleadoService.consultarCantidadRetrasosEmpleado(legajo);
    }

    @Override
    public List<Tupla<Integer, String>> empleadosAsignadosAProyecto(Integer numero) {
        return empleadoService.empleadosAsignados()
            .stream()
            .map(e -> new Tupla<>(e.getLegajo(), e.getNombre()))
            .toList();
    }

    @Override
    public Object[] tareasProyectoNoAsignadas(Integer IDProyecto) {
        return proyectoService.tareasProyectoNoAsignadas(IDProyecto).toArray();
    }
        

    @Override
    public Object[] tareasDeUnProyecto(Integer IDProyecto) {
        return proyectoService.tareasDeUnProyecto(IDProyecto).toArray();
    }

    @Override
    public String consultarDomicilioProyecto(Integer IDProyecto) {
        return proyectoService.consultarDomicilioProyecto(IDProyecto);
    }

    @Override
    public boolean tieneRestrasos(Integer legajo) {
        return empleadoService.tieneRestrasos(legajo);
    }

    @Override
    public List<Tupla<Integer, String>> empleados() {
        return empleadoService.tuplaEmpleados();
    }

    @Override
    public String consultarProyecto(Integer IDProyecto) {
        return proyectoService.informacionProyecto(IDProyecto);
    }

}
