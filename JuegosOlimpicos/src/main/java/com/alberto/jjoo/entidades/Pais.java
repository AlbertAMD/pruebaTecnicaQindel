/**
 * 
 */
package com.alberto.jjoo.entidades;

import java.io.Serializable;

/**
 * @author alber
 * 
 * Clase que representa a la entidad País
 *
 */
public class Pais implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 7436314646201544173L;

	private Integer idPais;
	
	private String nombrePais;
	
	private String codigoPais;
	
	private Integer valorPais;
	
	/**
	 * Constructor vacío
	 */
	public Pais() {
	}
	
	/**
	 * Constructor con valores obligatorios para la BBDD
	 * 
	 * @param idPais Identificador de País
	 * @param nombrePais Nombre del País
	 * @param codigoPais Código del País
	 * @param valorPais Valor del País
	 */
	public Pais(Integer idPais, String nombrePais, String codigoPais, Integer valorPais) {
		this.idPais = idPais;
		this.nombrePais = nombrePais;
		this.codigoPais = codigoPais;
		this.valorPais = valorPais;
	}

	/**
	 * @return the idPais
	 */
	public Integer getIdPais() {
		return idPais;
	}

	/**
	 * @param idPais the idPais to set
	 */
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	/**
	 * @return the nombrePais
	 */
	public String getNombrePais() {
		return nombrePais;
	}

	/**
	 * @param nombrePais the nombrePais to set
	 */
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the valorPais
	 */
	public Integer getValorPais() {
		return valorPais;
	}

	/**
	 * @param valorPais the valorPais to set
	 */
	public void setValorPais(Integer valorPais) {
		this.valorPais = valorPais;
	}

}
