package sistema;

import entidades.Tupla;
import java.util.List;

public class HomeSolution implements IHomeSolution {

    @Override
    public void registrarEmpleado(String nombre, double valor) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarEmpleado'");
    }

    @Override
    public void registrarEmpleado(String nombre, double valor, String categoria) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarEmpleado'");
    }

    @Override
    public void registrarProyecto(String[] titulos, String[] descripcion, double[] dias, String domicilio,
            String[] cliente, String inicio, String fin) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarProyecto'");
    }

    @Override
    public void asignarResponsableEnTarea(Integer numero, String titulo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarResponsableEnTarea'");
    }

    @Override
    public void asignarResponsableMenosRetraso(Integer numero, String titulo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarResponsableMenosRetraso'");
    }

    @Override
    public void registrarRetrasoEnTarea(Integer numero, String titulo, double cantidadDias)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarRetrasoEnTarea'");
    }

    @Override
    public void agregarTareaEnProyecto(Integer numero, String titulo, String descripcion, double dias)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarTareaEnProyecto'");
    }

    @Override
    public void finalizarTarea(Integer numero, String titulo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finalizarTarea'");
    }

    @Override
    public void finalizarProyecto(Integer numero, String fin) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finalizarProyecto'");
    }

    @Override
    public void reasignarEmpleadoEnProyecto(Integer numero, Integer legajo, String titulo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reasignarEmpleadoEnProyecto'");
    }

    @Override
    public void reasignarEmpleadoConMenosRetraso(Integer numero, String titulo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reasignarEmpleadoConMenosRetraso'");
    }

    @Override
    public double costoProyecto(Integer numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'costoProyecto'");
    }

    @Override
    public List<Tupla<Integer, String>> proyectosFinalizados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'proyectosFinalizados'");
    }

    @Override
    public List<Tupla<Integer, String>> proyectosPendientes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'proyectosPendientes'");
    }

    @Override
    public List<Tupla<Integer, String>> proyectosActivos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'proyectosActivos'");
    }

    @Override
    public Object[] empleadosNoAsignados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'empleadosNoAsignados'");
    }

    @Override
    public boolean estaFinalizado(Integer numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'estaFinalizado'");
    }

    @Override
    public int consultarCantidadRetrasosEmpleado(Integer legajo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarCantidadRetrasosEmpleado'");
    }

    @Override
    public List<Tupla<Integer, String>> empleadosAsignadosAProyecto(Integer numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'empleadosAsignadosAProyecto'");
    }

    @Override
    public Object[] tareasProyectoNoAsignadas(Integer numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tareasProyectoNoAsignadas'");
    }

    @Override
    public Object[] tareasDeUnProyecto(Integer numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tareasDeUnProyecto'");
    }

    @Override
    public String consultarDomicilioProyecto(Integer numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarDomicilioProyecto'");
    }

    @Override
    public boolean tieneRestrasos(Integer legajo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tieneRestrasos'");
    }

    @Override
    public List<Tupla<Integer, String>> empleados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'empleados'");
    }

    @Override
    public String consultarProyecto(Integer numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarProyecto'");
    }

}
