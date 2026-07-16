package entidades;

import excepciones.DatosInvalidosException;
import excepciones.EmpleadoNoDisponibleException;

public class Tarea {
	private String titulo;
	private String descripcion;
	private double cantidadDiasNecesarios;
	private Empleado responsableACargo;
	//si esta terminada, estadoTarea = true
	private boolean estadoTarea;
	
	public Tarea(String titulo, String descripcion, double cantidadDias) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		if (esMedioDia(cantidadDias))
			cantidadDias = cantidadDias - 0.5 + 1;
		this.cantidadDiasNecesarios = cantidadDias;
		this.responsableACargo = null;
		this.estadoTarea = false;
	}
	
	public double diasTarea() {
		return this.cantidadDiasNecesarios;
	}
	
	public Empleado responsableTarea() {
		return this.responsableACargo;
	}
	
	public boolean tieneResponsable() {
		return this.responsableACargo != null;
	}
	
	public void asignarEmpleado(Empleado empleado) {
		this.responsableACargo = empleado;
	}
	
	public void finalizarTarea() {
		this.eliminarResponsable();
		this.estadoTarea = true;
	}

	public void registrarRetraso(double diasDeRetraso) {
		this.cantidadDiasNecesarios += diasDeRetraso;
		if (this.responsableACargo == null) {
    		throw new EmpleadoNoDisponibleException("No se puede registrar un retraso en una tarea sin responsable");
		} else {
			this.responsableACargo.registrarRetraso();
		}
	}

	public void eliminarResponsable() {
		this.responsableACargo = null;
	}

	public boolean estadoTarea() {
		return this.estadoTarea;
	}
	
	public String toString() {
		return this.titulo;
	}
	
	private boolean esMedioDia(double n) {
		double parteDecimal = n - Math.floor(n);
		return Math.abs(parteDecimal - 0.5) < 1e-9;
	}
}

