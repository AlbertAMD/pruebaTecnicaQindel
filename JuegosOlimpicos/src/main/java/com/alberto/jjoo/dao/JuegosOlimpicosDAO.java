/**
 * 
 */
package com.alberto.jjoo.dao;

import java.util.List;
import java.util.Optional;

import com.alberto.jjoo.entidades.Ciudad;
import com.alberto.jjoo.entidades.Pais;
import com.alberto.jjoo.entidades.SedeJuegosOlimpicos;
import com.alberto.jjoo.entidades.TipoJuegosOlimpicos;
import com.alberto.jjoo.entidades.dto.JuegosOlimpicosDTO;

/**
 * @author alber
 * 
 * Interfaz DAO para las operaciones de acceso a datos
 *
 */
public interface JuegosOlimpicosDAO {
	
	/**
	 * Obtiene una lista de ciudades
	 * 
	 * @return Lista de ciudades
	 */
	List<JuegosOlimpicosDTO> obtenerListaCiudades();
	
	/**
	 * Insertar información en la tabla sede_jjoo
	 * 
	 * @param nuevaSede Información a insertar
	 */
	void insertarSede(SedeJuegosOlimpicos nuevaSede);
	
	/**
	 * Busca una sede por su id(año + tipo de juegos)
	 * 
	 * @param sede Información a buscar
	 * @return Un optional con la información encontrada
	 */
	Optional<SedeJuegosOlimpicos> obtenerSedeOlimpicaById(SedeJuegosOlimpicos sede);
	
	/**
	 * Busca el tipo de juegos olimpicos
	 * 
	 * @param tipoJuegos Información a buscar
	 * @return Un optional con la información encontrada
	 */
	Optional<TipoJuegosOlimpicos> obtenerTipoJuegosOlimicos(TipoJuegosOlimpicos tipoJuegos);
	
	/**
	 * Inserta un nuevo tipo de juegos olimpicos
	 * 
	 * @param tipoJuegos Información a insertar
	 * @return El Tipo de Juegos Olímpicos que se ha insertado
	 */
	TipoJuegosOlimpicos insertarTipoJuegosOlimpicos(TipoJuegosOlimpicos tipoJuegos);
	
	/**
	 * Busca una ciudad 
	 * 
	 * @param ciudad Información a buscar
	 * @return Optional con la información encontrada
	 */
	Optional<Ciudad> obtenerCiudad(Ciudad ciudad);
	
	/**
	 * Busca un pais
	 * 
	 * @param pais Información a buscar
	 * @return Optional con la información encontrada
	 */
	Optional<Pais> obtenerPais(Pais pais);
	
	/**
	 * Inserta un nuevo País
	 * 
	 * @param pais Información a insertar
	 * @return Objeto recien creado
	 */
	Pais insertarPais(Pais pais);
	
	/**
	 * Inserta una nueva Ciudad
	 * 
	 * @param ciudad Información a insertar
	 * @return Objeto recien creado
	 */
	Ciudad insertarCiudad(Ciudad ciudad);
	
	/**
	 * Busca todas las Sedes Olímpicas
	 * 
	 * @return Lista con todas las sedes
	 */
	List<SedeJuegosOlimpicos> obtenerAllSedes();
	
	/**
	 * Busca las Sedes Olímpicas en base a unos filtros
	 * 
	 * @param sedeFilters Filtros
	 * @return Lista de Sedes encontradas
	 */
	List<SedeJuegosOlimpicos> obtenerSedesByFilters(SedeJuegosOlimpicos sedeFilters);
	
	/**
	 * Actualiza los datos de una sede
	 * 
	 * @param sedeUpdate Datos a actualizar
	 * @param sedeOrigen Información de la sede que se pretende actualizar
	 * @return Objeto actualizado
	 */
	void actualizarSedeOlimpica(SedeJuegosOlimpicos sedeUpdate, SedeJuegosOlimpicos sedeOrigen);
	
	/**
	 * Elimina una Sede Olímpica
	 * 
	 * @param sede Sede a eliminar
	 */
	void eliminarSedeOlimpica(SedeJuegosOlimpicos sede);

}
