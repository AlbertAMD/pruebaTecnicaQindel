/**
 * 
 */
package com.alberto.jjoo.entidades;

import java.io.Serializable;

/**
 * @author alber
 * 
 * Clase que representa la entidad Sede Juegos Olimpicos
 *
 */
public class SedeJuegosOlimpicos implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 5020803629648177447L;

	private Integer year;
	
	private Ciudad sede;
	
	private TipoJuegosOlimpicos tipoJuegosOlimpicos;
	
	/**
	 * Constructor vacío
	 */
	public SedeJuegosOlimpicos() {
	}
	
	/**
	 * Constructor con todos los parametros
	 * 
	 * @param year Año que fue sede la ciudad
	 * @param sede Ciudad que fue sede
	 * @param tipoJuegosOlimpicos Tipo de juegos olimpicos celebrados
	 */
	public SedeJuegosOlimpicos(Integer year, Ciudad sede, TipoJuegosOlimpicos tipoJuegosOlimpicos) {
		this.year = year;
		this.sede = sede;
		this.tipoJuegosOlimpicos = tipoJuegosOlimpicos;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the sede
	 */
	public Ciudad getSede() {
		return sede;
	}

	/**
	 * @param sede the sede to set
	 */
	public void setSede(Ciudad sede) {
		this.sede = sede;
	}

	/**
	 * @return the tipoJuegosOlimpicos
	 */
	public TipoJuegosOlimpicos getTipoJuegosOlimpicos() {
		return tipoJuegosOlimpicos;
	}

	/**
	 * @param tipoJuegosOlimpicos the tipoJuegosOlimpicos to set
	 */
	public void setTipoJuegosOlimpicos(TipoJuegosOlimpicos tipoJuegosOlimpicos) {
		this.tipoJuegosOlimpicos = tipoJuegosOlimpicos;
	}

}
