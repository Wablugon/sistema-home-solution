package entidades;

public abstract class Empleado{

	private String nombre;
	private int nroLegajo;
	protected double valor;
	private Tarea tareaAsignado;
	private boolean estaOcupado;
	private int cantidadRetrasos;
	protected String tipo;
	
	public Empleado(String nombre, double valor, int legajo) {
		this.nombre = nombre;
		this.tareaAsignado = null;
		this.estaOcupado = false;
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
		//la nuevaTarea es verificada en HomeSolution
		
		if(estaAsignado()) {
			return false;
		} else {
			this.tareaAsignado = nuevaTarea;
			this.estaOcupado = true;
			return true;
		}
	}

	public boolean removerDeTarea() {
		if(!estaAsignado()) {
			return false;
		} else {
			this.tareaAsignado = null;
			this.estaOcupado = false;
			return true;
		}
	}

	public Boolean estaAsignado() {
		//estaOcupado no puede ser true si su tarea == null
		
		return this.estaOcupado;
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
