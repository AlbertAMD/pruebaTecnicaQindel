/**
 * 
 */
package com.alberto.jjoo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.alberto.jjoo.entidades.TipoJuegosOlimpicos;

/**
 * @author alber
 *
 */
public class TipoJuegosOlimpicosMapper implements ResultSetExtractor<TipoJuegosOlimpicos> {

	/**
	 * 
	 */
	public TipoJuegosOlimpicosMapper() {
	}

	@Override
	public TipoJuegosOlimpicos extractData(ResultSet rs) throws SQLException, DataAccessException {
		TipoJuegosOlimpicos tipo = null;
		
		while(rs.next()) {
			tipo = new TipoJuegosOlimpicos();
			
			tipo.setIdTipoJuegosOlimpicos(rs.getInt("ID_TIPO_JUEGO"));
			tipo.setDescripcion(rs.getString("DESCRIPCION"));
		}
		
		return tipo;
	}

}
