package entidades;

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
		this.eliminarEmpleado();
		this.estadoTarea = true;
	}

	public void registrarRetraso(double diasDeRetraso) {
		this.cantidadDiasNecesarios += diasDeRetraso;
		this.responsableACargo.registrarRetraso();
	}

	public void eliminarResponsable() {
		this.responsableACargo = null;
	}
	
	private void eliminarEmpleado() {
		this.responsableACargo = null;
	}

	public boolean estadoTarea() {
		return this.estadoTarea;
	}
	
	public String toString() {
		return this.titulo;
	}
	
	public boolean esMedioDia(double n) {
		double parteDecimal = n - Math.floor(n);
		return parteDecimal - 0.5 == 0;
	}
}

