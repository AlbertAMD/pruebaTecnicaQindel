/**
 * 
 */
package com.alberto.jjoo.services.impls;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alberto.jjoo.dao.JuegosOlimpicosDAO;
import com.alberto.jjoo.entidades.Ciudad;
import com.alberto.jjoo.entidades.Pais;
import com.alberto.jjoo.entidades.SedeJuegosOlimpicos;
import com.alberto.jjoo.entidades.TipoJuegosOlimpicos;
import com.alberto.jjoo.entidades.dto.JuegosOlimpicosDTO;
import com.alberto.jjoo.exceptions.SedeBadRequestException;
import com.alberto.jjoo.exceptions.SedeNotFoundException;
import com.alberto.jjoo.services.JuegosOlimpicosService;

/**
 * @author alber
 * 
 * Clase implementación del servicio con la lógica de negocio
 *
 */
@Service
public class JuegosOlimpicosServiceImpl implements JuegosOlimpicosService {

	private final static Logger LOGGER = LoggerFactory.getLogger(JuegosOlimpicosServiceImpl.class);
	
	@Autowired
	private JuegosOlimpicosDAO dao;
	
	/**
	 * Constructor vacío
	 */
	public JuegosOlimpicosServiceImpl() {
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public List<JuegosOlimpicosDTO> obtenerListaCiudades() {
		LOGGER.info("Obteniendo las ciudades...");
		
		return dao.obtenerListaCiudades();
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public ResponseEntity<Object> crearNuevaSede(SedeJuegosOlimpicos nuevaSede) {
		ResponseEntity<Object> respuesta = null;
		
		try {
			LOGGER.info("Creando nueva sede... Año: " + nuevaSede.getYear() + " Tipo: " + nuevaSede.getTipoJuegosOlimpicos().getDescripcion() 
					+ " Ciudad: " + nuevaSede.getSede().getNombreCiudad());
			
			// Se comprueba si vienen los datos necesarios para crear la nueva sede en la request
			if (nuevaSede.getYear() != null && nuevaSede.getTipoJuegosOlimpicos().getDescripcion() != null
					&& nuevaSede.getSede().getNombreCiudad() != null) {
				
				// Se comprueba si ya existe la sede
				Optional<SedeJuegosOlimpicos> optionalSede = dao.obtenerSedeOlimpicaById(nuevaSede);
				
				if (optionalSede.isPresent()) {
					LOGGER.warn("La Sede indicada: Año: " + nuevaSede.getYear() + " Tipo: " + nuevaSede.getTipoJuegosOlimpicos().getDescripcion() + 
							" Ciudad: " + nuevaSede.getSede().getNombreCiudad()
							+ " ya existe... ");
					respuesta = new ResponseEntity<Object>(optionalSede.get(), HttpStatus.ACCEPTED);
				} else {
					// Se comprueba si existe el tipo de juego
					 nuevaSede.setTipoJuegosOlimpicos(compruebaTipoJuegosOlimpicos(nuevaSede.getTipoJuegosOlimpicos(), true));
					 					 
					 //Se comprueba si existe la ciudad
					nuevaSede.setSede(compruebaCiudad(nuevaSede.getSede(), true));
					 
					dao.insertarSede(nuevaSede);
					
					LOGGER.info("Insertada nueva Sede: Año: " + nuevaSede.getYear() + " Tipo: " + nuevaSede.getTipoJuegosOlimpicos().getDescripcion() + " Ciudad: " + nuevaSede.getSede().getNombreCiudad());
					respuesta = new ResponseEntity<Object>(nuevaSede, HttpStatus.OK);
				}
			} else {
				LOGGER.error("No se puede crear la Sede porque faltan datos obligatorios por rellenar...");
				
				throw new SedeBadRequestException("No se puede realizar la petición. "
						+ "Falta algún dato obligatorio para crear la sede. "
						+ "Es obligatorio indicar el año, el tipo de juegos y la ciudad.");
			}
		} catch (NullPointerException npe) {
			LOGGER.error("No se puede crear la Sede porque el JSON está mal formado.");
			
			throw new SedeBadRequestException("No se puede realizar la petición. "
					+ "El JSON de la petición está mal formado. ");
		}
		
		return respuesta;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public List<SedeJuegosOlimpicos> obtenerAllSedes() {
		LOGGER.info("Buscando todas las Sedes Olímpicas...");
		
		return dao.obtenerAllSedes();
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public SedeJuegosOlimpicos obtenerSedeById(Integer year, String tipoJuegos) {
		LOGGER.info("Buscando Sede por id...");
		
		return compruebaExisteSede(year, tipoJuegos);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public List<SedeJuegosOlimpicos> obtenerSedesByFilters(SedeJuegosOlimpicos sedeFilters) {
		LOGGER.info("Buscando Sede por filtros...");
		
		return dao.obtenerSedesByFilters(sedeFilters);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public ResponseEntity<Object> actualizarSede(SedeJuegosOlimpicos sedeUpdate, Integer year, String tipoJuegos) {
		ResponseEntity<Object> respuesta = null;
		
		LOGGER.info("Buscando Sede que se pretende actualizar...");
		
		SedeJuegosOlimpicos sedeOriginal = compruebaExisteSede(year, tipoJuegos);
			
		// Se comprueba el tipo de juegos olímpicos
		sedeUpdate.setTipoJuegosOlimpicos(compruebaTipoJuegosOlimpicos(sedeUpdate.getTipoJuegosOlimpicos(), false));
		
		// Se comprueba la ciudad
		sedeUpdate.setSede(compruebaCiudad(sedeUpdate.getSede(), false));
		
		dao.actualizarSedeOlimpica(sedeUpdate, sedeOriginal);
		
		respuesta = new ResponseEntity<Object>(sedeUpdate, HttpStatus.OK);
		
		return respuesta;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void eliminarSedeOlimpica (Integer year, String tipoJuegos) {
		SedeJuegosOlimpicos sede = compruebaExisteSede(year, tipoJuegos);
		
		dao.eliminarSedeOlimpica(sede);
	}
	
	/**
	 * Comprueba si existe el tipo de Juegos Olímpicos, en caso de no existir lo crea
	 * 
	 * @param tipoJuegosOlimpicos Información del tipo de Juegos Olímpicos
	 * @param nuevaSedeBo Indica si se trata de la creación de una sede o de una actualización de sede
	 * @return Objeto recuperado/creado de base de datos
	 */
	private TipoJuegosOlimpicos compruebaTipoJuegosOlimpicos(TipoJuegosOlimpicos tipoJuegosOlimpicos, boolean nuevaSedeBo) {
		TipoJuegosOlimpicos resultado = null;
		
		Optional<TipoJuegosOlimpicos> optionalTipoJuegos = dao.obtenerTipoJuegosOlimicos(tipoJuegosOlimpicos);
		
		 if (!optionalTipoJuegos.isPresent()) {
			 // Si el tipo de juegos no existe se inserta como nuevo
			 if (tipoJuegosOlimpicos.getDescripcion() != null) {
				 LOGGER.info("Tipo de juegos olimpicos indicado no existe... Creando el tipo de Juegos Olimpicos...");
				 resultado = dao.insertarTipoJuegosOlimpicos(tipoJuegosOlimpicos);
			 } else {
				 LOGGER.error("No existe el tipo de juegos y no se pudo crear porque faltan datos obligatorios...");
				 
				 String errorMsg = "";
				 if (nuevaSedeBo) {
					 errorMsg = "No se pudo crear la Sede Olimpica. ";
				 } else {
					 errorMsg = "No se pudo actualizar la Sede Olímpica. ";
				 }
				 
				 throw new SedeBadRequestException("No existe el tipo de juegos y no se pudo crear porque faltan datos obligatorios. "
					 		+ errorMsg);
			 }
		 } else {
			 resultado = optionalTipoJuegos.get();
		 }
		 
		 return resultado;
	}
	
	/**
	 * Comprubea si existe una ciudad, en caso de no existir se crea
	 * 
	 * @param ciudad Información de la ciudad
	 * @param nuevaSedeBo Indica si se trata de la creación de una sede o de una actualización de sede
	 * @return Objeto recuperado/creado de base de datos
	 */
	private Ciudad compruebaCiudad(Ciudad ciudad, boolean nuevaSedeBo) {
		Ciudad resultado = null;
		
		Optional<Ciudad> optionalCiudad = dao.obtenerCiudad(ciudad);
		 
		 if(!optionalCiudad.isPresent()) {
			 // Si la ciudad no existe se crea como nueva
			 if (ciudad.getNombreCiudad() != null && ciudad.getPais() != null) {
				 LOGGER.info("Ciudad indicada no existe... Creando Ciudad...");
				 
				 // Se comprueba si existe el pais
				 ciudad.setPais(compruebaPais(ciudad.getPais(), nuevaSedeBo));
				 
				 resultado = dao.insertarCiudad(ciudad);
			 } else {
				 LOGGER.error("No existe la ciudad indicada y no se pudo crear porque faltan datos obligatorios... ");
				 
				 String errorMsg = "";
				 if (nuevaSedeBo) {
					 errorMsg = "No se pudo crear la Sede Olimpica. ";
				 } else {
					 errorMsg = "No se pudo actualizar la Sede Olímpica. ";
				 }
				 
				 throw new SedeBadRequestException("No existe la ciudad y no se pudo crear porque faltan datos obligatorios. "
					 		+ errorMsg);
			 }
		 } else {
			 resultado = optionalCiudad.get();
		 }
		 
		 return resultado;
	}
	
	/**
	 * Comprueba si existe un país, en caso de no existir se crea
	 * 
	 * @param pais Información del país
	 * @param nuevaSedeBo Indica si se trata de la creación de una sede o de una actualización de sede 
	 * @return Objeto recuperado/creado de base de datos
	 */
	private Pais compruebaPais(Pais pais, boolean nuevaSedeBo) {
		Pais resultado = null;
		
		Optional<Pais> optionalPais = dao.obtenerPais(pais);
		 
		 if (!optionalPais.isPresent()) {
			 // Si el país no existe se crea como nuevo
			 if (pais.getNombrePais() != null && pais.getCodigoPais() != null && pais.getValorPais() != null) {
				 LOGGER.info("País indicado no existe... Creando Pais...");
				 
				 resultado = dao.insertarPais(pais);
			 } else {
				 LOGGER.error("No existe el país indicado y no se puede crear porque faltan datos obligatorios... ");
				 
				 String errorMsg = "";
				 if (nuevaSedeBo) {
					 errorMsg = "No se pudo crear la Sede Olimpica. ";
				 } else {
					 errorMsg = "No se pudo actualizar la Sede Olímpica. ";
				 }
				 
				 throw new SedeBadRequestException("No existe el país indicado y no se puede crear porque faltan datos obligatorios. "
					 		+ errorMsg);
			 }
		 } else {
			 resultado = optionalPais.get();
		 }
		 
		 return resultado;
	}
	
	/**
	 * Comprueba si existe una sede a través de su id
	 * 
	 * @param year Año
	 * @param tipoJuegos Tipo de juegos olímpicos
	 * @return Optional
	 */
	private SedeJuegosOlimpicos compruebaExisteSede(Integer year, String tipoJuegos) {
		 SedeJuegosOlimpicos sede = null;
		
		if (year != null && tipoJuegos != null) {
			
			SedeJuegosOlimpicos sedeId = new SedeJuegosOlimpicos();
			sedeId.setYear(year);
			sedeId.setTipoJuegosOlimpicos(new TipoJuegosOlimpicos());
			sedeId.getTipoJuegosOlimpicos().setDescripcion(tipoJuegos);
			
			Optional<SedeJuegosOlimpicos> optionalSede = dao.obtenerSedeOlimpicaById(sedeId);
			
			if (optionalSede.isPresent()) {
				sede = optionalSede.get();
			} else {
				LOGGER.error("La Sede indicada: Año: " + year + " Tipo: " + tipoJuegos + " no existe.");
				
				throw new SedeNotFoundException("La Sede indicada: Año: " + year + " Tipo: " + tipoJuegos + " no existe.");
			}
		} else {
			LOGGER.error("No se puede buscar la Sede porque no se han encontrado los parámetros en la petición.");
			
			throw new SedeBadRequestException("No se puede buscar la Sede porque no se han encontrado los parámetros en la petición.");
		}
		
		return sede;
	}
}
