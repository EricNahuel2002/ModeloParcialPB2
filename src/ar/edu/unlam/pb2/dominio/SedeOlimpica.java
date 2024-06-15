package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SedeOlimpica {

	private String nombreSede;
	private List<Complejo> complejos;
	private List<Area> areas;
	private List<Evento> eventos;
	private List<Comisario> comisarios;

	public SedeOlimpica(String nombreSede) {
		this.nombreSede = nombreSede;
		this.complejos = new ArrayList<>();
		this.areas = new ArrayList<>();
		this.eventos = new ArrayList<>();
		this.comisarios = new ArrayList<>();
	}

	public Boolean agregarComplejo(Complejo complejo) {
		return complejos.add(complejo);
	}

	public Boolean agregarArea(Area area) {
		return areas.add(area);
	}

	public Area obtenerArea(Area area) {
		for (Area a : areas) {
			if (a.equals(area)) {
				return a;
			}
		}
		return null;
	}

	public Complejo obtenerComplejo(Complejo complejo) {
		for (Complejo c : complejos) {
			if (c.equals(complejo)) {
				return c;
			}
		}
		return null;
	}

	public Boolean designarUnAreaAUnComplejo(Area area, Complejo complejo) throws IndicadorAreaException {
		Area areaObtenida = this.obtenerArea(area);
		Complejo complejoObtenido = this.obtenerComplejo(complejo);
		return ((ComplejoPolideportivo) complejoObtenido).designarUnArea(areaObtenida);
	}

	public Boolean agregarEvento(Evento evento) {
		return eventos.add(evento);
	}

	public Evento obtenerEvento(Evento evento) {
		for (Evento e : eventos) {
			if (e.equals(evento)) {
				return e;
			}
		}
		return null;
	}

	public Boolean designarUnEventoAUnComplejo(Evento evento, Complejo complejo) {
		Evento eventoObtenido = this.obtenerEvento(evento);
		Complejo complejoObtenido = this.obtenerComplejo(complejo);
		return complejoObtenido.designarUnEvento(eventoObtenido);
	}

	public Boolean agregarComisario(Comisario comisario) {
		return comisarios.add(comisario);
	}

	public Boolean agregarUnComisarioJuezAUnEvento(Complejo complejo, Evento evento, Comisario juez)
			throws ComisarioException {
		Evento eventoObtenido = this.obtenerEvento(evento);
		Complejo complejoObtenido = this.obtenerComplejo(complejo);
		Comisario comisarioObtenido = validarQueExistaElComisario(juez);
		return complejoObtenido.agregarUnComisarioJuezAUnEvento(eventoObtenido, comisarioObtenido);
	}

	private Comisario validarQueExistaElComisario(Comisario juez) throws ComisarioException {
		Comisario comisarioObtenido = this.obtenerUnComisario(juez);
		if (comisarioObtenido == null) {
			throw new ComisarioException();
		}
		return comisarioObtenido;
	}

	public Comisario obtenerUnComisario(Comisario juez) {
		for (Comisario c : comisarios) {
			if (c.equals(juez)) {
				return c;
			}
		}
		return null;
	}

	public Integer calcularElTotalDeParticipantesDeLosEventosDeUnComplejoSimple(ComplejoSimple complejo) {
		Complejo complejoObtenido = this.obtenerComplejo(complejo);
		return complejoObtenido.calcularTotalDeParticipantesDeLosEventos();
	}

	public Double calcularElPromedioDeEdadDeLosComisariosObservadoresDeUnEventoEspecifico(Complejo complejo,
			Evento evento) {
		Complejo complejoObtenido = this.obtenerComplejo(complejo);
		Evento eventoObtenido = this.obtenerEvento(evento);
		return complejoObtenido.calcularElPromedioDeEdadDeLosComisariosObservadoresDeUnEventoEspecifico(eventoObtenido);
	}

	public HashSet<Comisario> obtenerUnaListaDeComisariosJuecesDeUnEventoEspecificoSinRepeticiones(
			ComplejoPolideportivo complejo, Evento evento) {
		Complejo complejoObtenido = this.obtenerComplejo(complejo);
		Evento eventoObtenido = this.obtenerEvento(evento);
		return complejoObtenido.obtenerUnaListaDeComisariosJuecesDeUnEventoEspecificoSinRepeticiones(eventoObtenido);
	}

	public Map<String, List<Area>> obtenerUnMapaDeUnComplejoPolideportivoConNombreDeComplejoYUbicacion(
			ComplejoPolideportivo complejo) {
		Complejo complejoObtenido = this.obtenerComplejo(complejo);
		Map<String, List<Area>> mapa = new HashMap<>();
		mapa.put(((ComplejoPolideportivo) complejoObtenido).getNombreComplejo(), ((ComplejoPolideportivo) complejoObtenido).getAreas());
		return mapa;
	}

}
