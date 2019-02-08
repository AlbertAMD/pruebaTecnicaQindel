/**
 * 
 */
package com.alberto.jjoo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.alberto.jjoo.entidades.Pais;

/**
 * @author Borak
 *
 */
public class PaisMapper implements ResultSetExtractor<Pais> {

	/**
	 * 
	 */
	public PaisMapper() {
	}

	@Override
	public Pais extractData(ResultSet rs) throws SQLException, DataAccessException {
		Pais pais = null;
		
		while(rs.next()) {
			pais = new Pais();
			
			pais.setIdPais(rs.getInt("ID_PAIS"));
			pais.setNombrePais(rs.getString("NOMBRE_PAIS"));
			pais.setCodigoPais(rs.getString("CODIGO_PAIS"));
			pais.setValorPais(rs.getInt("VALOR_PAIS"));
		}
		
		return pais;
	}

}
