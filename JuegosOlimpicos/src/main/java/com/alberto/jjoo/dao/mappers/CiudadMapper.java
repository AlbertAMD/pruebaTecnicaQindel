/**
 * 
 */
package com.alberto.jjoo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.alberto.jjoo.entidades.Ciudad;
import com.alberto.jjoo.entidades.Pais;

/**
 * @author alber
 *
 */
public class CiudadMapper implements  ResultSetExtractor<Ciudad> {

	/**
	 * 
	 */
	public CiudadMapper() {
	}

	@Override
	public Ciudad extractData(ResultSet rs) throws SQLException, DataAccessException {
		Ciudad ciudad = null;
		
		while(rs.next()) {
			ciudad = new Ciudad();
			
			ciudad.setIdCiudad(rs.getInt("ID_CIUDAD"));
			ciudad.setNombreCiudad(rs.getString("NOMBRE_CIUDAD"));
			ciudad.setValorCiudad(rs.getInt("VALOR_CIUDAD"));
			ciudad.setPais(new Pais(rs.getInt("ID_PAIS"), rs.getString("NOMBRE_PAIS"), rs.getString("CODIGO_PAIS"), rs.getInt("VALOR_PAIS")));
		}
		
		return ciudad;
	}

}
