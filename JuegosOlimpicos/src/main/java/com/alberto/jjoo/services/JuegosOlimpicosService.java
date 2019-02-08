/**
 * 
 */
package com.alberto.jjoo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.alberto.jjoo.entidades.SedeJuegosOlimpicos;
import com.alberto.jjoo.entidades.dto.JuegosOlimpicosDTO;

/**
 * @author alber
 * 
 * Interfaz Service para la lógica de negocio
 *
 */
public interface JuegosOlimpicosService {

	/**
	 * Obtiene una lista de ciudades
	 * 
	 * @return Lista de ciudades
	 */
	List<JuegosOlimpicosDTO> obtenerListaCiudades();
	
	/**
	 * Crea una nueva sede
	 * 
	 * @param nuevaSede Valores de la nueva sede
	 * @return Respuesta
	 */
	ResponseEntity<Object> crearNuevaSede(SedeJuegosOlimpicos nuevaSede);
	
	/**
	 * Busca todas las Sedes Olímpicas
	 * 
	 * @return Lista con todas las sedes
	 */
	List<SedeJuegosOlimpicos> obtenerAllSedes();
	
	/**
	 * Busca una Sede por su id
	 * 
	 * @param sedeId Identificador de la sede a buscar
	 * @return Sede encontrada
	 */
	SedeJuegosOlimpicos obtenerSedeById(Integer year, String tipoJuegos);
	
	/**
	 * Busca las Sedes Olímpicas en base a unos filtros
	 * 
	 * @param sedeFilters Filtros
	 * @return Lista de Sedes encontradas
	 */
	List<SedeJuegosOlimpicos> obtenerSedesByFilters(SedeJuegosOlimpicos sedeFilters);
	
	/**
	 * Actualiza una Sede Olímpica
	 * 
	 * @param sedeUpdate Información que se pretende actualizar
	 * 
	 * @return Respuesta
	 */
	ResponseEntity<Object> actualizarSede(SedeJuegosOlimpicos sedeUpdate, Integer year, String tipoJuegos);
	
	/**
	 * Elimina una Sede Olímpica
	 * 
	 * @param year Año de la Sede que se quiere actualizar
	 * @param tipoJuegos Tipo de juegos olímpicos de la sede que se pretende actualizar
	 */
	void eliminarSedeOlimpica (Integer year, String tipoJuegos);
}
