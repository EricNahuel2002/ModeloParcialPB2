package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Evento {
	private Integer id;
	private LocalDate fecha;
	private Integer duracion;
	private Integer numeroParticipantes;
	private List<Comisario> comisarios;

	public Evento(Integer id, LocalDate fecha, Integer duracion, Integer numeroParticipantes) {
		this.id = id;
		this.fecha = fecha;
		this.duracion = duracion;
		this.numeroParticipantes = numeroParticipantes;
		this.comisarios = new ArrayList<>();
	}
	
	
	public Integer getId() {
		return id;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public Integer getDuracion() {
		return duracion;
	}


	public Integer getNumeroParticipantes() {
		return numeroParticipantes;
	}


	public List<Comisario> getComisarios() {
		return comisarios;
	}


	@Override
	public int hashCode() {
		return Objects.hash(duracion, fecha, id, numeroParticipantes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(duracion, other.duracion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(id, other.id) && Objects.equals(numeroParticipantes, other.numeroParticipantes);
	}

	public Boolean agregarComisarioJuez(Comisario comisario) {
		return comisarios.add(comisario);
	}


	public Double calcularElPromedioDeEdadDeLosComisariosObservadores() {
		List<Comisario> observadores = new ArrayList<>();
		Integer edades = 0;
		for(Comisario c: comisarios) {
			if(c instanceof Observador) {
				edades += c.getEdad();
				observadores.add(c);
			}
		}
		Double promedioEdadDeLosObservadores = (double)edades / observadores.size();
		return promedioEdadDeLosObservadores;
	}


	public HashSet<Comisario> obtenerUnaListaDeComisariosJuecesSinRepeticiones() {
		HashSet<Comisario> comisariosJueces = new HashSet<>();
		for(Comisario c: comisarios) {
			if(c instanceof Juez) {
				comisariosJueces.add(c);
			}
		}
		return comisariosJueces;
	}



}
