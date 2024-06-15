package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Complejo {

	private Integer id;
	private String nombreComplejo;
	private Double areaTotalOcupada;
	private List<Evento> eventos;
	

	public Complejo(Integer id, String nombreComplejo, Double areaTotalOcupada) {
		this.id = id;
		this.nombreComplejo = nombreComplejo;
		this.areaTotalOcupada = areaTotalOcupada;
		this.eventos = new ArrayList<>();
	}

	public Boolean designarUnEvento(Evento evento) {
		return eventos.add(evento);
	}
	
	public Boolean agregarUnComisarioJuezAUnEvento(Evento evento, Comisario juez) {
		Evento eventoObtenido = this.obtenerUnEvento(evento);
		return eventoObtenido.agregarComisarioJuez(juez);
	}

	public Evento obtenerUnEvento(Evento evento) {
		for(Evento e: eventos) {
			if(e.equals(evento)) {
				return e;
			}
		}
		return null;
	}

	public Integer calcularTotalDeParticipantesDeLosEventos() {
		Integer cantidadDeParticipantesDeLosEventos = 0;
		for(Evento e: eventos) {
			cantidadDeParticipantesDeLosEventos += e.getNumeroParticipantes();
		}
		return cantidadDeParticipantesDeLosEventos;
	}

	public Double calcularElPromedioDeEdadDeLosComisariosObservadoresDeUnEventoEspecifico(Evento evento) {
		Double promedioEdadDeLosComisariosObservadoresDeUnEvento = 0.0;
		for(Evento e: eventos) {
			if(e.equals(evento)) {
				promedioEdadDeLosComisariosObservadoresDeUnEvento = e.calcularElPromedioDeEdadDeLosComisariosObservadores();
			}
		}
		return promedioEdadDeLosComisariosObservadoresDeUnEvento;
	}

	public HashSet<Comisario> obtenerUnaListaDeComisariosJuecesDeUnEventoEspecificoSinRepeticiones(
			Evento eventoObtenido) {
		Evento eventoEncontrado = this.obtenerUnEvento(eventoObtenido);
		return eventoEncontrado.obtenerUnaListaDeComisariosJuecesSinRepeticiones();
	}

	public Integer getId() {
		return id;
	}

	public String getNombreComplejo() {
		return nombreComplejo;
	}

	public Double getAreaTotalOcupada() {
		return areaTotalOcupada;
	}

	public List<Evento> getEventos() {
		return eventos;
	}
	
}
