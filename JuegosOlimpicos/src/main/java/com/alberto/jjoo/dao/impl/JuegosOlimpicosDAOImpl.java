/**
 * 
 */
package com.alberto.jjoo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alberto.jjoo.dao.JuegosOlimpicosDAO;
import com.alberto.jjoo.dao.mappers.CiudadMapper;
import com.alberto.jjoo.dao.mappers.JuegosOlimpicosMapper;
import com.alberto.jjoo.dao.mappers.PaisMapper;
import com.alberto.jjoo.dao.mappers.SedeJuegosOlimpicosMapper;
import com.alberto.jjoo.dao.mappers.TipoJuegosOlimpicosMapper;
import com.alberto.jjoo.entidades.Ciudad;
import com.alberto.jjoo.entidades.Pais;
import com.alberto.jjoo.entidades.SedeJuegosOlimpicos;
import com.alberto.jjoo.entidades.TipoJuegosOlimpicos;
import com.alberto.jjoo.entidades.dto.JuegosOlimpicosDTO;

/**
 * @author alber
 * 
 * Clase implementación de las operaciones de acceso a base de datos
 *
 */
@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class })
public class JuegosOlimpicosDAOImpl implements JuegosOlimpicosDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	 * Constructor vacío
	 */
	public JuegosOlimpicosDAOImpl() {
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public List<JuegosOlimpicosDTO> obtenerListaCiudades() {
		return namedParameterJdbcTemplate.query(sqlListaCiudades(), new JuegosOlimpicosMapper());
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void insertarSede(SedeJuegosOlimpicos nuevaSede) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("year", nuevaSede.getYear());
		parametros.put("idTipoJjoo", nuevaSede.getTipoJuegosOlimpicos().getIdTipoJuegosOlimpicos());
		parametros.put("sede", nuevaSede.getSede().getIdCiudad());
		
		namedParameterJdbcTemplate.update(sqlInsertarSede(), parametros);
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public Optional<SedeJuegosOlimpicos> obtenerSedeOlimpicaById(SedeJuegosOlimpicos sede) {
		SedeJuegosOlimpicos resultado = null;
		
		List<SedeJuegosOlimpicos> lista = obtenerSedesOlimpicas(sede);
		
		if (lista != null && !lista.isEmpty()) {
			resultado = lista.get(0);
		}
		
		return Optional.ofNullable(resultado);
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public Optional<TipoJuegosOlimpicos> obtenerTipoJuegosOlimicos(TipoJuegosOlimpicos tipoJuegos) {
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("descripcion", tipoJuegos.getDescripcion());
		
		return Optional.ofNullable(namedParameterJdbcTemplate.query(sqlObtenerTipoJuegosOlimpicos(), parametros, new TipoJuegosOlimpicosMapper()));
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public TipoJuegosOlimpicos insertarTipoJuegosOlimpicos(TipoJuegosOlimpicos tipoJuegos) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("descripcion", tipoJuegos.getDescripcion());
		
		namedParameterJdbcTemplate.update(sqlInsertarTipoJuegosOlimipicos(), parametros);
		
		return namedParameterJdbcTemplate.query(sqlObtenerTipoJuegosOlimpicos(), parametros, new TipoJuegosOlimpicosMapper());
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public Optional<Ciudad> obtenerCiudad(Ciudad ciudad) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nombreCiudad", ciudad.getNombreCiudad());
		
		return Optional.ofNullable(namedParameterJdbcTemplate.query(sqlObtenerCiudad(), parametros, new CiudadMapper()));
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public Optional<Pais> obtenerPais(Pais pais) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nombrePais", pais.getNombrePais());
		
		return Optional.ofNullable(namedParameterJdbcTemplate.query(sqlObtenerPais(), parametros, new PaisMapper()));
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public Pais insertarPais(Pais pais) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nombrePais", pais.getNombrePais());
		parametros.put("codigoPais", pais.getCodigoPais());
		parametros.put("valorPais", pais.getValorPais());
		
		namedParameterJdbcTemplate.update(sqlInsertPais(pais), parametros);
		
		return namedParameterJdbcTemplate.query(sqlObtenerPais(), parametros, new PaisMapper());
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public Ciudad insertarCiudad(Ciudad ciudad) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nombreCiudad", ciudad.getNombreCiudad());
		parametros.put("idPais", ciudad.getPais().getIdPais());
		parametros.put("valorCiudad", ciudad.getValorCiudad());
		
		namedParameterJdbcTemplate.update(sqlInsertCiudad(ciudad), parametros);
		
		return namedParameterJdbcTemplate.query(sqlObtenerCiudad(), parametros, new CiudadMapper());
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public List<SedeJuegosOlimpicos> obtenerAllSedes() {
		return obtenerSedesOlimpicas(new SedeJuegosOlimpicos());
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public List<SedeJuegosOlimpicos> obtenerSedesByFilters(SedeJuegosOlimpicos sedeFilters) {
		return obtenerSedesOlimpicas(sedeFilters);
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void actualizarSedeOlimpica(SedeJuegosOlimpicos sedeUpdate, SedeJuegosOlimpicos sedeOrigen) {		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("yearUpdate", sedeUpdate.getYear());
		parametros.put("idTipoJjooUpdate", sedeUpdate.getTipoJuegosOlimpicos().getIdTipoJuegosOlimpicos());
		parametros.put("idSedeUpdate", sedeUpdate.getSede().getIdCiudad());
		parametros.put("yearOriginal", sedeOrigen.getYear());
		parametros.put("idTipoJjooOriginal", sedeOrigen.getTipoJuegosOlimpicos().getIdTipoJuegosOlimpicos());
		
		namedParameterJdbcTemplate.update(sqlActualizarSede(), parametros);
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void eliminarSedeOlimpica(SedeJuegosOlimpicos sede) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("year", sede.getYear());
		parametros.put("idTipoJuegos", sede.getTipoJuegosOlimpicos().getIdTipoJuegosOlimpicos());
		
		namedParameterJdbcTemplate.update(sqlEliminarSede(), parametros);
	}
	
	/**
	 * Obtiene todas las sedes de base de datos
	 * 
	 * @param sede Información de la sede para filtrar
	 * @return Lista de sedes
	 */
	private List<SedeJuegosOlimpicos> obtenerSedesOlimpicas(SedeJuegosOlimpicos sede) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		if (sede.getYear() != null) {
			parametros.put("year", sede.getYear());
		}
		
		if (sede.getTipoJuegosOlimpicos() != null) {
			if (sede.getTipoJuegosOlimpicos().getIdTipoJuegosOlimpicos() != null) {
				parametros.put("idTipoJuego", sede.getTipoJuegosOlimpicos().getIdTipoJuegosOlimpicos());
			}
			
			if (sede.getTipoJuegosOlimpicos().getDescripcion() != null) {
				parametros.put("nombreJuego", sede.getTipoJuegosOlimpicos().getDescripcion());
			}
		}
		
		if (sede.getSede() != null) {
			if (sede.getSede().getIdCiudad() != null) {
				parametros.put("idCiudad", sede.getSede().getIdCiudad());
			}
			
			if (sede.getSede().getNombreCiudad() != null) {
				parametros.put("nombreCiudad", sede.getSede().getNombreCiudad());
			}
			
			if (sede.getSede().getPais() != null) {
				if (sede.getSede().getPais().getIdPais() != null) {
					parametros.put("idPais", sede.getSede().getPais().getIdPais());
				}
				
				if (sede.getSede().getPais().getNombrePais() != null) {
					parametros.put("nombrePais", sede.getSede().getPais().getNombrePais());
				}
				
				if (sede.getSede().getPais().getCodigoPais() != null) {
					parametros.put("codigoPais", sede.getSede().getPais().getCodigoPais());
				}
				
				if (sede.getSede().getPais().getValorPais() != null) {
					parametros.put("valorPais", sede.getSede().getPais().getValorPais());
				}
			}
			
			if (sede.getSede().getValorCiudad() != null) {
				parametros.put("valorCiudad", sede.getSede().getValorCiudad());
			}
		}
		
		return namedParameterJdbcTemplate.query(sqlObtenerSede(sede), parametros, new SedeJuegosOlimpicosMapper());
	}
	
	/**
	 * Obtiene la consulta SQL para realizar la consulta contra la BBDD
	 * 
	 * @return consulta SQL para ejecutar
	 */
	private String sqlListaCiudades() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT CIUDAD.id_ciudad ID_CIUDAD, ");
		sql.append("PAIS.id_pais ID_PAIS, ");
		sql.append("PAIS.nombre_pais NOMBRE_PAIS, ");
		sql.append("CIUDAD.nombre_ciudad NOMBRE_CIUDAD, ");
		sql.append("IFNULL(CIUDAD.valor_ciudad, PAIS.valor_pais) VALOR, ");
		sql.append("TIPO.descripcion_tipo TIPO_JJOO, ");
		sql.append("COUNT(SEDES.sede) NUM_VECES_SEDE ");
		sql.append("FROM pais PAIS ");
		sql.append("LEFT JOIN ciudad CIUDAD ON PAIS.id_pais = CIUDAD.id_pais ");
		sql.append("LEFT JOIN sede_jjoo SEDES ON CIUDAD.id_ciudad = SEDES.sede ");
		sql.append("LEFT JOIN tipo_jjoo TIPO ON SEDES.id_tipo_jjoo = TIPO.id_tipo_jjoo ");
		sql.append("GROUP BY CIUDAD.id_ciudad, PAIS.id_pais, PAIS.nombre_pais, CIUDAD.nombre_ciudad, ");
		sql.append("IFNULL(CIUDAD.valor_ciudad, PAIS.valor_pais), TIPO.descripcion_tipo "); 
		sql.append("ORDER BY CIUDAD.id_ciudad, PAIS.id_pais ");
		
		return sql.toString();
	}
	
	/**
	 * Obtiene la consulta SQL para insertar información en la tabla sede_jjoo
	 * 
	 * @return Consulta SQL a ejecutar
	 */
	private String sqlInsertarSede() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO sede_jjoo (año, id_tipo_jjoo, sede) ");
		sql.append("VALUES ");
		sql.append("(:year, :idTipoJjoo, :sede) ");
		
		return sql.toString();
	}
	
	/**
	 * Obtiene la consulta SQL para buscar una Sede
	 * 
	 * @param sede Información a buscar
	 * @return Consulta SQL a ejecutar
	 */
	private String sqlObtenerSede(SedeJuegosOlimpicos sede) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT SEDE.año YEAR, ");
		sql.append("TIPO_JUEGO.id_tipo_jjoo ID_TIPO_JUEGO, ");
		sql.append("TIPO_JUEGO.descripcion_tipo DESCRIPCION_JUEGO, ");
		sql.append("CIUDAD.id_ciudad ID_CIUDAD, ");
		sql.append("CIUDAD.nombre_ciudad NOMBRE_CIUDAD, ");
		sql.append("CIUDAD.valor_ciudad VALOR_CIUDAD, ");
		sql.append("PAIS.id_pais ID_PAIS, ");
		sql.append("PAIS.nombre_pais NOMBRE_PAIS, ");
		sql.append("PAIS.codigo_pais CODIGO_PAIS, ");
		sql.append("PAIS.valor_pais VALOR_PAIS ");
		sql.append("FROM sede_jjoo SEDE ");
		sql.append("LEFT JOIN tipo_jjoo TIPO_JUEGO ON SEDE.id_tipo_jjoo = TIPO_JUEGO.id_tipo_jjoo ");
		sql.append("LEFT JOIN ciudad CIUDAD ON CIUDAD.id_ciudad = SEDE.sede ");
		sql.append("LEFT JOIN pais PAIS ON PAIS.id_pais = CIUDAD.id_pais ");
		sql.append("WHERE 1 = 1 ");
		
		if (sede.getYear() != null) {
			sql.append("AND SEDE.año = :year ");
		}
		
		if (sede.getTipoJuegosOlimpicos() != null) {
			if (sede.getTipoJuegosOlimpicos().getIdTipoJuegosOlimpicos() != null) {
				sql.append("AND SEDE.id_tipo_jjoo = :idTipoJuego ");
			}
			
			if (sede.getTipoJuegosOlimpicos().getDescripcion() != null) {
				sql.append("AND TIPO_JUEGO.descripcion_tipo LIKE :nombreJuego ");
			}
		}
		
		if (sede.getSede() != null) {
			if (sede.getSede().getIdCiudad() != null) {
				sql.append("AND SEDE.sede = :idCiudad ");
			}
			
			if (sede.getSede().getNombreCiudad() != null) {
				sql.append("AND CIUDAD.nombre_ciudad LIKE :nombreCiudad ");
			}
			
			if (sede.getSede().getPais() != null) {
				if (sede.getSede().getPais().getIdPais() != null) {
					sql.append("AND PAIS.id_pais = :idPais ");
				}
				
				if (sede.getSede().getPais().getNombrePais() != null) {
					sql.append("AND PAIS.nombre_pais LIKE :nombrePais ");
				}
				
				if (sede.getSede().getPais().getCodigoPais() != null) {
					sql.append("AND PAIS.codigo_pais LIKE :codigoPais ");
				}
				
				if (sede.getSede().getPais().getValorPais() != null) {
					sql.append("AND PAIS.valor_pais = :valorPais ");
				}
			}
			
			if (sede.getSede().getValorCiudad() != null) {
				sql.append("AND CIUDAD.valor_ciudad = :valorCiudad");
			}
		}
		
		return sql.toString();
	}
	
	/**
	 * Obtiene la consulta SQL para buscar un tipo de juegos olimpicos
	 * 
	 * @return Consulta SQL a ejecutar
	 */
	private String sqlObtenerTipoJuegosOlimpicos() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT TIPO_JUEGOS.id_tipo_jjoo ID_TIPO_JUEGO, ");
		sql.append("TIPO_JUEGOS.descripcion_tipo DESCRIPCION ");
		sql.append("FROM tipo_jjoo TIPO_JUEGOS ");
		sql.append("WHERE TIPO_JUEGOS.descripcion_tipo LIKE :descripcion ");
		
		return sql.toString();
	}
	
	/**
	 * Obtiene la consulta SQL para insertar un nuevo tipo de juegos olimpicos
	 * 
	 * @return Consulta SQL a ejecutar
	 */
	private String sqlInsertarTipoJuegosOlimipicos() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tipo_jjoo SELECT (SELECT MAX(id_tipo_jjoo) + 1 FROM tipo_jjoo), :descripcion FROM dual ");
		
		return sql.toString();
	}
	
	/**
	 * Obtiene la consulta SQL para buscar un tipo
	 * 
	 * @return Consulta SQL a ejecutar
	 */
	private String sqlObtenerCiudad() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT CIUDAD.id_ciudad ID_CIUDAD, ");
		sql.append("CIUDAD.nombre_ciudad NOMBRE_CIUDAD, ");
		sql.append("CIUDAD.valor_ciudad VALOR_CIUDAD, ");
		sql.append("PAIS.id_pais ID_PAIS, ");
		sql.append("PAIS.nombre_pais NOMBRE_PAIS, ");
		sql.append("PAIS.codigo_pais CODIGO_PAIS, ");
		sql.append("PAIS.valor_pais VALOR_PAIS ");
		sql.append("FROM ciudad CIUDAD ");
		sql.append("LEFT JOIN pais PAIS ON PAIS.id_pais = CIUDAD.id_pais ");
		sql.append("WHERE CIUDAD.nombre_ciudad LIKE :nombreCiudad ");
		
		return sql.toString();
	}
	
	/**
	 * Obtiene la consulta SQL para buscar un país
	 * 
	 * @return Consulta SQL a ejecutar
	 */
	private String sqlObtenerPais() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT PAIS.id_pais ID_PAIS, ");
		sql.append("PAIS.nombre_pais NOMBRE_PAIS, ");
		sql.append("PAIS.codigo_pais CODIGO_PAIS, ");
		sql.append("PAIS.valor_pais VALOR_PAIS ");
		sql.append("FROM pais ");
		sql.append("WHERE PAIS.nombre_pais LIKE :nombrePais ");
		
		return sql.toString();
	}
	
	/**
	 * Obtiene la consulta SQL para inserta un país
	 * 
	 * @param pais Información a insertar
	 * @return Consulta SQL a ejecutar
	 */
	private String sqlInsertPais(Pais pais) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO pais SELECT (SELECT MAX(id_pais) + 1 FROM pais), :nombrePais, :codigoPais, :valorPais ");
		
		sql.append("FROM dual ");
		
		return sql.toString();
	}
	
	/**
	 * Obtiene la consulta SQL para insertar una ciudad
	 * 
	 * @param ciudad Información a insertar
	 * @return Consulta SQL a ejecutar
	 */
	private String sqlInsertCiudad(Ciudad ciudad) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO ciudad SELECT (SELECT MAX(id_ciudad) + 1 FROM ciudad), :nombreCiudad, :idPais, :valorCiudad ");
		
		sql.append("FROM dual ");
		
		return sql.toString();
	}
	
	/**
	 * Obtiene la consulta SQL para actualiza la información de una sede
	 * 
	 * @return Consulta SQL a ejecutar
	 */
	private String sqlActualizarSede() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE sede_jjoo SET año = :yearUpdate, id_tipo_jjoo = :idTipoJjooUpdate, sede = :idSedeUpdate ");
		sql.append("WHERE año = :yearOriginal AND id_tipo_jjoo = :idTipoJjooOriginal ");
		
		return sql.toString();
	}
	
	/**
	 * Obtiene la consulta SQL para elimina una Sede Olímpica
	 * 
	 * @return Consulta SQL a ejecutar
	 */
	private String sqlEliminarSede() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM sede_jjoo WHERE año = :year AND id_tipo_jjoo = :idTipoJuegos ");
		
		return sql.toString();
	}
}
