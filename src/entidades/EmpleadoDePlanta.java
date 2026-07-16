package entidades;

public class EmpleadoDePlanta extends Empleado{
	private Categoria categoriaEmpleado;
	
	/*private static final int HORAS_DIA = 8;*/
	
	public EmpleadoDePlanta(String nombre, double valorDia, int legajo, Categoria categoria) {
		super(nombre, valorDia, legajo);
		this.categoriaEmpleado = categoria;
		this.tipo = "Planta";
	}
	
	/*el valor es un parametro desde la clase abstracta Empleado, pero lo que cambia entre EmpleadoContratado
	 y EmpleadoDePlanta es el metodo de calcular su costo*/

	/*public double calcularCostoAdicionalSinRetraso(double costoBase) {
	}	METODO NO TIENE SENTIDO
	*/

	public double obtenerValorDia() {
		return valor;
	}
	
	/*private double getValorHoraEquivalente() {
        return this.valor / HORAS_DIA;
    }*/

	public String obtenerCategoria() {
		return this.categoriaEmpleado.toString();
	}
    /*
	@Override
	public double calcularCostoPorHoras(double dias, boolean huboRetraso) {
		double diasAcobrar = Math.ceil(dias);
		double costo = diasAcobrar * this.valor;
		
		if(!huboRetraso) {
			costo = costo * 1.02;
		}
		
		return costo;
	}*/
	@Override
	public double calcularCostoPorHoras(double dias, boolean huboRetraso) {
		double diasAcobrar = Math.ceil(dias);
		double costo = diasAcobrar * this.valor;
		
		costo = costo * 1.02;
		
		return costo;
	}
}

