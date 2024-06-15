package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.List;

public class ComplejoPolideportivo extends Complejo {

	private List<Area> areas;
	

	public ComplejoPolideportivo(Integer id, String nombreComplejo, Double areaTotalOcupada) {
		super(id, nombreComplejo, areaTotalOcupada);
		this.areas = new ArrayList<>();
		
	}

	public Boolean designarUnArea(Area area) throws IndicadorAreaException {
		validarQueNoHayaAreaDuplicada(area);
		return areas.add(area);
	}

	private void validarQueNoHayaAreaDuplicada(Area area) throws IndicadorAreaException {
		Area areaEncontrada = this.obtenerUnArea(area);
		if(areaEncontrada != null) {
			throw new IndicadorAreaException();
		}
	}


	public Area obtenerUnArea(Area area) {
		for(Area a: areas) {
			if(a.equals(area)) {
				return a;
			}
		}
		return null;
	}


	public List<Area> getAreas() {
		return areas;
	}

	

}
