package servicios;

public class AsignacionService {
    private EmpleadoService empleadoService;
    private ProyectoService proyectoService;

    public AsignacionService(EmpleadoService empleadoService, ProyectoService proyectoService) {
        this.empleadoService = empleadoService;
        this.proyectoService = proyectoService;
    }

    public void asignarResponsableEnTarea(Integer numero, String titulo) {
        //TODO
    }

    public void asignarResponsableMenosRetraso(Integer numero, String titulo) {
        //TODO
    }

    public void reasignarEmpleadoEnProyecto(Integer numero, Integer legajo, String titulo) {
        //TODO
    }

    public void reasignarEmpleadoConMenosRetraso(Integer numero, String titulo) {
        //TODO
    }
    
}
