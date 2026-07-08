package entidades;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Proyecto {
	
	private static int siguienteID = 0;
	
	private int numeroProyecto;
	private String direccionVivienda;
	private Map<String, Tarea> tareasSolicitadas;
	private Set<Empleado> historialEmpleado;
	private LocalDate fechaInicio;
	private LocalDate fechaEstimadaFin;
	private LocalDate fechaRealFin;
	private Estado estadoProyecto;
	private String nombreCliente;
	private int telefonoCliente;
	private String emailCliente;
	
	boolean tuvoRetraso = false;
	
	public Proyecto(String direccionVivienda, LocalDate fechaInicio, LocalDate fechaFin, String nombreCliente, int telefonoCliente, String emailCliente) {
		this.numeroProyecto = siguienteID++;
		
		this.direccionVivienda = direccionVivienda;
		
		this.tareasSolicitadas = new HashMap<>();
		this.historialEmpleado = new HashSet<>();
		
		this.fechaInicio = fechaInicio;
		this.fechaEstimadaFin = fechaFin;
		this.fechaRealFin = fechaFin;
		this.estadoProyecto = Estado.PENDIENTE;
		this.nombreCliente = nombreCliente;
		this.telefonoCliente = telefonoCliente;
		this.emailCliente = emailCliente;
	}
	
	public int getID() {
		return this.numeroProyecto;
	}
	
	public String getDomicilio() {
		return this.direccionVivienda;
	}
	
	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaRealFin;
	}
	
	public boolean cambiarAPendiente() {
		if(this.estadoProyecto != Estado.FINALIZADO) {
			this.estadoProyecto = Estado.PENDIENTE;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean cambiarAActivo() {
		if(this.estadoProyecto != Estado.FINALIZADO) {
			this.estadoProyecto = Estado.ACTIVO;
			return true;
		} else
			return false;
	}
	
	public void actualizarAFinalizado(LocalDate fechaFin) {
		this.estadoProyecto = Estado.FINALIZADO;
		this.fechaRealFin = fechaFin;
	}
	
	public Set<Empleado> devolverHistorial() {
		return this.historialEmpleado;
	}

	
	public boolean todasTareasFinalizadas() {
		for(Tarea t : tareasSolicitadas.values())
			if(!t.estadoTarea())
				return false;
		return true;
	}


	public double calcularCostoFinal() {
		double costoTotal = 0.0;
		for(Tarea t : this.tareasSolicitadas.values()) {
			if(t.tieneReponsable()) {
				Empleado responsable = t.responsableTarea();
				double dias = t.diasTarea();
				
				double costoTarea = responsable.calcularCostoPorHoras(dias, this.tuvoRetraso);
				
				costoTotal += costoTarea;
			}
		
		}
		
		double costoFinal;
		
		if(this.tuvoRetraso)
			costoFinal = costoTotal * 1.25;
		else {
			costoFinal = costoTotal * 1.35;
		}
		
		return costoFinal;
	}

	public String consultarEstado() {
		return this.estadoProyecto.toString();
	}

	public Boolean agregarTarea(String titulo, String descripcion, double diasNecesarios) {
		if(tareasSolicitadas.containsKey(titulo)) {
			return false;
		}
		Tarea nuevaTarea = new Tarea(titulo, descripcion, diasNecesarios);
		
		if(!this.estadoProyecto.equals(Estado.PENDIENTE)) {
			calcularFecha(diasNecesarios);
		}
		
		//calcularFecha(diasNecesarios);
		tareasSolicitadas.put(titulo, nuevaTarea);
		return true;
	}

	public boolean asignarEmpleado(Empleado empleadoNuevo, String tarea) {
		Tarea t = tareasSolicitadas.get(tarea);
		t.asignarEmpleado(empleadoNuevo);
		historialEmpleado.add(empleadoNuevo);
		return true;
	}

	public Tarea getTarea(String tituloTarea) {
		return tareasSolicitadas.get(tituloTarea);
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Proyecto " + this.getID() + "\n" +
				"Fecha de Inicio: " + fechaInicio.toString() +"\n" +
				"Fecha estimada: " + fechaEstimadaFin.toString() +"\n" +
				"Fecha real: " + fechaRealFin.toString() + "\n" +
				"Cliente: " + nombreCliente + "\n" +
				"Domicilio: " + direccionVivienda + "\n" +
				"Tareas: ");
		for(Tarea t : tareasSolicitadas.values()) {
			sb.append("\t- ");
			sb.append(t.toString());
			//sb.append("\n");
		}
		
		return sb.toString();
	}
	//Metodos para saber si el proyecto esta en x estado
	public boolean estaFinalizado() { //Devuelve TRUE si el proyecto se encuentra finalizado, caso contrario devuelve FALSE
		if (estadoProyecto.equals(Estado.FINALIZADO))
			return true;
		return false;
	}
	
	public boolean estaPendiente() { //Devuelve TRUE si el proyecto se encuentra pendiente, caso contrario devuelve FALSE
		if (estadoProyecto.equals(Estado.PENDIENTE))
			return true;
		return false;
	}
	
	public String direccionVivienda() {
		String direccion = this.direccionVivienda;
		return direccion;
	}
	
	public void registrarRetrasoProyecto() {
		this.tuvoRetraso = true;
	}
	
	public ArrayList<Tarea> devolverTareas() {
		ArrayList<Tarea> listaTareas = new ArrayList<>();
		
		for(Tarea t : tareasSolicitadas.values()) {
			listaTareas.add(t);
		}
		
		return listaTareas;
		
	}
	
	/*METODOS PRIVADOS AUXILIARES*/
	private void calcularFecha(double cantidadDiasNuevos) {
		//calcula la fecha estimada, con los dias que se les pase de parametro
		long diasCompletos = (long) Math.ceil(cantidadDiasNuevos);
		this.fechaEstimadaFin = this.fechaEstimadaFin.plusDays(diasCompletos);
	}



}

