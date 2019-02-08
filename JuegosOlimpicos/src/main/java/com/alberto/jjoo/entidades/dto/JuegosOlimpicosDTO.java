/**
 * 
 */
package com.alberto.jjoo.entidades.dto;

/**
 * @author alber
 *
 */
public class JuegosOlimpicosDTO {

	private Integer idPais;
	
	private String nombrePais;
	
	private Integer idCiudad;
	
	private String nombreCiudad;
	
	private Integer valor;
	
	private String tipoJuegosOlimpicos;
	
	private Integer numVecesSede;
	
	/**
	 * Constructor vacío
	 */
	public JuegosOlimpicosDTO() {
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
	 * @return the idCiudad
	 */
	public Integer getIdCiudad() {
		return idCiudad;
	}

	/**
	 * @param idCiudad the idCiudad to set
	 */
	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	/**
	 * @return the nombreCiudad
	 */
	public String getNombreCiudad() {
		return nombreCiudad;
	}

	/**
	 * @param nombreCiudad the nombreCiudad to set
	 */
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
	}

	/**
	 * @return the tipoJuegosOlimpicos
	 */
	public String getTipoJuegosOlimpicos() {
		return tipoJuegosOlimpicos;
	}

	/**
	 * @param tipoJuegosOlimpicos the tipoJuegosOlimpicos to set
	 */
	public void setTipoJuegosOlimpicos(String tipoJuegosOlimpicos) {
		this.tipoJuegosOlimpicos = tipoJuegosOlimpicos;
	}

	/**
	 * @return the numVecesSede
	 */
	public Integer getNumVecesSede() {
		return numVecesSede;
	}

	/**
	 * @param numVecesSede the numVecesSede to set
	 */
	public void setNumVecesSede(Integer numVecesSede) {
		this.numVecesSede = numVecesSede;
	}

}
