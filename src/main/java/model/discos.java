package model;

public class discos {
	int id;
	String banda;
	String nombre;
	String año;
	int stock;
	String imagen;
	
	public discos(int id, String nombre, String t, String d, int s, boolean a){
		this.id = id;
		this.nombre = nombre;
		this.tipo = t;
		this.descripcion = d;
		this.stock = s;
		this.apto_celiacos = a;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nuevoNombre){
		this.nombre = nuevoNombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isApto_celiacos() {
		return apto_celiacos;
	}

	public void setApto_celiacos(boolean apto_celiacos) {
		this.apto_celiacos = apto_celiacos;
	}
	
}
