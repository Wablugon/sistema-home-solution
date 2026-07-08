package entidades;

public class EmpleadoContratado extends Empleado {
	
	public EmpleadoContratado(String nombre, double valor) {
		super(nombre, valor);
		this.tipo = "Contratado";
	}
	
	/*el valor es un parametro desde la clase abstracta Empleado, pero lo que cambia entre EmpleadoContratado
	 y EmpleadoDePlanta es el metodo de calcular su costo*/
	

	public double obtenerValorHora() {
		return this.valor;
	}

	@Override
	public double calcularCostoPorHoras(double dias, boolean huboRetraso) {
		double horasTrabajadas = dias * 8.0;
		return horasTrabajadas * this.valor;
	}
}
