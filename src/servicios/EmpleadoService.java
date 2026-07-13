package servicios;

import java.util.List;
import entidades.Empleado;

public class EmpleadoService {

    public void registrarEmpleado(String nombre, double valor) {
        //TODO
    }

    public void registrarEmpleado(String nombre, double valor, String categoria) {
        //TODO
    }

    public int consultarCantidadRetrasosEmpleado(Integer legajo) {
        //TODO
        return -1;
    }

    public boolean tieneRestrasos(Integer legajo) {
        //TODO
        return false;
    }

    public List<Empleado> empleados() {
        //TODO
        return null;
    }

    public List<Empleado> empleadosNoAsignados() {
        //TODO
        return null;
    }

    private void liberarEmpleado(Empleado empleado) {
        //TODO
    }

    //TODO: Evaluar retorno empleado o legajo, placeholder: legajo
    private int encontrarEmpleadoMenosRetraso() {
        //TODO
        return -1;
    }
}
