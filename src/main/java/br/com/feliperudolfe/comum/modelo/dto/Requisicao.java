package br.com.feliperudolfe.comum.modelo.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Felipe Rudolfe
 *
 * @param <T>
 */
public class Requisicao<T extends DTO> {

	@Valid
	@NotNull(message = "Objeto 'data' deve ser informado")
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}// Requisicao()