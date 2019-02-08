/**
 * 
 */
package com.alberto.jjoo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.alberto.jjoo.entidades.dto.JuegosOlimpicosDTO;

/**
 * @author alber
 * 
 * Clase para mapear los resultados de la consulta
 *
 */
public class JuegosOlimpicosMapper implements RowMapper<JuegosOlimpicosDTO> {

	/**
	 * Constructor vacío
	 */
	public JuegosOlimpicosMapper() {
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public JuegosOlimpicosDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JuegosOlimpicosDTO sede = new JuegosOlimpicosDTO();
		
		// Se recogen los datos del país
		sede.setIdPais(rs.getInt("ID_PAIS"));
		sede.setNombrePais(rs.getString("NOMBRE_PAIS"));
		
		// Se recogen los datos de la ciudad
		sede.setIdCiudad(rs.getInt("ID_CIUDAD"));
		sede.setNombreCiudad(rs.getString("NOMBRE_CIUDAD"));
		sede.setValor(rs.getInt("VALOR"));
		sede.setNumVecesSede(rs.getInt("NUM_VECES_SEDE"));
		
		// Se recogen los datos del tipo de juegos olimpicos celebrado
		sede.setTipoJuegosOlimpicos(rs.getString("TIPO_JJOO"));
		
		return sede;
	}

}
