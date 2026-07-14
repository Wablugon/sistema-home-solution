package servicios;

import entidades.Empleado;

public class AsignacionService {
    private EmpleadoService empleadoService;
    private ProyectoService proyectoService;

    public AsignacionService(EmpleadoService empleadoService, ProyectoService proyectoService) {
        this.empleadoService = empleadoService;
        this.proyectoService = proyectoService;
    }

    public void asignarResponsableEnTarea(Integer IDProyecto, String tituloTarea) {
        Empleado empleado = empleadoService.obtenerEmpleado();
        proyectoService.asignarEmpleado(empleado, IDProyecto, tituloTarea);
    }

    public void asignarResponsableMenosRetraso(Integer IDProyecto, String tituloTarea) {
        Empleado empleado = empleadoService.obtenerEmpleadoMenosRetrasos();
        proyectoService.asignarEmpleado(empleado, IDProyecto, tituloTarea);
    }

    public void reasignarEmpleadoEnProyecto(Integer IDProyecto, Integer legajo, String tituloTarea) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorLegajo(legajo);
        proyectoService.reasignarEmpleado(empleado, IDProyecto, tituloTarea);
    }

    public void reasignarEmpleadoConMenosRetraso(Integer IDProyecto, String tituloTarea) {
        Empleado empleado = empleadoService.obtenerEmpleadoMenosRetrasos();
        proyectoService.reasignarEmpleado(empleado, IDProyecto, tituloTarea);
    }
    
}
