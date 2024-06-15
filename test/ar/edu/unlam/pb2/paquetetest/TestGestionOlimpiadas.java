package ar.edu.unlam.pb2.paquetetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Area;
import ar.edu.unlam.pb2.dominio.Comisario;
import ar.edu.unlam.pb2.dominio.ComisarioException;
import ar.edu.unlam.pb2.dominio.Complejo;
import ar.edu.unlam.pb2.dominio.ComplejoPolideportivo;
import ar.edu.unlam.pb2.dominio.ComplejoSimple;
import ar.edu.unlam.pb2.dominio.Evento;
import ar.edu.unlam.pb2.dominio.IndicadorAreaException;
import ar.edu.unlam.pb2.dominio.Juez;
import ar.edu.unlam.pb2.dominio.Observador;
import ar.edu.unlam.pb2.dominio.SedeOlimpica;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TestGestionOlimpiadas {

	private SedeOlimpica sede;
	private static final String NOMBRE_SEDE = "sede1";

	@Before
	public void init() {
		this.sede = new SedeOlimpica(NOMBRE_SEDE);
	}

	@Test
	public void queSePuedaCrearUnComplejoSimpleEnUnaSedeOlimpica() { //del parcial
		Integer id = 1;
		String nombreComplejo = "complejo1";
		Double areaTotalOcupada = 200.0D;
		Complejo complejo = new ComplejoSimple(id, nombreComplejo, areaTotalOcupada);

		Boolean complejoDeportivoAgregado = sede.agregarComplejo(complejo);

		assertTrue(complejoDeportivoAgregado);
	}

	@Test
	public void queUnComplejoPolideportivoTengaAreasDesignadas() throws IndicadorAreaException {
		Area area = new Area(1, "Centro");
		sede.agregarArea(area);
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);

		Boolean areaDesignadaAComplejoPoli = complejo.designarUnArea(area);

		assertTrue(areaDesignadaAComplejoPoli);
	}

	@Test
	public void queSePuedaAgregarUnComplejoPolideportivoEnUnaSedeOlimpica() {
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);

		Boolean complejoDeportivoAgregado = sede.agregarComplejo(complejo);

		assertTrue(complejoDeportivoAgregado);
	}

	@Test
	public void queSePuedaCrearUnComplejoPolideportivoConUnAreaEnUnaSedeOlimpica() throws IndicadorAreaException { //del parcial
		Area area = new Area(1, "Centro");
		sede.agregarArea(area);
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		
		Boolean complejoDeportivoAgregado = sede.agregarComplejo(complejo);
		sede.designarUnAreaAUnComplejo(area,complejo);

		assertTrue(complejoDeportivoAgregado);
	}
	
	@Test
	public void queUnComplejoPolideportivoTengaEventosDesignados() {
		LocalDate fecha = LocalDate.of(2024, 6, 7);
		Integer duracion = 6;
		Integer numeroParticipantes = 200;
		Evento evento = new Evento(5,fecha,duracion, numeroParticipantes);
		sede.agregarEvento(evento);
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		sede.agregarComplejo(complejo);
		
		Boolean eventoDesignadoAlComplejo = sede.designarUnEventoAUnComplejo(evento,complejo);
		
		assertTrue(eventoDesignadoAlComplejo);
	}

	@Test
	public void queSePuedaCrearUnComplejoPolideportivoConUnAreaYUnEventoEnUnaSedeOlimpica() throws IndicadorAreaException { //del parcial
		Evento evento = new Evento(5,LocalDate.of(2024, 6, 7),6, 200);
		Area area = new Area(1, "Centro");
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		
		
		Boolean complejoDeportivoAgregado = sede.agregarComplejo(complejo);
		sede.designarUnAreaAUnComplejo(area,complejo);
		sede.designarUnEventoAUnComplejo(evento,complejo);
		
		assertTrue(complejoDeportivoAgregado);
	}
	
	@Test
	public void queSePuedaObtenerElAreaDeUnComplejo() throws IndicadorAreaException {
		Area area = new Area(1, "Centro");
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		complejo.designarUnArea(area);
		
		Area areaObtenida = complejo.obtenerUnArea(area);
		
		assertEquals(area,areaObtenida);
	}
	
	@Test (expected = IndicadorAreaException.class)
	public void queAlAgregarUnAreaAUnPolideportivoConIndicadorYaExistenteLanceUnaExcepcionIndicadorAreaException() throws IndicadorAreaException { //del parcial
		Area area = new Area(1, "Centro");
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		complejo.designarUnArea(area);
		complejo.designarUnArea(area);
	}
	
	
	@Test
	public void queSePuedaObtenerUnEventoDeUnComplejo() {
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		Evento evento = new Evento(5,LocalDate.of(2024, 6, 7),6, 200);
		complejo.designarUnEvento(evento);
		
		Evento eventoObtenido = complejo.obtenerUnEvento(evento);
		
		assertEquals(evento,eventoObtenido);
	}
	
	@Test
	public void queSePuedaObtenerUnComisario() {
		Juez juez = new Juez(34236111,"Pepe",54);
		sede.agregarComisario(juez);
		
		Comisario comisarioObtenido = sede.obtenerUnComisario(juez);
		
		assertEquals(juez,comisarioObtenido);
	}
	
	@Test
	public void queSePuedaAgregarUnComisarioJuezAUnEvento() throws IndicadorAreaException, ComisarioException { //del parcial
		Evento evento = new Evento(5,LocalDate.of(2024, 6, 7),6, 200);
		Area area = new Area(1, "Centro");
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		sede.agregarArea(area);
		sede.agregarEvento(evento);
		sede.agregarComplejo(complejo);
		Comisario juez = new Juez(34236111,"Pepe",54);
		sede.agregarComisario(juez);
		sede.designarUnEventoAUnComplejo(evento, complejo);
		
		Boolean comisarioJuezAgregadoAlEvento = sede.agregarUnComisarioJuezAUnEvento(complejo,evento,juez);
		
		assertTrue(comisarioJuezAgregadoAlEvento);
	}
	
	//del parcial
	@Test	(expected = ComisarioException.class)
	public void queAlAgregarUnComisarioJuezInexistenteLanceUnaExcepcionComisarioException() throws ComisarioException {
		Evento evento = new Evento(5,LocalDate.of(2024, 6, 7),6, 200);
		Area area = new Area(1, "Centro");
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		sede.agregarArea(area);
		sede.agregarEvento(evento);
		sede.agregarComplejo(complejo);
		Comisario juez = new Juez(34236111,"Pepe",54);
		sede.designarUnEventoAUnComplejo(evento, complejo);
		
		sede.agregarUnComisarioJuezAUnEvento(complejo,evento,juez);
	}
	
	//del parcial
	@Test
	public void queSePuedaCalcularElTotalDeParticipantesDeLosEventosDeUnComplejoSimple() {
		Evento evento = new Evento(5,LocalDate.of(2024, 6, 7),6, 200);
		Evento evento2 = new Evento(8,LocalDate.of(2024, 6, 8),6, 500);
		Evento evento3 = new Evento(1,LocalDate.of(2024, 6, 9),6, 500);
		ComplejoSimple complejo = new ComplejoSimple(5, "complejo2", 500.0);
		sede.agregarEvento(evento);
		sede.agregarEvento(evento2);
		sede.agregarEvento(evento3);
		sede.agregarComplejo(complejo);
		sede.designarUnEventoAUnComplejo(evento, complejo);
		sede.designarUnEventoAUnComplejo(evento2, complejo);
		sede.designarUnEventoAUnComplejo(evento3, complejo);
		Integer totalParticipantesEsperado = 1200;
		
		Integer totalParticipantesDeLosEventosDeUnComplejoSimple = sede.calcularElTotalDeParticipantesDeLosEventosDeUnComplejoSimple(complejo);
		
		assertEquals(totalParticipantesEsperado,totalParticipantesDeLosEventosDeUnComplejoSimple);
	}
	
	//del parcial
	@Test
	public void queSePuedaCalcularElPromedioDeEdadDeLosComisariosObservadoresDeUnEventoEspecifico() throws ComisarioException {
		Evento evento = new Evento(5,LocalDate.of(2024, 6, 7),6, 200);
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		sede.agregarEvento(evento);
		sede.agregarComplejo(complejo);
		Comisario observador = new Observador(34236111,"Pepe",54);
		sede.agregarComisario(observador);
		Comisario observador2 = new Observador(33236333,"Pepe",44);
		sede.agregarComisario(observador2);
		Comisario observador3 = new Observador(33151216,"Pepe",50);
		sede.agregarComisario(observador3);
		Comisario juez = new Juez(34236111,"Pepe",54);
		sede.agregarComisario(juez);
		sede.designarUnEventoAUnComplejo(evento, complejo);
		
		sede.agregarUnComisarioJuezAUnEvento(complejo,evento,observador);
		sede.agregarUnComisarioJuezAUnEvento(complejo,evento,observador2);
		sede.agregarUnComisarioJuezAUnEvento(complejo,evento,observador3);
		sede.agregarUnComisarioJuezAUnEvento(complejo,evento,juez);
		Double promedioEsperado = 148.0 / 3;
		
		Double promedioObtenido = sede.calcularElPromedioDeEdadDeLosComisariosObservadoresDeUnEventoEspecifico(complejo,evento);
		
		assertEquals(promedioEsperado,promedioObtenido);
	}
	
	//del parcial
	@Test
	public void queSePuedaObtenerUnaListaDeComisariosJuecesDeUnEventoEspecificoSinRepeticiones() throws ComisarioException {
		Evento evento = new Evento(5,LocalDate.of(2024, 6, 7),6, 200);
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		sede.agregarEvento(evento);
		sede.agregarComplejo(complejo);
		Comisario juez = new Juez(34236111,"Pepe",54);
		sede.agregarComisario(juez);
		Comisario juez2 = new Juez(33236333,"Pepe",44);
		sede.agregarComisario(juez2);
		Comisario juez3 = new Juez(33151216,"Pepe",50);
		sede.agregarComisario(juez3);
		Comisario observador = new Observador(34236111,"Pepe",54);
		sede.agregarComisario(observador);
		sede.designarUnEventoAUnComplejo(evento, complejo);
		
		sede.agregarUnComisarioJuezAUnEvento(complejo,evento,juez);
		sede.agregarUnComisarioJuezAUnEvento(complejo,evento,juez);
		sede.agregarUnComisarioJuezAUnEvento(complejo,evento,juez2);
		sede.agregarUnComisarioJuezAUnEvento(complejo,evento,juez3);
		sede.agregarUnComisarioJuezAUnEvento(complejo,evento,observador);
		
		HashSet<Comisario> comisariosJuecesObtenidos = sede.obtenerUnaListaDeComisariosJuecesDeUnEventoEspecificoSinRepeticiones(complejo,evento);
		
		assertEquals(3,comisariosJuecesObtenidos.size());
	}

	
	//del parcial
	@Test
	public void queSePuedaObtenerUnMapaDeUnComplejoPolideportivoConNombreDeComplejoYUbicacion() throws IndicadorAreaException {
		ComplejoPolideportivo complejo = new ComplejoPolideportivo(5, "complejo2", 500.0);
		sede.agregarComplejo(complejo);
		Area area = new Area(1, "Centro");
		Area area2 = new Area(2, "esquinaNE");
		sede.agregarArea(area);
		sede.agregarArea(area2);
		sede.designarUnAreaAUnComplejo(area, complejo);
		sede.designarUnAreaAUnComplejo(area2, complejo);
		
		
		Map<String, List<Area>> mapaObtenido = sede.obtenerUnMapaDeUnComplejoPolideportivoConNombreDeComplejoYUbicacion(complejo);
		Integer cantidadDeAreasEsperado = 2;
		
		for(Map.Entry<String,List<Area>> posicion: mapaObtenido.entrySet()) {
			assertEquals("complejo2",posicion.getKey());
			Integer cantidadDeAreas = 0;
			for(Area a: posicion.getValue()) {
				cantidadDeAreas++;
			}
			assertEquals(cantidadDeAreasEsperado,cantidadDeAreas);
		}
	}

	
}
