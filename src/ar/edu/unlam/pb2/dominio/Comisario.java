package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Comisario {

	private Integer dni;
	private String nombre;
	private Integer edad;

	public Comisario(Integer dni, String nombre, Integer edad) {
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
	}

	public Integer getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, edad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comisario other = (Comisario) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(edad, other.edad)
				&& Objects.equals(nombre, other.nombre);
	}

}
