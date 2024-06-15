package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Area {

	private Integer id;
	private String ubicacion;

	public Area(Integer id, String ubicacion) {
		this.id = id;
		this.ubicacion = ubicacion;
	}

	public Integer getId() {
		return id;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ubicacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Area other = (Area) obj;
		return Objects.equals(id, other.id) && Objects.equals(ubicacion, other.ubicacion);
	}
	
}
