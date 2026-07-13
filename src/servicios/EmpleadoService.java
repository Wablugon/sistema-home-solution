package servicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Categoria;
import entidades.Empleado;
import entidades.EmpleadoContratado;
import entidades.EmpleadoDePlanta;
import entidades.Tupla;
import excepciones.EmpleadoNoDisponibleException;
import excepciones.SinEmpleadosDisponiblesException;
import util.Validaciones;

public class EmpleadoService {

    private int siguienteLegajo = 1;
    private final Map<Integer, Empleado> empleados;

    public EmpleadoService() {
        empleados = new HashMap<>();
    }

    public void registrarEmpleado(String nombre, double valor) {
        Validaciones.validarNoVacio(nombre);
        Validaciones.validarPositivo(valor);
        Empleado empleadoNuevo = new EmpleadoContratado(nombre, valor, generarLegajo());
        empleados.put(empleadoNuevo.getLegajo(), empleadoNuevo);
    }

    public void registrarEmpleado(String nombre, double valor, String categoria) {
        Validaciones.validarNoVacio(nombre);
        Validaciones.validarPositivo(valor);
        Validaciones.validarNoVacio(categoria);

        Categoria.esCategoriaValida(categoria);

        Empleado empleadoNuevo = new EmpleadoDePlanta(nombre, valor, generarLegajo(), categoria);
        empleados.put(empleadoNuevo.getLegajo(), empleadoNuevo);
    }

    public int consultarCantidadRetrasosEmpleado(Integer legajo) {
        Empleado empleado = empleados.get(legajo);
        Validaciones.validarNoNulo(empleado);
        return empleado.cantidadRetrasos();
    }

    public boolean tieneRestrasos(Integer legajo) {
        return consultarCantidadRetrasosEmpleado(legajo) > 0;
    }

    public List<Tupla<Integer, String>> empleados() {
        List<Tupla<Integer, String>> lista = new ArrayList<>();
        for(Empleado e : empleados.values()) {
			int legajo = e.getLegajo();
			String nombre = e.getNombre();
			
			Tupla<Integer, String> tupla = new Tupla<>(legajo, nombre);
			lista.add(tupla);
		}
		return lista;
    }

    public List<Empleado> empleadosNoAsignados() {
        return List.copyOf(
            empleados.values()
                .stream()
                .filter(e -> !e.estaAsignado())
                .toList()
        );
    }

    public void liberarEmpleado(Empleado empleado) {
        Validaciones.validarNoNulo(empleado);
        if(!existeEmpleado(empleado.getLegajo())) {
            throw new EmpleadoNoDisponibleException("El empleado no esta registrado en el sistema");
        }
        if(empleado.estaAsignado()) {
            empleado.removerDeTarea();
        } else {
            throw new EmpleadoNoDisponibleException("El empleado no está asignado a ninguna tarea");
        }
    }

    public int encontrarEmpleadoMenosRetraso() {
        return empleados.values()
            .stream()
            .min(Comparator.comparingInt(Empleado::cantidadRetrasos))
            .orElseThrow(() -> new SinEmpleadosDisponiblesException("No hay empleados registrados"))
            .getLegajo();
    }

    private int generarLegajo() {
        return siguienteLegajo++;
    }

    private boolean existeEmpleado(Integer legajo) {
        return empleados.containsKey(legajo);
    }
}
