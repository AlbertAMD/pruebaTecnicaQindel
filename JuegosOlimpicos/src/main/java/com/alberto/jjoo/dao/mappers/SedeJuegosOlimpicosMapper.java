/**
 * 
 */
package com.alberto.jjoo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.alberto.jjoo.entidades.Ciudad;
import com.alberto.jjoo.entidades.Pais;
import com.alberto.jjoo.entidades.SedeJuegosOlimpicos;
import com.alberto.jjoo.entidades.TipoJuegosOlimpicos;

/**
 * @author alber
 * 
 * Clase Mapper para recoger los datos de BBDD
 *
 */
public class SedeJuegosOlimpicosMapper implements RowMapper<SedeJuegosOlimpicos> {

	/**
	 * Constructor vacío
	 */
	public SedeJuegosOlimpicosMapper() {
	}

	@Override
	public SedeJuegosOlimpicos mapRow(ResultSet rs, int rowNum) throws SQLException {
		SedeJuegosOlimpicos sede = new SedeJuegosOlimpicos();
		
		sede.setYear(rs.getInt("YEAR"));
		sede.setTipoJuegosOlimpicos(new TipoJuegosOlimpicos(rs.getInt("ID_TIPO_JUEGO"), rs.getString("DESCRIPCION_JUEGO")));
		sede.setSede(new Ciudad(rs.getInt("ID_CIUDAD"), rs.getString("NOMBRE_CIUDAD"),
				new Pais(rs.getInt("ID_PAIS"), rs.getString("NOMBRE_PAIS"), rs.getString("CODIGO_PAIS"), rs.getInt("VALOR_PAIS")),
				rs.getInt("VALOR_CIUDAD")));
		
		return sede;
	}
	
	

}
