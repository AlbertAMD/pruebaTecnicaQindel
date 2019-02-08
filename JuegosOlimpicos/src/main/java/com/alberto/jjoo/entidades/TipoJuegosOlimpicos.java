/**
 * 
 */
package com.alberto.jjoo.entidades;

import java.io.Serializable;

/**
 * @author alber
 * 
 * Clase que representa la entidad Tipo Juegos Olimpicos
 *
 */
public class TipoJuegosOlimpicos implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -3239932982081200517L;

	private Integer idTipoJuegosOlimpicos;
	
	private String descripcion;
	
	/**
	 * Constructor vacío
	 */
	public TipoJuegosOlimpicos() {
	}
	
	/**
	 * Constructor con los parámetros obligatorios en BBDD
	 * 
	 * @param idTipoJuegosOlimpicos Identificador del tipo de juegos olimpicos
	 * @param descripcion Descripción del tipo de juegos olimpicos
	 */
	public TipoJuegosOlimpicos(Integer idTipoJuegosOlimpicos, String descripcion) {
		this.idTipoJuegosOlimpicos = idTipoJuegosOlimpicos;
		this.descripcion = descripcion;
	}

	/**
	 * @return the idTipoJuegosOlimpicos
	 */
	public Integer getIdTipoJuegosOlimpicos() {
		return idTipoJuegosOlimpicos;
	}

	/**
	 * @param idTipoJuegosOlimpicos the idTipoJuegosOlimpicos to set
	 */
	public void setIdTipoJuegosOlimpicos(Integer idTipoJuegosOlimpicos) {
		this.idTipoJuegosOlimpicos = idTipoJuegosOlimpicos;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
