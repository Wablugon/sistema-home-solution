package entidades;

public abstract class Empleado{

	private String nombre;
	private int nroLegajo;
	protected double valor;
	private Tarea tareaAsignado;
	private int cantidadRetrasos;
	protected String tipo;
	
	public Empleado(String nombre, double valor, int legajo) {
		this.nombre = nombre;
		this.tareaAsignado = null;
		this.cantidadRetrasos = 0;
		this.valor = valor;
		this.nroLegajo = legajo;
	}

	public int getLegajo() {
		return this.nroLegajo;
	}
	
	public String tipo() {
		String t = this.tipo;
		return t;
	}
	
	public boolean asignarATarea(Tarea nuevaTarea) {
		if(this.estaAsignado()) {
			return false;
		} else {
			this.tareaAsignado = nuevaTarea;
			return true;
		}
	}

	public boolean removerDeTarea() {
		if(!estaAsignado()) {
			return false;
		} else {
			this.tareaAsignado = null;
			return true;
		}
	}

	public Boolean estaAsignado() {
		return this.tareaAsignado != null;
	}

	public int cantidadRetrasos() {
		return this.cantidadRetrasos;
	}
	
	public void registrarRetraso() {
		this.cantidadRetrasos++;
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(/*"Nombre: " + nombre + ", Nº Legajo: " + */nroLegajo/* + ", Valor por hora: " + valor + ", Cantidad de Retrasos: " + cantidadRetrasos*/);
		return res.toString();
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public abstract double calcularCostoPorHoras(double dias, boolean huboRetraso);
}
